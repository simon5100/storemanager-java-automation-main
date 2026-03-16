package org.example.backend.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {

    private String name;

    private String email;

    private String password;

    public static RegisterRequest generate() {

        return RegisterRequest.builder()
                .name("user_" + System.currentTimeMillis())
                .email(System.currentTimeMillis() + "@mail.com")
                .password("pass_" + System.currentTimeMillis())
                .build();
    }
}