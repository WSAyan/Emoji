package com.wsayan.emoji.mig.category;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.StringRes;

import com.vanniktech.emoji.emoji.EmojiCategory;
import com.wsayan.emoji.mig.MigEmoji;
import com.wsayan.emoji.mig.R;

@SuppressWarnings("PMD.MethodReturnsInternalArray") public final class FacesCategory implements EmojiCategory {
    private static final MigEmoji[] EMOJIS = CategoryUtils.concatAll(FacesCategoryChunk0.get());

    @Override @NonNull
    public MigEmoji[] getEmojis() {
        return EMOJIS;
    }

    @Override @DrawableRes
    public int getIcon() {
        return R.drawable.faces_category;
    }

    @Override @StringRes
    public int getCategoryName() {
        return R.string.emoji_mig_category_faces;
    }
}
