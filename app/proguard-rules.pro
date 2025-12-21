# --- 1. 基础全局配置 ---
-optimizationpasses 5
-dontusemixedcaseclassnames
-dontskipnonpubliclibraryclasses
-dontpreverify
-verbose
-ignorewarnings
-keepattributes Signature,Exceptions,*Annotation*,InnerClasses

# --- 2. TV 核心业务与服务器 (解决下载消失、服务器启动失败) ---
-keep class com.fongmi.android.tv.bean.** { *; }
-keep class com.fongmi.android.tv.api.** { *; }
-keep class com.fongmi.android.tv.server.** { *; }
-keep class com.fongmi.android.tv.db.** { *; }
-keep class com.fongmi.android.tv.utils.** { *; }
-keep class androidx.room.** { *; }
-dontwarn androidx.room.**

# --- 3. Media3 / EXO 核心保护 (解决黑屏、HLS/DASH播放) ---
-keep class androidx.media3.** { *; }
-keep interface androidx.media3.** { *; }
-dontwarn androidx.media3.**
-keep class androidx.media3.exoplayer.video.VideoRenderer { *; }
-keep class androidx.media3.exoplayer.video.MediaCodecVideoRenderer { *; }
-keep class androidx.media3.ui.PlayerView { *; }
-keep class androidx.media3.ui.StyledPlayerView { *; }

# --- 4. 修复编译报错：忽略缺失类警告 (关键修复点) ---
# Cling 引用了 Android 不存在的 Swing/AWT 类
-dontwarn javax.swing.**
-dontwarn javax.imageio.**
-dontwarn java.awt.**
-dontwarn org.fourthline.cling.support.shared.log.impl.**
-dontwarn org.fourthline.cling.support.contentdirectory.ui.**

# Smbj 引用了 Android 不存在的 GSSAPI/JGSS 类
-dontwarn org.ietf.jgss.**
-dontwarn javax.security.auth.callback.**

# 忽略系统/内部 Handler 警告
-dontwarn sun.net.www.protocol.http.**
-dontwarn sun.misc.**

# --- 5. 第三方库常规保护 ---
# Gson
-keep class com.google.gson.** { *; }
-keepclassmembers class * { @com.google.gson.annotations.SerializedName <fields>; }

# OkHttp & Okio
-keep class okhttp3.** { *; }
-keep interface okhttp3.** { *; }
-dontwarn okhttp3.**
-keep class okio.** { *; }
-dontwarn okio.**

# CatVod Crawler
-keep class com.github.catvod.** { *; }
-keep class com.github.catvod.crawler.** { *; }
-keep class * extends com.github.catvod.crawler.Spider

# 其他组件
-keep class org.fourthline.cling.** { *; }
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
-keep class com.tencent.smtt.** { *; }
-keep class com.tencent.tbs.** { *; }
-keep class com.github.anilbeesetti.nextlib.** { *; }
-keep class androidx.multidex.** { *; }
