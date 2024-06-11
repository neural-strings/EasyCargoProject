package org.easyCargoProject.org;

import org.easyCargoProject.org.database.CarRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestJpa {
    @Autowired
    CarRepository carRepository;

    @Test
    void test() {
        System.out.println(carRepository);
    }
}
