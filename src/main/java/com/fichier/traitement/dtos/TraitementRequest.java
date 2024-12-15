package com.fichier.traitement.dtos;

import lombok.Data;

@Data
public class TraitementRequest {
    private Long idReception;
    private String message;
    private Long mot;
    private Long page;
    private Long duree;
}
