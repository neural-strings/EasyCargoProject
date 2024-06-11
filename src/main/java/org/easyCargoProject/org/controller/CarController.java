package org.easyCargoProject.org.controller;

import org.easyCargoProject.org.entity.Car;
import org.easyCargoProject.org.database.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class CarController {
    @Autowired
    CarRepository carRepository;
}
