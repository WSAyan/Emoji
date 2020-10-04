package com.wsayan.emoji.mig;

import androidx.annotation.NonNull;

import com.vanniktech.emoji.EmojiProvider;
import com.vanniktech.emoji.emoji.EmojiCategory;
import com.wsayan.emoji.mig.category.AliensCategory;
import com.wsayan.emoji.mig.category.AnimalsCategory;
import com.wsayan.emoji.mig.category.FacesCategory;
import com.wsayan.emoji.mig.category.FoodsCategory;
import com.wsayan.emoji.mig.category.GesturesCategory;
import com.wsayan.emoji.mig.category.GiftsCategory;

public class MigEmojiProvider implements EmojiProvider {
    @Override
    @NonNull
    public EmojiCategory[] getCategories() {
        return new EmojiCategory[]{
                new FacesCategory(),
                new AnimalsCategory(),
                new GiftsCategory(),
                new AliensCategory(),
                new GesturesCategory(),
                new FoodsCategory()
        };
    }
}
