package br.com.ota.backend.model.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class MessageResponse {

    @Getter private String message;
    @Getter private Long id;

    public MessageResponse(String message) {
        this.message = message;
    }
}
