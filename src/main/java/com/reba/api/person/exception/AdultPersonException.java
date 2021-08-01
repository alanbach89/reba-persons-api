package com.reba.api.person.exception;

import java.util.InvalidPropertiesFormatException;

public class AdultPersonException extends InvalidPropertiesFormatException {
    public AdultPersonException() {
        super("La persona debe tener al menos 18 años");
    }
}
