package org.easyCargoProject.org.database;

import org.easyCargoProject.org.entity.Cargo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CargoRepository extends JpaRepository<Cargo, Integer> {
    Cargo findByName(String name);
}
