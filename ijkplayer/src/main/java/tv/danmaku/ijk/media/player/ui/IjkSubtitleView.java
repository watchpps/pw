package androidx.media3.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.media3.common.Cue;
import androidx.media3.ui.SubtitleView; // 确保显式导入官方类
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
        this(context, attrs, 0);
    }

    public IjkSubtitleView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        // 初始化官方的 SubtitleView
        this.internalView = new SubtitleView(context, attrs);
        // 将官方 View 撑满整个父容器
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
        if (this.textSize > 3.0f) this.textSize = 3.0f; // 限制最大缩放
        updateTextSize();
    }

    public void subTextSize(float fraction) {
        this.textSize -= fraction;
        if (this.textSize < 0.5f) this.textSize = 0.5f; // 限制最小缩放
        updateTextSize();
    }

    private void updateTextSize() {
        // 使用 18sp 作为基准字号进行缩放
        internalView.setFixedTextSize(TypedValue.COMPLEX_UNIT_SP, 18 * textSize);
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
        if (this.bottomPadding > 0.5f) this.bottomPadding = 0.5f; // 限制最大边距
        updatePadding();
    }

    public float getBottomPadding() {
        return this.bottomPadding;
    }

    private void updatePadding() {
        // 通过设置 internalView 的底边距来实现字幕位置上移
        // getResources().getDisplayMetrics().heightPixels 获取屏幕总高度
        int paddingBottom = (int) (getResources().getDisplayMetrics().heightPixels * bottomPadding);
        internalView.setPadding(0, 0, 0, paddingBottom);
    }
}
