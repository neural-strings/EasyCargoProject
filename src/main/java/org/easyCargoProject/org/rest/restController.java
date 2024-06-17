package org.easyCargoProject.org.rest;

import org.easyCargoProject.org.database.CarRepository;
import org.easyCargoProject.org.database.CargoRepository;
import org.easyCargoProject.org.entity.Car;
import org.easyCargoProject.org.entity.Cargo;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;

import java.util.List;

@RestController
public class restController {

    private static Logger logger = LoggerFactory.getLogger(restController.class);

    private final CarRepository carRepository;
    private final CargoRepository cargoRepository;

    restController(CarRepository carRepository, CargoRepository cargoRepository1) {
        this.carRepository = carRepository;
        this.cargoRepository = cargoRepository1;
    }

    @GetMapping("/car")
    List<Car> allCar() {
        logger.info("get info about all car");
        return carRepository.findAll();
    }

    @GetMapping("/cargo")
    List<Cargo> allCargo() {
        logger.info("get info about all cargo");
        return cargoRepository.findAll();
    }

    @PostMapping("/car")
    Car newCar(@RequestBody Car newCar) {
        logger.info("Create new car" + newCar.toString());
        return carRepository.save(newCar);
    }

    @PostMapping("/cargo")
    Cargo newCargo(@RequestBody Cargo newCargo) {
        logger.info("Create new cargo" + newCargo.toString());
        return cargoRepository.save(newCargo);
    }

    @GetMapping("/car/name/{name}")
    Car oneCar(@PathVariable String name) {
        logger.info("get info about car with name" + name);
        return carRepository.findByName(name);
    }

    @GetMapping("/cargo/name/{name}")
    Cargo oneCargo(@PathVariable String name) {
        logger.info("get info about cargo with name" + name);
        return cargoRepository.findByName(name);
    }

    @DeleteMapping("/car/{id}")
    void deleteCar(@PathVariable Integer id) {
        logger.info("delete car with id" + id);
        carRepository.deleteById(id);
    }

    @DeleteMapping("/cargo/{id}")
    void deleteCargo(@PathVariable Integer id) {
        logger.info("delete cargo with id" + id);
        cargoRepository.deleteById(id);
    }
}
