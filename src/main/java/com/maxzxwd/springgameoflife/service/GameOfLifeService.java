package com.maxzxwd.springgameoflife.service;

import com.maxzxwd.springgameoflife.dto.ActiveCell;
import com.maxzxwd.springgameoflife.dto.CreateWorldRequest;
import com.maxzxwd.springgameoflife.dto.CreateWorldResponse;
import com.maxzxwd.springgameoflife.dto.GetWorldResponse;
import com.maxzxwd.springgameoflife.world.World;
import com.maxzxwd.springgameoflife.world.WorldBuilder;
import com.maxzxwd.springgameoflife.world.WorldCell;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Service
public class GameOfLifeService {

    private final Map<String, World> worlds = new ConcurrentHashMap<>();

    public CreateWorldResponse processCreateWorldRequest(CreateWorldRequest request) {

        var worldBuilder = new WorldBuilder();

        if (request.minX() != null) {
            worldBuilder.setMinX(request.minX());
        }

        if (request.minY() != null) {
            worldBuilder.setMinY(request.minY());
        }

        if (request.maxX() != null) {
            worldBuilder.setMaxX(request.maxX());
        }

        if (request.maxY() != null) {
            worldBuilder.setMaxY(request.maxY());
        }

        if (request.aliveCells() != null) {
            worldBuilder.setAliveCells(
                    request.aliveCells().stream()
                            .map(cell -> new WorldCell(cell.x(), cell.y(), 0))
                            .collect(Collectors.toSet())
            );
        }

        if (Boolean.TRUE.equals(request.randomizeAliveCells())) {
            worldBuilder.randomizeAliveCells();
        }

        var uuid = UUID.randomUUID().toString();

        worlds.put(uuid, worldBuilder.build());

        return new CreateWorldResponse(uuid);
    }

    public Map<String, World> getWorlds() {

        return Collections.unmodifiableMap(worlds);
    }

    public void destroyWorld(String uuid) {

        worlds.remove(uuid);
    }

    public GetWorldResponse processGetWorldRequest(String uuid) {

        var world = worlds.get(uuid);

        return new GetWorldResponse(
                world.getMinX(),
                world.getMinY(),
                world.getMaxX(),
                world.getMaxY(),
                world.getGeneration(),
                world.currentGenerationCells().parallelStream()
                        .map(cell -> new ActiveCell(cell.x(), cell.y()))
                        .collect(Collectors.toSet())
        );
    }

    public String processGetWorldAsTextRequest(String uuid) {

        return worlds.get(uuid).toString();
    }
}
