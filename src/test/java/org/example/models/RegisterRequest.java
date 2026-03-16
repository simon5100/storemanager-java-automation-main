package org.example.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// Аннотации Lombok для автоматической генерации геттеров, сеттеров, конструкторов и других методов
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {

    private String name;

    private String email;

    private String password;


    // Поля класса, представляющие данные для регистрации пользователя

    // Метод для генерации экземпляра RegisterRequest с случайными данными
    // Используется паттерн Builder для создания объекта
    // email генерируется как текущее время в миллисекундах + "@email.com"
    // name генерируется как "user_" + текущее время в миллисекундах
    // password генерируется как "pass_" + текущее время в миллисекундах
    public static RegisterRequest generate() {

        return RegisterRequest.builder()
                .name("user_" + System.currentTimeMillis())
                .email(System.currentTimeMillis() + "@mail.com")
                .password("pass_" + System.currentTimeMillis())
                .build();
    }
}

