package com.maxzxwd.springgameoflife.controller;

import com.maxzxwd.springgameoflife.service.GameOfLifeService;
import com.maxzxwd.springgameoflife.dto.CreateWorldRequest;
import com.maxzxwd.springgameoflife.dto.CreateWorldResponse;
import com.maxzxwd.springgameoflife.dto.GetWorldResponse;
import com.maxzxwd.springgameoflife.dto.ListWorldResponseItem;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
public class GameOfLifeController {

    @Autowired
    GameOfLifeService gameOfLifeService;

    @Operation(summary = "Create game of life world")
    @PutMapping(value = "/world", consumes = MediaType.APPLICATION_JSON_VALUE)
    public CreateWorldResponse createWorld(@RequestBody CreateWorldRequest body) {

        return gameOfLifeService.processCreateWorldRequest(body);
    }

    @Operation(summary = "Destroy game of life world")
    @DeleteMapping(value = "/world/{uuid}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void destroyWorld(@PathVariable String uuid) {

        gameOfLifeService.destroyWorld(uuid);
    }

    @Operation(summary = "List of game of life worlds")
    @GetMapping(value = "/world/list")
    public List<ListWorldResponseItem> listWorld() {

        return gameOfLifeService.getWorlds().entrySet().parallelStream()
                .map(entry -> new ListWorldResponseItem(
                        entry.getKey(),
                        entry.getValue().getMinX(),
                        entry.getValue().getMinY(),
                        entry.getValue().getMaxX(),
                        entry.getValue().getMaxY(),
                        entry.getValue().getGeneration(),
                        entry.getValue().currentGenerationCells().size()
                ))
                .collect(Collectors.toList());
    }

    @Operation(summary = "Get game of life world")
    @GetMapping(value = "/world/{uuid}")
    public GetWorldResponse getWorld(@PathVariable String uuid) {

        return gameOfLifeService.processGetWorldRequest(uuid);
    }

    @Operation(summary = "Get game of life world in text format")
    @GetMapping(value = "/world/{uuid}", produces = MediaType.TEXT_PLAIN_VALUE)
    public String getWorldAsText(@PathVariable String uuid) {

        return gameOfLifeService.processGetWorldAsTextRequest(uuid);
    }
}
