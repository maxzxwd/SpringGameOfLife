package com.maxzxwd.springgameoflife.world;

import java.util.Iterator;
import java.util.NoSuchElementException;

class WorldIterator implements Iterator<WorldCell> {

    private WorldCell nextCell;
    private final World world;

    WorldIterator(World world) {

       this.world = world;
       this.nextCell = new WorldCell(world.minX, world.minY, world.generation);
    }

    @Override
    public boolean hasNext() {
        return nextCell != null;
    }

    @Override
    public WorldCell next() {

        if (!hasNext()) {
            throw new NoSuchElementException();
        }

        var cell = nextCell;

        if (cell.x() == world.maxX && cell.y() == world.maxY) {
            nextCell = null;
        } else if (cell.x() == world.maxX) {
            nextCell = new WorldCell(world.minX, cell.y() + 1, cell.generation());
        } else {
            nextCell = new WorldCell(cell.x() + 1, cell.y(), cell.generation());
        }

        return cell;
    }
}
