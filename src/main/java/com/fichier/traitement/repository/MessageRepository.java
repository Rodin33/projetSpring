package com.fichier.traitement.repository;

import com.fichier.traitement.entity.AppUser;
import com.fichier.traitement.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository  <Message,Long>{
}
