package com.fongmi.android.tv.ui.dialog;

import android.view.LayoutInflater;
import android.view.View;
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
        // 使用通用的资源名称查找，尝试多种可能的 ID 命名
        setClick(binding.getRoot(), "text_add", v -> { if (subtitleView != null) subtitleView.addTextSize(0.05f); });
        setClick(binding.getRoot(), "text_sub", v -> { if (subtitleView != null) subtitleView.subTextSize(0.05f); });
        setClick(binding.getRoot(), "padding_add", v -> { if (subtitleView != null) subtitleView.addBottomPadding(0.01f); });
        setClick(binding.getRoot(), "padding_sub", v -> { if (subtitleView != null) subtitleView.subBottomPadding(0.01f); });
        
        // 兼容另一种命名方式
        setClick(binding.getRoot(), "text_size_add", v -> { if (subtitleView != null) subtitleView.addTextSize(0.05f); });
        setClick(binding.getRoot(), "text_size_sub", v -> { if (subtitleView != null) subtitleView.subTextSize(0.05f); });
        setClick(binding.getRoot(), "subtitle_add", v -> { if (subtitleView != null) subtitleView.addBottomPadding(0.01f); });
        setClick(binding.getRoot(), "subtitle_sub", v -> { if (subtitleView != null) subtitleView.subBottomPadding(0.01f); });
    }

    private void setClick(View root, String idName, View.OnClickListener listener) {
        int id = root.getResources().getIdentifier(idName, "id", root.getContext().getPackageName());
        if (id != 0) {
            View v = root.findViewById(id);
            if (v != null) v.setOnClickListener(listener);
        }
    }
}
