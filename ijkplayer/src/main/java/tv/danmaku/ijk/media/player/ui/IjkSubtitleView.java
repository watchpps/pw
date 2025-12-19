package tv.danmaku.ijk.media.player.ui; // 修正为与你物理路径一致的包名

import android.content.Context;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.media3.common.Cue;
import androidx.media3.ui.SubtitleView;
import java.util.List;

public class IjkSubtitleView extends FrameLayout {

    private SubtitleView internalView;
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
        this.internalView = new SubtitleView(context, attrs);
        addView(internalView, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
    }

    public void setCues(@Nullable List<Cue> cues) {
        if (internalView != null) internalView.setCues(cues);
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

    private void updateTextSize() {
        if (internalView != null) {
            internalView.setFixedTextSize(TypedValue.COMPLEX_UNIT_SP, 18 * textSize);
        }
    }

    public float getTextSize() {
        return this.textSize;
    }

    public void subBottomPadding(float fraction) {
        this.bottomPadding -= fraction;
        if (this.bottomPadding < 0) this.bottomPadding = 0;
        updatePadding();
    }

    public void addBottomPadding(float fraction) {
        this.bottomPadding += fraction;
        if (this.bottomPadding > 0.5f) this.bottomPadding = 0.5f;
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
