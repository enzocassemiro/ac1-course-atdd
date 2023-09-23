package com.arula.Cursos.exceptions;

import java.util.Arrays;
import java.util.stream.Collectors;

public class NotFoundException extends RuntimeException {

    public NotFoundException(Long... ids) {
        super("Could not find any record with the following ID(s): " +
                Arrays.stream(ids)
                        .map(Object::toString)
                        .collect(Collectors.joining(", ")));
    }
}
