package org.easyCargoProject.org.service;

import org.easyCargoProject.org.database.CarRepository;
import org.easyCargoProject.org.entity.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarService {

    @Autowired
    private CarRepository carRepository;

    @Autowired
    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public void saveCar(Car car){
        carRepository.save(car);
    }
}
