package com.fichier.traitement.repository;

import com.fichier.traitement.entity.Traitement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TraitementRepository extends JpaRepository <Traitement,Long> {

}
