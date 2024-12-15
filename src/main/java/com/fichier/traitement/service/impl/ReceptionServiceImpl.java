package com.fichier.traitement.service.impl;

import com.fichier.traitement.dtos.ReceptionRequest;
import com.fichier.traitement.entity.AppUser;
import com.fichier.traitement.entity.OperationEntree;
import com.fichier.traitement.entity.Reception;
import com.fichier.traitement.repository.AppUserRepository;
import com.fichier.traitement.repository.OperationEntreeRepository;
import com.fichier.traitement.repository.ReceptionRepository;
import com.fichier.traitement.service.ReceptionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class ReceptionServiceImpl implements ReceptionService {

    private AppUserRepository appUserRepository;
    private ReceptionRepository receptionRepository;
    private OperationEntreeRepository operationEntreeRepository;

    @Override
    public Reception  saveReception(ReceptionRequest request) {
        Reception reception=new Reception();
        LocalDateTime now=LocalDateTime.now(ZoneId.of("Indian/Antananarivo"));
        reception.setDateReception(""+now);
        AppUser user=appUserRepository.findById(request.getIdUser()).get();
        reception.setAppUser(user);
        System.out.println(reception);
        OperationEntree operationEntree=operationEntreeRepository.findById(request.getIdOperation()).get();
        reception.setOperationEntree(operationEntree);
        reception.setTaches(request.getIdOperation());
        operationEntree.setStatus("En cours");
        Reception sc=receptionRepository.save(reception);
        return sc;

    }

    @Override
    public List<Reception> listeReception() {
        return receptionRepository.findAll();    }

    @Override
    public Reception receptionClient(Long id) {
        Reception v=receptionRepository.findById(id).get();
        return v;
    }

    @Override
    public List<Reception> findByAppUser(Long idAppUser) {
        AppUser appUser=appUserRepository.findById(idAppUser).get();
        return receptionRepository.findByAppUser(appUser);
    }
}
