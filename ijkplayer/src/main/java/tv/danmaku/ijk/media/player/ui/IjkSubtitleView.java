package tv.danmaku.ijk.media.player.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.List;

public class IjkSubtitleView extends FrameLayout {

    // 使用 Object 类型在编译期规避找不到类的风险，运行期会自动链接到 androidx.media3.ui.SubtitleView
    private Object internalView;
    private float bottomPadding = 0.05f;
    private float textSize = 1.0f;

    public IjkSubtitleView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        try {
            // 动态初始化官方 View
            androidx.media3.ui.SubtitleView view = new androidx.media3.ui.SubtitleView(context, attrs);
            this.internalView = view;
            addView(view, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 将参数类型改为通用的 List，内部直接传递
    public void setCues(@Nullable List<?> cues) {
        if (internalView instanceof androidx.media3.ui.SubtitleView) {
            ((androidx.media3.ui.SubtitleView) internalView).setCues((List) cues);
        }
    }

    public void setUserDefaultStyle() {
        if (internalView instanceof androidx.media3.ui.SubtitleView) {
            ((androidx.media3.ui.SubtitleView) internalView).setUserDefaultStyle();
        }
    }

    public void setUserDefaultTextSize() {
        if (internalView instanceof androidx.media3.ui.SubtitleView) {
            ((androidx.media3.ui.SubtitleView) internalView).setUserDefaultTextSize();
        }
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
        if (internalView instanceof androidx.media3.ui.SubtitleView) {
            ((androidx.media3.ui.SubtitleView) internalView).setFixedTextSize(TypedValue.COMPLEX_UNIT_SP, 18 * textSize);
        }
    }

    public float getTextSize() { return this.textSize; }

    public void subBottomPadding(float fraction) {
        this.bottomPadding -= fraction;
        updatePadding();
    }

    public float getBottomPadding() { return this.bottomPadding; }

    private void updatePadding() {
        if (internalView instanceof androidx.media3.ui.SubtitleView) {
            androidx.media3.ui.SubtitleView view = (androidx.media3.ui.SubtitleView) internalView;
            int pb = (int) (getResources().getDisplayMetrics().heightPixels * bottomPadding);
            view.setPadding(0, 0, 0, pb);
        }
    }
}
