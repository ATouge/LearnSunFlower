package com.touge.learnsunflower.data;

import androidx.annotation.NonNull;

/**
 * @Author Touge
 * @Date 2020/4/4 21:15
 * @Description
 */
public class Plant {
    private String id;
    private String name;
    private String description;

    public Plant(@NonNull String id, @NonNull String name, @NonNull String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    @NonNull
    @Override
    public String toString() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
