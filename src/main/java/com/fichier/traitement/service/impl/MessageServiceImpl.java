package com.fichier.traitement.service.impl;

import com.fichier.traitement.dtos.MessageRequest;
import com.fichier.traitement.entity.Message;
import com.fichier.traitement.repository.MessageRepository;
import com.fichier.traitement.service.MessageService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class MessageServiceImpl implements MessageService {
    private MessageRepository messageRepository;
    @Override
    public Long saveMessage(MessageRequest request) {
        Message mess= new Message();
        mess.setDateMessage(new Date());
        mess.setUserInit(request.getUserInit());
        mess.setUserDest(request.getUserDest());
        mess.setMessage(request.getMessage());
        Message saveMess=messageRepository.save(mess);
        return saveMess.getId();
    }

    @Override
    public List<Message> listeOMessage() {
        return messageRepository.findAll();
    }
}
