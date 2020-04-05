package com.touge.learnsunflower.data;

import androidx.annotation.NonNull;

/**
 * @Author Touge
 * @Date 2020/4/4 21:15
 * @Description
 */
public class Plant {
    public final String id;
    public final String name;
    public final String details;

    public Plant(@NonNull String id, @NonNull String name, @NonNull String details) {
        this.id = id;
        this.name = name;
        this.details = details;
    }

    @NonNull
    @Override
    public String toString() {
        return name;
    }
}
