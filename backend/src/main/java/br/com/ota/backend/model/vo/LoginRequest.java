package br.com.ota.backend.model.vo;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

public class LoginRequest {

    @NotBlank
    @Getter private String username;

    @NotBlank
    @Getter private String password;

}
