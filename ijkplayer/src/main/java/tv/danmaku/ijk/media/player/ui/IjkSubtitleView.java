package tv.danmaku.ijk.media.player.ui; // 必须是这个，不能是 androidx.media3.ui

import android.content.Context;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.List;

// 注意：如果编译依然找不到 Cue，说明 media3 库没拉下来
// 我们可以暂时改用 List<?> 来避开显式引用 Cue 类，确保编译通过
public class IjkSubtitleView extends FrameLayout {

    private androidx.media3.ui.SubtitleView internalView;
    private float bottomPadding = 0.05f;
    private float textSize = 1.0f;

    public IjkSubtitleView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.internalView = new androidx.media3.ui.SubtitleView(context, attrs);
        addView(internalView, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
    }

    // 使用通配符 List<?>，避免直接写 Cue 导致找不到符号
    public void setCues(@Nullable List<?> cues) {
        if (internalView != null) {
            internalView.setCues((List<androidx.media3.common.Cue>) cues);
        }
    }

    public void setUserDefaultStyle() {
        if (internalView != null) internalView.setUserDefaultStyle();
    }

    public void setUserDefaultTextSize() {
        if (internalView != null) internalView.setUserDefaultTextSize();
    }

    public void addTextSize(float fraction) {
        this.textSize += fraction;
        updateTextSize();
    }

    public void subTextSize(float fraction) {
        this.textSize -= fraction;
        updateTextSize();
    }

    private void updateTextSize() {
        if (internalView != null) {
            internalView.setFixedTextSize(TypedValue.COMPLEX_UNIT_SP, 18 * textSize);
        }
    }

    public float getTextSize() { return this.textSize; }

    public void subBottomPadding(float fraction) {
        this.bottomPadding -= fraction;
        updatePadding();
    }

    public float getBottomPadding() { return this.bottomPadding; }

    private void updatePadding() {
        if (internalView != null) {
            int pb = (int) (getResources().getDisplayMetrics().heightPixels * bottomPadding);
            internalView.setPadding(0, 0, 0, pb);
        }
    }
}
