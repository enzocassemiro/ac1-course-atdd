package com.arula.Cursos.enums;

import lombok.Getter;

@Getter
public enum StudentLevel {
    DEFAULT(3),
    PREMIUM(20),
    MENTOR(Integer.MAX_VALUE);

    private final Integer limit;

    StudentLevel(Integer limit) {
        this.limit = limit;
    }
}
