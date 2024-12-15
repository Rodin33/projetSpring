package com.fichier.traitement.service;

import com.fichier.traitement.dtos.MessageRequest;
import com.fichier.traitement.entity.Message;
import com.fichier.traitement.entity.OperationEntree;

import java.util.List;

public interface MessageService {
    Long saveMessage(MessageRequest request);
    List<Message> listeOMessage();
}
