package androidx.media3.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.media3.common.Cue;
import java.util.List;

/**
 * 这是一个包装类，它内部持有真正的官方 SubtitleView
 * 这样既能解决 XML 属性冲突，又能提供自定义的调节方法
 */
public class IjkSubtitleView extends FrameLayout {

    private SubtitleView internalView;
    private float bottomPadding = 0.05f;
    private float textSize = 1.0f;

    public IjkSubtitleView(@NonNull Context context) {
        this(context, null);
    }

    public IjkSubtitleView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
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

    // --- 修复 SubtitleDialog 报错的核心自定义方法 ---

    public void addTextSize(float fraction) {
        this.textSize += fraction;
        // 假设默认大小为 20sp
        internalView.setFixedTextSize(TypedValue.COMPLEX_UNIT_SP, 20 * textSize);
    }

    public void subTextSize(float fraction) {
        this.textSize -= fraction;
        internalView.setFixedTextSize(TypedValue.COMPLEX_UNIT_SP, 20 * textSize);
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
        updatePadding();
    }

    public float getBottomPadding() {
        return this.bottomPadding;
    }

    private void updatePadding() {
        // 通过设置 internalView 的 padding 来实现位置偏移
        internalView.setPadding(0, 0, 0, (int) (getResources().getDisplayMetrics().heightPixels * bottomPadding));
    }
}
