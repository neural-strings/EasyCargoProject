package org.easyCargoProject.org;

import org.easyCargoProject.org.database.CarRepository;
import org.easyCargoProject.org.entity.Car;
import org.easyCargoProject.org.service.CarService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CarServiceTest {

    @Mock
    private CarRepository carRepository;

    @InjectMocks
    private CarService carService;

    private Car car;

    @BeforeEach
    public void setUp() {
        car = new Car("CarName", 2, 2, 1L);
    }

    @Test
    public void testSaveCar() {
        carService.saveCar(car);
    }
}
