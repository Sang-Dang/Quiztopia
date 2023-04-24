package com.fpt.assignment.util;

import java.util.UUID;

import com.fpt.assignment.exception.checked.validate.UUIDParseException;

public class Converter {
    private Converter() {}

    public static UUID toUUID(String uuid) throws UUIDParseException {
        try {
        return UUID.fromString(uuid);
        } catch (IllegalArgumentException e) {
            throw new UUIDParseException(uuid);
        }
    }
}
