package org.easyCargoProject.org.rest.restExeption;

public class CarNotFoundException extends RuntimeException {

    public CarNotFoundException(String name) {
        super("could not found " + name);
    }
}
