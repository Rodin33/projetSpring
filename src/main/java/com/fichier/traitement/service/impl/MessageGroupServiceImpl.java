package com.fichier.traitement.service.impl;

import com.fichier.traitement.dtos.MessageGroupRequest;
import com.fichier.traitement.entity.MessageGroup;
import com.fichier.traitement.repository.MessageGroupRepository;
import com.fichier.traitement.service.MessageGroupService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class MessageGroupServiceImpl implements MessageGroupService {
    private MessageGroupRepository messageGroupRepository;
    @Override
    public Long saveMessageGroup(MessageGroupRequest request) {
        MessageGroup messageGroup=new MessageGroup();
        messageGroup.setDateMessage(new Date());
        messageGroup.setMessage(request.getMessage());
        messageGroup.setUserInit(request.getUserInit());
        messageGroup.setGroupe(request.getGroupe());
        MessageGroup saveGroup=messageGroupRepository.save(messageGroup);
        return saveGroup.getId();
    }

    @Override
    public List<MessageGroup> listeMessageGroup() {
        return messageGroupRepository.findAll();
    }
}
