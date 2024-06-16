package org.easyCargoProject.org.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "car_model")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    protected Integer id;

    @Column(name = "chat_id")
    protected  Long chatId;

    @Column(name = "name")
    protected String name;

    @Column(name = "cargo_width")
    protected int cargoWidth;

    @Column(name = "cargo_height")
    protected int cargoHeight;

    public Car(
            String name,
            int cargoWidth,
            int cargoHeight,
            Long chatId
    )
    {
        this.name = name;
        this.cargoWidth = cargoWidth;
        this.cargoHeight = cargoHeight;
        this.chatId = chatId;
    }
}
