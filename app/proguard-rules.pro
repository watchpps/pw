# --- TV 核心业务与服务器 (解决下载功能消失、本地服务器启动失败) ---
-keep class com.fongmi.android.tv.bean.** { *; }
-keep class com.fongmi.android.tv.api.** { *; }
-keep class com.fongmi.android.tv.server.** { *; }
-keep class com.fongmi.android.tv.db.** { *; }
-keep class com.fongmi.android.tv.utils.** { *; }

# --- Media3 / EXO 核心保护 (解决黑屏、HLS播放、下载管理器类缺失) ---
-keep class androidx.media3.** { *; }
-keep interface androidx.media3.** { *; }
-dontwarn androidx.media3.**

# 针对视频渲染核心类（黑屏修复关键）
-keep class androidx.media3.exoplayer.video.VideoRenderer { *; }
-keep class androidx.media3.exoplayer.video.MediaCodecVideoRenderer { *; }
-keep class androidx.media3.ui.PlayerView { *; }
-keep class androidx.media3.ui.StyledPlayerView { *; }
-keep class androidx.media3.exoplayer.hls.** { *; }
-keep class androidx.media3.exoplayer.dash.** { *; }

# --- Room 数据库保护 (下载记录保存依赖此框架) ---
-keep class androidx.room.** { *; }
-dontwarn androidx.room.**

# --- Gson (保持模型类不被混淆，防止解析 JSON 失败) ---
-keepattributes Signature
-keepattributes *Annotation*
-dontwarn sun.misc.**
-keep class com.google.gson.** { *; }
-keep class * extends com.google.gson.TypeAdapter
-keep class * implements com.google.gson.TypeAdapterFactory
-keep class * implements com.google.gson.JsonSerializer
-keep class * implements com.google.gson.JsonDeserializer
-keepclassmembers,allowobfuscation class * { @com.google.gson.annotations.SerializedName <fields>; }
-keep,allowobfuscation,allowshrinking class com.google.gson.reflect.TypeToken
-keep,allowobfuscation,allowshrinking class * extends com.google.gson.reflect.TypeToken

# --- SimpleXML ---
-keep interface org.simpleframework.xml.core.Label { public *; }
-keep class * implements org.simpleframework.xml.core.Label { public *; }
-keep interface org.simpleframework.xml.core.Parameter { public *; }
-keep class * implements org.simpleframework.xml.core.Parameter { public *; }
-keep interface org.simpleframework.xml.core.Extractor { public *; }
-keep class * implements org.simpleframework.xml.core.Extractor { public *; }
-keepclassmembers,allowobfuscation class * { @org.simpleframework.xml.Text <fields>; }
-keepclassmembers,allowobfuscation class * { @org.simpleframework.xml.Path <fields>; }
-keepclassmembers,allowobfuscation class * { @org.simpleframework.xml.ElementList <fields>; }
-keepclassmembers,allowobfuscation class * { @org.simpleframework.xml.Root <fields>; }
-keepclassmembers,allowobfuscation class * { @org.simpleframework.xml.Attribute <fields>; }

# --- OkHttp 3 & Okio ---
-keepattributes Signature
-keepattributes *Annotation*
-keep class okhttp3.** { *; }
-keep interface okhttp3.** { *; }
-dontwarn okhttp3.**
-keep class okio.** { *; }
-dontwarn okio.**

# --- CatVod (防止配置源读取不到) ---
-keep class com.github.catvod.** { *; }
-keep class com.github.catvod.Proxy { *; }
-keep class com.github.catvod.crawler.** { *; }
-keep class * extends com.github.catvod.crawler.Spider
-dontwarn com.github.catvod.**

# --- MultiDex 保护 ---
-keep class androidx.multidex.** { *; }

# --- 其他第三方组件保护 ---
-keep class org.fourthline.cling.** { *; }
-keep class javax.xml.** { *; }
-keep class org.chromium.net.** { *; }
-keep class com.google.net.cronet.** { *; }
-keep class tv.danmaku.ijk.media.player.** { *; }
-keep class com.p2p.** { *; }
-keep class fi.iki.elonen.** { *; }
-keep class com.whl.quickjs.** { *; }
-keep class com.fongmi.quickjs.** { *; }
-keep class com.thegrizzlylabs.sardineandroid.** { *; }
-keep class com.hierynomus.** { *; }
-keep class net.engio.mbassy.** { *; }
-keep class com.tvbus.engine.** { *; }
-keep class com.xunlei.downloadlib.** { *; }
-keep class com.sun.jna.** { *; }
-keep class com.east.android.zlive.** { *; }
-keep class com.google.zxing.** { *; }
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}
-keep class com.tencent.smtt.** { *; }
-keep class com.tencent.tbs.** { *; }
-keep class com.github.anilbeesetti.nextlib.** { *; }
