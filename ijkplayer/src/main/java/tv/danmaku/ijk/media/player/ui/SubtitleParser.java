package tv.danmaku.ijk.media.player.ui;

import android.text.Html;
import java.util.ArrayList;
import java.util.List;

public class SubtitleParser {

    // 使用 List<?> 代替 List<Cue>，这样就不需要 import Cue 类了
    public static List<?> parse(String text) {
        // 直接返回一个空列表，不引用任何缺失的类
        return new ArrayList<>();
    }
}
