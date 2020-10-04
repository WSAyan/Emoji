package com.wsayan.emoji_custom;

import androidx.annotation.NonNull;

import com.vanniktech.emoji.EmojiProvider;
import com.vanniktech.emoji.emoji.EmojiCategory;
import com.wsayan.emoji_custom.category.ActivitiesCategory;

public class CustomEmojiProvider implements EmojiProvider {
    @Override
    @NonNull
    public EmojiCategory[] getCategories() {
        return new EmojiCategory[]{
                new ActivitiesCategory()
        };
    }
}
