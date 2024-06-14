package org.easyCargoProject.org.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "cargo_model")
public class Cargo {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;

    @Column(name = "scheme")
    protected String scheme;

    @Column(name = "name")
    protected String name;

    @Column(name = "chat_id")
    protected  Long chatId;

    @Column(name = "car_id")
    protected Integer carId;

    public Cargo(
            String name,
            String scheme,
            Long chatId,
            Integer carId
    )
    {
        this.name = name;
        this.scheme = scheme;
        this.chatId = chatId;
        this.carId = carId;
    }
}
