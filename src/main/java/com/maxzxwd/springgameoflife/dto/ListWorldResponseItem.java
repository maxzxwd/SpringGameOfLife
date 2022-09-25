package com.maxzxwd.springgameoflife.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import org.jetbrains.annotations.NotNull;

public record ListWorldResponseItem(
        @Schema(example = "f926c101-e5cb-40c8-a4db-bebbcea1938b")
        @NotNull
        String uuid,

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

        @Schema(example = "6")
        @NotNull
        Integer aliveCount
) {}
