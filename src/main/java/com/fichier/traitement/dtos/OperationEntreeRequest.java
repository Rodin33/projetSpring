package com.fichier.traitement.dtos;

import lombok.Data;

import java.util.Date;

@Data
public class OperationEntreeRequest {

    private Long id;
    private String description;
    private  String priority;
    private String nameDoc;
    private String nameAudio;
    private Date dateOperation;
    private String nomDocument;
    private String nomAudio;
    private Long idUser ;

}
