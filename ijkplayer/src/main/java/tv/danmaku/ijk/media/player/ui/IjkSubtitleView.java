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

    public IjkSubtitleView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.internalView = new androidx.media3.ui.SubtitleView(context, attrs);
        addView(internalView, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
    }

    public void setCues(@Nullable List<?> cues) {
        if (internalView != null) internalView.setCues((List) cues);
    }

    // --- 补全 IjkUtil 调用的方法 ---
    public void setApplyEmbeddedFontSizes(boolean apply) {
        if (internalView != null) internalView.setApplyEmbeddedFontSizes(apply);
    }

    public void setApplyEmbeddedStyles(boolean apply) {
        if (internalView != null) internalView.setApplyEmbeddedStyles(apply);
    }

    public void setFractionalTextSize(float size) {
        this.textSize = size;
        if (internalView != null) internalView.setFractionalTextSize(size);
    }

    public void setBottomPaddingFraction(float fraction) {
        this.bottomPadding = fraction;
        updatePadding();
    }

    // --- SubtitleDialog 调用的调节方法 ---
    public void addTextSize(float fraction) {
        this.textSize += fraction;
        if (internalView != null) internalView.setFractionalTextSize(textSize);
    }

    public void subTextSize(float fraction) {
        this.textSize -= fraction;
        if (internalView != null) internalView.setFractionalTextSize(textSize);
    }

    public void addBottomPadding(float fraction) {
        this.bottomPadding += fraction;
        updatePadding();
    }

    public void subBottomPadding(float fraction) {
        this.bottomPadding -= fraction;
        updatePadding();
    }

    private void updatePadding() {
        if (internalView != null) {
            int pb = (int) (getResources().getDisplayMetrics().heightPixels * bottomPadding);
            internalView.setPadding(0, 0, 0, pb);
        }
    }
}
