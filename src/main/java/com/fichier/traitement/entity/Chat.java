package com.fichier.traitement.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.catalina.User;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private AppUser userDes;
    @ManyToOne
    private  AppUser userExp;
    private Long idExp;
    private String message;
    private Date dateMsg;
    private Long ides;
    private Integer statusDes;
    private Integer statusExp;
}
