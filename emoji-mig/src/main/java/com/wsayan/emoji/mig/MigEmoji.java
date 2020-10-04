package com.wsayan.emoji.mig;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.LruCache;

import androidx.annotation.NonNull;

import com.vanniktech.emoji.emoji.CacheKey;
import com.vanniktech.emoji.emoji.Emoji;

import java.lang.ref.SoftReference;

public class MigEmoji extends Emoji {
    private static final int CACHE_SIZE = 100;
    private static final int SPRITE_SIZE = 64;
    private static final int SPRITE_SIZE_INC_BORDER = 66;
    private static final int NUM_STRIPS = 56;

    private static final Object LOCK = new Object();

    private static final SoftReference[] STRIP_REFS = new SoftReference[NUM_STRIPS];
    private static final LruCache<CacheKey, Bitmap> BITMAP_CACHE = new LruCache<>(CACHE_SIZE);

    static {
        for (int i = 0; i < NUM_STRIPS; i++) {
            STRIP_REFS[i] = new SoftReference<Bitmap>(null);
        }
    }

    private final int icon;

    public MigEmoji(@NonNull final int[] codePoints, @NonNull final String[] shortcodes, final int icon, final int y,
                       final boolean isDuplicate, int drawable) {
        super(codePoints, shortcodes, -1, isDuplicate);

        this.icon = icon;
    }

    public MigEmoji(final int codePoint, @NonNull final String[] shortcodes, final int icon, final int y,
                       final boolean isDuplicate) {
        super(codePoint, shortcodes, -1, isDuplicate);

        this.icon = icon;
    }

    public MigEmoji(final int codePoint, @NonNull final String[] shortcodes, final int icon, final int y,
                       final boolean isDuplicate, final Emoji... variants) {
        super(codePoint, shortcodes, -1, isDuplicate, variants);
        this.icon = icon;
    }

    public MigEmoji(@NonNull final int[] codePoints, @NonNull final String[] shortcodes, final int icon, final int y,
                       final boolean isDuplicate, final Emoji... variants) {
        super(codePoints, shortcodes, -1, isDuplicate, variants);
        this.icon = icon;
    }

    @Override
    @NonNull
    public Drawable getDrawable(final Context context) {
        final CacheKey key = new CacheKey(icon, icon);
        final Bitmap bitmap = BITMAP_CACHE.get(key);
        if (bitmap != null) {
            return new BitmapDrawable(context.getResources(), bitmap);
        }

        Bitmap newIcon = BitmapFactory.decodeResource(context.getResources(), icon);
        BITMAP_CACHE.put(key, newIcon);
        return new BitmapDrawable(context.getResources(), newIcon);
    }


    @Override
    public void destroy() {
        synchronized (LOCK) {
            BITMAP_CACHE.evictAll();
            for (int i = 0; i < NUM_STRIPS; i++) {
                final Bitmap strip = (Bitmap) STRIP_REFS[i].get();
                if (strip != null) {
                    strip.recycle();
                    STRIP_REFS[i].clear();
                }
            }
        }
    }
}
