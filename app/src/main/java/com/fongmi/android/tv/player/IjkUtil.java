package com.fongmi.android.tv.player;

import android.net.Uri;
import com.fongmi.android.tv.Setting;
import com.fongmi.android.tv.utils.UrlUtil;
import java.util.Map;
import tv.danmaku.ijk.media.player.MediaSource;
import tv.danmaku.ijk.media.player.ui.IjkVideoView;
import tv.danmaku.ijk.media.player.ui.IjkSubtitleView;

public class IjkUtil {

    // 补回原本缺失的 getSource 方法，修复 Players.java 的报错
    public static MediaSource getSource(Map<String, String> headers, String url) {
        Uri uri = UrlUtil.uri(url);
        return new MediaSource(Players.checkUa(headers), uri);
    }

    public static void setSubtitleView(IjkVideoView ijk) {
        if (ijk == null || ijk.getSubtitleView() == null) return;
        Object subtitleView = ijk.getSubtitleView();
        if (subtitleView instanceof IjkSubtitleView) {
            IjkSubtitleView view = (IjkSubtitleView) subtitleView;
            view.setApplyEmbeddedFontSizes(false);
            view.setApplyEmbeddedStyles(!Setting.isCaption());
            if (Setting.getSubtitleTextSize() != 0) view.setFractionalTextSize(Setting.getSubtitleTextSize());
            if (Setting.getSubtitleBottomPadding() != 0) view.setBottomPaddingFraction(Setting.getSubtitleBottomPadding());
        }
    }
}
