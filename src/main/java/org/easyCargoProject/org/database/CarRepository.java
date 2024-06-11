package org.easyCargoProject.org.database;

import org.easyCargoProject.org.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<Car, Integer> {
    Car findByName(String name);
}
