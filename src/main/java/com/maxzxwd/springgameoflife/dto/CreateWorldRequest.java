package com.maxzxwd.springgameoflife.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.lang.Nullable;
import java.util.Set;

public record CreateWorldRequest(
        @Schema(example = "0")
        @Nullable
        Long minX,

        @Schema(example = "0")
        @Nullable
        Long minY,

        @Schema(example = "9")
        @Nullable
        Long maxX,

        @Schema(example = "9")
        @Nullable
        Long maxY,

        @Schema(example = "true")
        @Nullable
        Boolean randomizeAliveCells,

        @Schema(example = "null")
        @Nullable
        Set<ActiveCell> aliveCells
) { }