package com.fichier.traitement.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AppUserDto {
    private Long id;
    private String nom;

    private String email;
    @JsonProperty(access = JsonProperty.Access.READ_WRITE)
    private String password;
    private String role;
}
