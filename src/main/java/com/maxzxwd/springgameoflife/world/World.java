package com.maxzxwd.springgameoflife.world;

import org.jetbrains.annotations.NotNull;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public final class World implements Iterable<WorldCell> {

    private static final int BEGINNING_LIFE_FACTOR = 3;
    private static final int KEEP_ALIVE_FACTOR = 2;

    long minX;
    long minY;
    long maxX;
    long maxY;
    final Set<WorldCell> aliveCells;
    volatile int generation = 0;

    World(WorldBuilder builder) {
        minX = builder.minX;
        minY = builder.minY;
        maxX = builder.maxX;
        maxY = builder.maxY;
        aliveCells = ConcurrentHashMap.newKeySet();
        aliveCells.addAll(builder.aliveCells);
    }

    public Set<WorldCell> currentGenerationCells() {

        return aliveCells.stream()
                .filter(cell -> cell.isGeneration(generation))
                .collect(Collectors.toUnmodifiableSet());
    }

    public synchronized void nextGeneration() {

        new WorldIterator(this).forEachRemaining(cell -> {

            var cellNextGeneration = cell.nextGeneration();
            aliveCells.remove(cellNextGeneration);

            var aliveCellsAround = calcAliveCellsAround(cell);

            if (aliveCells.contains(cell) && aliveCellsAround == KEEP_ALIVE_FACTOR ||
                    aliveCellsAround == BEGINNING_LIFE_FACTOR) {
                aliveCells.add(cellNextGeneration);
            }
        });

        generation++;
    }

    public long getMinX() {
        return minX;
    }

    public long getMinY() {
        return minY;
    }

    public long getMaxX() {
        return maxX;
    }

    public long getMaxY() {
        return maxY;
    }

    public int getGeneration() {
        return generation;
    }

    private int calcAliveCellsAround(WorldCell cell) {

        var neighbors = cell.getNeighbors();
        neighbors.retainAll(aliveCells);

        return neighbors.size();
    }

    @Override
    public @NotNull Iterator<WorldCell> iterator() {
        return currentGenerationCells().iterator();
    }

    @Override
    public String toString() {

        var sb = new StringBuilder();
        var aliveCells = currentGenerationCells();

        for (long y = minY; y <= maxY; y++) {
            for (long x = minX; x <= maxX; x++) {

                if (aliveCells.contains(new WorldCell(x, y, generation))) {
                    sb.append('+');
                } else {
                    sb.append('-');
                }
            }

            if (y != maxY) {
                sb.append(System.lineSeparator());
            }
        }

        return sb.toString();
    }
}
