package br.com.ota.backend.model.vo;

import br.com.ota.backend.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class LoginResponse {

    private User user;

    private String token;

}
