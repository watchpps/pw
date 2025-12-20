package com.fongmi.android.tv.player;

import com.fongmi.android.tv.Setting;
import tv.danmaku.ijk.media.player.ui.IjkVideoView;
import tv.danmaku.ijk.media.player.ui.IjkSubtitleView;

public class IjkUtil {

    /**
     * 适配 Activity 中的调用：IjkUtil.setSubtitleView(mBinding.ijk)
     * 将样式设置应用到自定义的 IjkSubtitleView 包装类中
     */
    public static void setSubtitleView(IjkVideoView ijk) {
        if (ijk == null || ijk.getSubtitleView() == null) return;
        
        Object subtitleView = ijk.getSubtitleView();
        
        // 检查是否是我们之前定义的包装类
        if (subtitleView instanceof IjkSubtitleView) {
            IjkSubtitleView view = (IjkSubtitleView) subtitleView;
            
            // 应用设置中的字幕样式
            view.setApplyEmbeddedFontSizes(false);
            view.setApplyEmbeddedStyles(!Setting.isCaption());
            
            // 设置字体大小
            if (Setting.getSubtitleTextSize() != 0) {
                view.setFractionalTextSize(Setting.getSubtitleTextSize());
            }
            
            // 设置底部边距
            if (Setting.getSubtitleBottomPadding() != 0) {
                view.setBottomPaddingFraction(Setting.getSubtitleBottomPadding());
            }
        }
    }
}
    }
}
