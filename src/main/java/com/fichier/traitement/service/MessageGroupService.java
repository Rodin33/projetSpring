package com.fichier.traitement.service;


import com.fichier.traitement.dtos.MessageGroupRequest;
import com.fichier.traitement.entity.Message;
import com.fichier.traitement.entity.MessageGroup;

import java.util.List;

public interface MessageGroupService {
    Long saveMessageGroup(MessageGroupRequest request);
    List<MessageGroup> listeMessageGroup();
}
