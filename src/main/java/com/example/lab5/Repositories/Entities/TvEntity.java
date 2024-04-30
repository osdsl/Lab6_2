package com.example.lab5.Repositories.Entities;




import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * Класс телевизора
 */
@Entity
@Table(name = "tv")
@NoArgsConstructor
@Data
public class TvEntity {
    @Setter
    @Getter

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @PositiveOrZero(message = "Здесь должно быть число 0 или больше")
    private int id;



    @Column(name = "product_name")
    @NotBlank(message = "Поле не должно быть пустым!")
    @Size(min = 1, max = 30, message = "Размер поля должен быть от 2 до 30 символов")
    private String name;


    @Column(name = "type")
    @NotBlank(message = "Поле не должно быть пустым!")
    @Size(min = 1, max = 30, message = "Размер поля должен быть от 2 до 30 символов")
    private String type;

    @Column(name = "brand")
    @NotBlank(message = "Поле не должно быть пустым!")
    @Size(min = 1, max = 30)
    private String brand;

    @Column(name = "screen_diagonal")
    @PositiveOrZero(message = "Здесь должно быть число 0 или больше")
    private int size;

    @Column(name = "price")
    @PositiveOrZero(message = "Здесь должно быть число 0 или больше")
    private double price;

    @Column(name = "quantity")
    @PositiveOrZero(message = "Здесь должно быть число 0 или больше")
    private int quantity;

    public TvEntity(int id, String name, String type, String brand, int size, double price, int quantity) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.brand = brand;
        this.size = size;
        this.price = price;
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return String.format(
                "ID: %d, Brand: %s, Type: %s, Product Name: %s, Screen diagonal: %d, Price: %.2f ₽, Quantity: %d",
                id, name, type, brand, size, price, quantity
        );
    }
}