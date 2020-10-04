package com.wsayan.emoji.mig.category;

import com.wsayan.emoji.mig.MigEmoji;

import java.util.Arrays;

public class CategoryUtils {
    static MigEmoji[] concatAll(final MigEmoji[] first, final MigEmoji[]... rest) {
        int totalLength = first.length;
        for (final MigEmoji[] array : rest) {
            totalLength += array.length;
        }

        final MigEmoji[] result = Arrays.copyOf(first, totalLength);

        int offset = first.length;
        for (final MigEmoji[] array : rest) {
            System.arraycopy(array, 0, result, offset, array.length);
            offset += array.length;
        }

        return result;
    }

    private CategoryUtils() {
        // No instances.
    }
}
