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
        // 尝试使用更常见的命名：textSizeAdd / textSizeSub / subtitleAdd / subtitleSub
        // 如果你的 XML 里是 text_size_add，DataBinding 会变成 textSizeAdd
        if (binding.textSizeAdd != null) {
            binding.textSizeAdd.setOnClickListener(v -> { if (subtitleView != null) subtitleView.addTextSize(0.05f); });
        }
        if (binding.textSizeSub != null) {
            binding.textSizeSub.setOnClickListener(v -> { if (subtitleView != null) subtitleView.subTextSize(0.05f); });
        }
        if (binding.subtitleAdd != null) {
            binding.subtitleAdd.setOnClickListener(v -> { if (subtitleView != null) subtitleView.addBottomPadding(0.01f); });
        }
        if (binding.subtitleSub != null) {
            binding.subtitleSub.setOnClickListener(v -> { if (subtitleView != null) subtitleView.subBottomPadding(0.01f); });
        }
    }
}
