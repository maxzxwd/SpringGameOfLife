package com.maxzxwd.springgameoflife.world;

import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

public final class WorldBuilder {

    long minX = 0;
    long minY = 0;
    long maxX = 2;
    long maxY = 2;

    final Set<WorldCell> aliveCells = new HashSet<>();

    public WorldBuilder randomizeAliveCells() {

        aliveCells.clear();

        for (long x = minX; x <= maxX; x++) {
            for (long y = minY; y <= maxY; y++) {

                if (ThreadLocalRandom.current().nextBoolean()) {
                    aliveCells.add(new WorldCell(x, y, 0));
                }
            }
        }

        return this;
    }

    public WorldBuilder setMinX(long minX) {

        if (minX >= maxX) {
            throw new IllegalArgumentException("minX must < maxX");
        }

        this.minX = minX;
        return this;
    }

    public WorldBuilder setMinY(long minY) {

        if (minY >= maxY) {
            throw new IllegalArgumentException("minY must < maxY");
        }

        this.minY = minY;
        return this;
    }

    public WorldBuilder setMaxX(long maxX) {

        if (maxX <= minX) {
            throw new IllegalArgumentException("maxX must > minX");
        }

        this.maxX = maxX;
        return this;
    }

    public WorldBuilder setMaxY(long maxY) {

        if (maxY <= minY) {
            throw new IllegalArgumentException("maxY must > minY");
        }

        this.maxY = maxY;
        return this;
    }

    public WorldBuilder setAliveCells(Collection<WorldCell> cells) {

        Objects.requireNonNull(cells, "cells must not be null");

        aliveCells.clear();
        aliveCells.addAll(cells);
        return this;
    }

    public World build() {
        return new World(this);
    }
}
