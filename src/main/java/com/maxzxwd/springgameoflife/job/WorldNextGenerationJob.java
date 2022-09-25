package com.maxzxwd.springgameoflife.job;

import com.maxzxwd.springgameoflife.Application;
import com.maxzxwd.springgameoflife.service.GameOfLifeService;
import com.maxzxwd.springgameoflife.world.World;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class WorldNextGenerationJob {

    private final static Logger logger = LoggerFactory.getLogger(Application.class);

    @Autowired
    GameOfLifeService gameOfLifeService;

    @Scheduled(fixedRate = 1, timeUnit = TimeUnit.SECONDS)
    public void calculateNextGenerations() {

        var worlds = gameOfLifeService.getWorlds().values();

        long startTime = System.currentTimeMillis();
        gameOfLifeService.getWorlds().values().parallelStream().forEach(World::nextGeneration);
        long endTime = System.currentTimeMillis();

        logger.info("Processed {} worlds in {}ms", worlds.size(), endTime - startTime);
    }
}
