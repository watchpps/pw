package com.fongmi.android.tv.ui.dialog;

import android.view.LayoutInflater;
import androidx.appcompat.app.AlertDialog;
import com.fongmi.android.tv.databinding.DialogSubtitleBinding;
import com.fongmi.android.tv.impl.SubtitleCallback;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

// 关键：确保导入了我们自定义的 IjkSubtitleView
import tv.danmaku.ijk.media.player.ui.IjkSubtitleView;

public class SubtitleDialog {

    private DialogSubtitleBinding binding;
    private IjkSubtitleView subtitleView;
    private SubtitleCallback callback;
    private AlertDialog dialog;
    private boolean full;

    public static SubtitleDialog create() {
        return new SubtitleDialog();
    }

    public SubtitleDialog view(IjkSubtitleView subtitleView) {
        this.subtitleView = subtitleView;
        return this;
    }

    public SubtitleDialog full(boolean full) {
        this.full = full;
        return this;
    }

    public SubtitleDialog callback(SubtitleCallback callback) {
        this.callback = callback;
        return this;
    }

    public void show(android.content.Context context) {
        binding = DialogSubtitleBinding.inflate(LayoutInflater.from(context));
        dialog = new MaterialAlertDialogBuilder(context).setView(binding.getRoot()).create();
        initView();
        dialog.show();
    }

    private void initView() {
        // 这里暂时注释掉 Setting 相关的调用，或者确保路径正确
        // 如果 Setting 报错，我们先用直接的方法测试编译
        binding.textAdd.setOnClickListener(v -> {
            if (subtitleView != null) subtitleView.addTextSize(0.05f);
        });

        binding.textSub.setOnClickListener(v -> {
            if (subtitleView != null) subtitleView.subTextSize(0.05f);
        });

        binding.paddingAdd.setOnClickListener(v -> {
            if (subtitleView != null) subtitleView.addBottomPadding(0.01f);
        });

        binding.paddingSub.setOnClickListener(v -> {
            if (subtitleView != null) subtitleView.subBottomPadding(0.01f);
        });
    }
}
