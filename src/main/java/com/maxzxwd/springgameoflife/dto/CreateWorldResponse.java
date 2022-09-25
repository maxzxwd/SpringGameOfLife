package com.maxzxwd.springgameoflife.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import org.jetbrains.annotations.NotNull;

public record CreateWorldResponse(
        @Schema(example = "f926c101-e5cb-40c8-a4db-bebbcea1938b")
        @NotNull
        String uuid
) {}