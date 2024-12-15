package com.fichier.traitement.dtos;

import lombok.Data;
@Data
public class MessageGroupRequest {
    private Long id;
    private Long userInit;
    private String groupe;
    private String message;
}
