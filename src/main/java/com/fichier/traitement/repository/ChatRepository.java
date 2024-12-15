package com.fichier.traitement.repository;

import com.fichier.traitement.entity.AppUser;
import com.fichier.traitement.entity.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ChatRepository extends JpaRepository<Chat,Long> {
    List<Chat> findByUserDesAndUserExpAndStatusExp(AppUser des, AppUser exp, Integer status);
    @Query("SELECT c.ides,sum(c.statusExp) FROM Chat c WHERE c.userExp=:userDes AND c.statusExp=1 GROUP BY c.ides")
    List<Object[]> listMessageLength(@Param("userDes") AppUser userDes);
    List<Chat> findByIdesAndIdExpAndStatusExp(Long idDes,Long idExp,Integer status);

}
