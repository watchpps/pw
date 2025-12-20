package com.fongmi.android.tv.player;

import com.fongmi.android.tv.Setting;
import tv.danmaku.ijk.media.player.ui.IjkVideoView;
import tv.danmaku.ijk.media.player.ui.IjkSubtitleView;

public class IjkUtil {

    /**
     * 适配 Activity 中的调用：IjkUtil.setSubtitleView(mBinding.ijk)
     */
    public static void setSubtitleView(IjkVideoView ijk) {
        if (ijk == null || ijk.getSubtitleView() == null) return;
        
        Object subtitleView = ijk.getSubtitleView();
        
        // 检查是否为自定义包装类 IjkSubtitleView
        if (subtitleView instanceof IjkSubtitleView) {
            IjkSubtitleView view = (IjkSubtitleView) subtitleView;
            
            // 应用 Setting 中的样式设置
            view.setApplyEmbeddedFontSizes(false);
            view.setApplyEmbeddedStyles(!Setting.isCaption());
            
            if (Setting.getSubtitleTextSize() != 0) {
                view.setFractionalTextSize(Setting.getSubtitleTextSize());
            }
            
            if (Setting.getSubtitleBottomPadding() != 0) {
                view.setBottomPaddingFraction(Setting.getSubtitleBottomPadding());
            }
        }
    }
}
