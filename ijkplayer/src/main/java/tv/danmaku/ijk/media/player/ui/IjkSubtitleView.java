package androidx.media3.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.List;

/**
 * 这是一个自定义的 SubtitleView，用于解决官方 Media3 SubtitleView 缺少自定义调节方法的问题。
 */
public class IjkSubtitleView extends FrameLayout {

    private final SubtitleView internalView;
    private float bottomPadding = 0.05f;

    public IjkSubtitleView(@NonNull Context context) {
        this(context, null);
    }

    public IjkSubtitleView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.internalView = new SubtitleView(context, attrs);
        addView(internalView);
    }

    public void setCues(@Nullable List<androidx.media3.common.Cue> cues) {
        internalView.setCues(cues);
    }

    // --- 以下是修复 SubtitleDialog 报错的关键自定义方法 ---

    public void addTextSize(float fraction) {
        // 这里简化逻辑，实际项目中你可以根据需要调整
        float currentSize = internalView.getResources().getDisplayMetrics().scaledDensity * 15; 
        internalView.setFixedTextSize(TypedValue.COMPLEX_UNIT_PX, currentSize * (1 + fraction));
    }

    public void subTextSize(float fraction) {
        float currentSize = internalView.getResources().getDisplayMetrics().scaledDensity * 15;
        internalView.setFixedTextSize(TypedValue.COMPLEX_UNIT_PX, currentSize * (1 - fraction));
    }

    public float getTextSize() {
        return 1.0f; // 返回一个基准值
    }

    public void subBottomPadding(float fraction) {
        this.bottomPadding -= fraction;
        setPadding(0, 0, 0, (int) (getHeight() * bottomPadding));
    }

    public float getBottomPadding() {
        return this.bottomPadding;
    }

    public void setUserDefaultStyle() {
        internalView.setUserDefaultStyle();
    }

    public void setUserDefaultTextSize() {
        internalView.setUserDefaultTextSize();
    }
}
