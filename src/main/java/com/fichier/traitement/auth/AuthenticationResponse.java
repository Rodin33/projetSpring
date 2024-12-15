package com.fichier.traitement.auth;

import com.fichier.traitement.dtos.AppUserResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {
    private String token;

    private AppUserResponse appUser;


}
