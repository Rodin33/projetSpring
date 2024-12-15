package com.fichier.traitement.repository;

import com.fichier.traitement.entity.AppUser;
import com.fichier.traitement.entity.OperationEntree;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OperationEntreeRepository extends JpaRepository<OperationEntree,Long> {
    @Query("SELECT MAX(e.id) FROM OperationEntree e") Long findMaxId();

    List<OperationEntree> findByAppUser(AppUser appUser);

}
