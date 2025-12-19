package com.fongmi.android.tv.ui.dialog;

import android.view.LayoutInflater;
import androidx.appcompat.app.AlertDialog;
import com.fongmi.android.tv.databinding.DialogSubtitleBinding;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import tv.danmaku.ijk.media.player.ui.IjkSubtitleView;

public class SubtitleDialog {

    private DialogSubtitleBinding binding;
    private IjkSubtitleView subtitleView;
    private AlertDialog dialog;

    public static SubtitleDialog create() {
        return new SubtitleDialog();
    }

    public SubtitleDialog view(IjkSubtitleView subtitleView) {
        this.subtitleView = subtitleView;
        return this;
    }

    public SubtitleDialog full(boolean full) {
        return this;
    }

    public void show(android.content.Context context) {
        binding = DialogSubtitleBinding.inflate(LayoutInflater.from(context));
        dialog = new MaterialAlertDialogBuilder(context).setView(binding.getRoot()).create();
        initView();
        dialog.show();
    }

    private void initView() {
        // 绑定调节字号的按钮事件
        binding.textAdd.setOnClickListener(v -> {
            if (subtitleView != null) subtitleView.addTextSize(0.05f);
        });

        binding.textSub.setOnClickListener(v -> {
            if (subtitleView != null) subtitleView.subTextSize(0.05f);
        });

        // 绑定调节位置（边距）的按钮事件
        binding.paddingAdd.setOnClickListener(v -> {
            if (subtitleView != null) subtitleView.addBottomPadding(0.01f);
        });

        binding.paddingSub.setOnClickListener(v -> {
            if (subtitleView != null) subtitleView.subBottomPadding(0.01f);
        });
    }
}
