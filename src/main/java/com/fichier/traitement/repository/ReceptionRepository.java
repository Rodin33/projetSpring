package com.fichier.traitement.repository;

import com.fichier.traitement.entity.AppUser;
import com.fichier.traitement.entity.OperationEntree;
import com.fichier.traitement.entity.Reception;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReceptionRepository extends JpaRepository<Reception,Long> {
    List<Reception> findByAppUser(AppUser appUser);
}
