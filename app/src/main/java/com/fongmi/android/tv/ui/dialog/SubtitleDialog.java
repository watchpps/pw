package com.fongmi.android.tv.ui.dialog;

import android.view.LayoutInflater;
import android.view.View;
import tv.danmaku.ijk.media.player.ui.IjkSubtitleView;
import com.fongmi.android.tv.databinding.DialogSubtitleBinding;
import com.fongmi.android.tv.utils.Setting;
import androidx.appcompat.app.AlertDialog;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class SubtitleDialog {
    // 假设这是你的弹窗逻辑简版，重点是类型转换
    public static void show(android.content.Context context, IjkSubtitleView subtitleView) {
        DialogSubtitleBinding binding = DialogSubtitleBinding.inflate(LayoutInflater.from(context));
        
        // 之前报错的地方现在正常了
        binding.textAdd.setOnClickListener(v -> {
            subtitleView.addTextSize(0.02f);
            Setting.putSubtitleTextSize(subtitleView.getTextSize());
        });

        binding.paddingSub.setOnClickListener(v -> {
            subtitleView.subBottomPadding(0.005f);
            Setting.putSubtitleBottomPadding(subtitleView.getBottomPadding());
        });

        new MaterialAlertDialogBuilder(context).setView(binding.getRoot()).show();
    }
}
