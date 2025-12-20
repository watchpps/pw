package com.fongmi.android.tv.player;

import android.net.Uri;
import com.fongmi.android.tv.Setting; // 修正后的路径
import com.fongmi.android.tv.bean.Channel;
import com.fongmi.android.tv.bean.Result;
import com.fongmi.android.tv.utils.UrlUtil;
import java.util.Map;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;
import tv.danmaku.ijk.media.player.MediaSource;
import tv.danmaku.ijk.media.player.ui.IjkSubtitleView;

public class IjkUtil {

    public static MediaSource getSource(Result result) {
        return getSource(result.getHeaders(), result.getRealUrl());
    }

    public static MediaSource getSource(Channel channel) {
        return getSource(channel.getHeaders(), channel.getUrl());
    }

    public static MediaSource getSource(Map<String, String> headers, String url) {
        Uri uri = UrlUtil.uri(url);
        return new MediaSource(Players.checkUa(headers), uri);
    }

    public static void setSubtitle(IjkMediaPlayer ijk) {
        if (ijk == null) return;
        
        Object subtitleView = ijk.getSubtitleView();
        if (subtitleView instanceof IjkSubtitleView) {
            IjkSubtitleView view = (IjkSubtitleView) subtitleView;
            
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
