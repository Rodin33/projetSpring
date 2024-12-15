package com.fichier.traitement.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class OperationEntree {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private String codeTache;
    private Date dateOperation;
    private String nomDocument;
    private String nomAudio;
    private String status;
    private Integer mot;
    private Double duree;
    private String nameDoc;
    private String nameAudio;
    private  String priority;
    @ManyToOne
    private AppUser appUser;

}
