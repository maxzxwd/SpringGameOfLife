package com.maxzxwd.springgameoflife.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

public record GetWorldResponse(
        @Schema(example = "0")
        @NotNull
        Long minX,

        @Schema(example = "0")
        @NotNull
        Long minY,

        @Schema(example = "9")
        @NotNull
        Long maxX,

        @Schema(example = "9")
        @NotNull
        Long maxY,

        @Schema(example = "42")
        @NotNull
        Integer generation,

        @NotNull
        Set<ActiveCell> aliveCells
) {}