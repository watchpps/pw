# --- 1. 基础全局配置 ---
-optimizationpasses 5
-dontusemixedcaseclassnames
-dontskipnonpubliclibraryclasses
-dontpreverify
-verbose
-ignorewarnings
-keepattributes Signature,Exceptions,*Annotation*,InnerClasses,EnclosingMethod

# --- 2. 解决黑屏：必须保留 ExoPlayer 渲染器及工厂类 ---
# 黑屏通常是因为 R8 删除了反射调用的构造函数
-keep class androidx.media3.exoplayer.video.VideoRenderer { *; }
-keep class androidx.media3.exoplayer.video.MediaCodecVideoRenderer { *; }
-keep class androidx.media3.exoplayer.RenderersFactory { *; }
-keep class androidx.media3.exoplayer.DefaultRenderersFactory { *; }
-keep class androidx.media3.exoplayer.mediacodec.MediaCodecSelector { *; }
-keep class androidx.media3.ui.** { *; }
-keep class androidx.media3.common.** { *; }
-keep class com.github.anilbeesetti.nextlib.** { *; }

# --- 3. 找回下载功能与配置：保护业务核心 ---
-keep class com.fongmi.android.tv.BuildConfig { *; }
-keep class com.fongmi.android.tv.bean.** { *; }
-keep class com.fongmi.android.tv.api.** { *; }
-keep class com.fongmi.android.tv.server.** { *; }
-keep class com.fongmi.android.tv.db.** { *; }
-keep class com.fongmi.android.tv.utils.** { *; }
-keep class com.fongmi.android.tv.utils.Prefers { *; }

# 保护 Room 数据库
-keep class androidx.room.** { *; }
-dontwarn androidx.room.**

# --- 4. 忽略编译报错 (Cling/Smbj/OkHttp) ---
-dontwarn javax.swing.**
-dontwarn javax.imageio.**
-dontwarn java.awt.**
-dontwarn org.ietf.jgss.**
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
-dontwarn com.github.catvod.**

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
-keep class androidx.multidex.** { *; }
