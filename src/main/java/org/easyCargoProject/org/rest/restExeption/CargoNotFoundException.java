package org.easyCargoProject.org.rest.restExeption;

public class CargoNotFoundException extends RuntimeException {

    public CargoNotFoundException(String name) {
        super("could not found " + name);
    }
}
