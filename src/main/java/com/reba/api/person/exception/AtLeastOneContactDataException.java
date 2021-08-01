package com.reba.api.person.exception;

import java.util.InvalidPropertiesFormatException;

public class AtLeastOneContactDataException extends InvalidPropertiesFormatException {
    public AtLeastOneContactDataException() {
        super("Ingresar al menos un dato de contacto");
    }
}
