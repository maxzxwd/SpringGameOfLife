package com.maxzxwd.springgameoflife.world;

import java.util.HashSet;
import java.util.Set;

public record WorldCell(long x, long y, int generation) {

    private static final int BUFFERIZATION_FACTOR = 2;

    public WorldCell(long x, long y, int generation) {
        this.x = x;
        this.y = y;
        this.generation = generation % BUFFERIZATION_FACTOR;
    }

    HashSet<WorldCell> getNeighbors() {

        return new HashSet<>(Set.of(
                new WorldCell(x, y - 1, generation),
                new WorldCell(x + 1, y - 1, generation),
                new WorldCell(x + 1, y, generation),
                new WorldCell(x + 1, y + 1, generation),
                new WorldCell(x, y + 1, generation),
                new WorldCell(x - 1, y + 1, generation),
                new WorldCell(x - 1, y, generation),
                new WorldCell(x - 1, y - 1, generation)
        ));
    }

    boolean isGeneration(int generation) {
        return this.generation == generation % BUFFERIZATION_FACTOR;
    }

    WorldCell nextGeneration() {
        return new WorldCell(x, y, (generation + 1) % BUFFERIZATION_FACTOR);
    }
}
