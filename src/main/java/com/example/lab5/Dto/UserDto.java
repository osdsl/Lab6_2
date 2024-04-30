package com.example.lab5.Dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@Data
public class UserDto {

    public interface registration{}
    public interface login{}

    @NotBlank(groups = {registration.class, login.class}, message = "Поле имени не может быть пустым")
    @Size(min = 4, groups = {registration.class}, message = "Логин должен содержать минимум 4 символа")
    private String username;

    @NotBlank(groups = {registration.class, login.class}, message = "Поле пароля не может быть пустым")
    @Size(min = 2, groups = {registration.class, login.class},  message = "Минимум 2 символа в пароле!")
    private String password;


    @NotBlank(groups = {registration.class}, message = "")
    private String passwordConfirm;
}
