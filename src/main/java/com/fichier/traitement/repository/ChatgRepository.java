package com.fichier.traitement.repository;

import com.fichier.traitement.entity.Chatg;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatgRepository extends JpaRepository<Chatg,Long> {
    List<Chatg> findByIdGroup(Long idGroup);
}
