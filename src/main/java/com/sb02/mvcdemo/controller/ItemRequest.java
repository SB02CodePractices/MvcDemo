package com.sb02.mvcdemo.controller;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ItemRequest(
        @NotBlank String name,
        @NotNull @Min(0) Long price
) {
}
