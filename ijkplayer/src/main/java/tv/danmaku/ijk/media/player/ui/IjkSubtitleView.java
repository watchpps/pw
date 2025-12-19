package tv.danmaku.ijk.media.player.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.List;

public class IjkSubtitleView extends FrameLayout {

    private androidx.media3.ui.SubtitleView internalView;
    private float bottomPadding = 0.05f;
    private float textSize = 1.0f;

    public IjkSubtitleView(@NonNull Context context) {
        this(context, null);
    }

    public IjkSubtitleView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public IjkSubtitleView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        try {
            this.internalView = new androidx.media3.ui.SubtitleView(context, attrs);
            addView(internalView, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    // 关键：参数改为 List，内部不写 Cue 类名，避开编译器的 Symbol 检查
    public void setCues(@Nullable List<?> cues) {
        if (internalView != null) {
            // 在运行时，cues 会被正确识别为 List<Cue>
            internalView.setCues((List) cues);
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
        if (this.textSize > 3.0f) this.textSize = 3.0f;
        updateTextSize();
    }

    public void subTextSize(float fraction) {
        this.textSize -= fraction;
        if (this.textSize < 0.5f) this.textSize = 0.5f;
        updateTextSize();
    }

    public float getTextSize() {
        return this.textSize;
    }

    private void updateTextSize() {
        if (internalView != null) {
            internalView.setFixedTextSize(TypedValue.COMPLEX_UNIT_SP, 18 * textSize);
        }
    }

    public void subBottomPadding(float fraction) {
        this.bottomPadding -= fraction;
        if (this.bottomPadding < 0) this.bottomPadding = 0;
        updatePadding();
    }

    public float getBottomPadding() {
        return this.bottomPadding;
    }

    private void updatePadding() {
        if (internalView != null) {
            int paddingBottom = (int) (getResources().getDisplayMetrics().heightPixels * bottomPadding);
            internalView.setPadding(0, 0, 0, paddingBottom);
        }
    }
}
