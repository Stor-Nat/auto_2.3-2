package ru.netology;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

class RegistrationPage {
    private String login;
    private String password;
    private String status;
}
