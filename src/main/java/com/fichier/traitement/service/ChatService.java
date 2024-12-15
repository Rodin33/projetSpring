package com.fichier.traitement.service;


import com.fichier.traitement.dtos.ChatGroupRequest;
import com.fichier.traitement.dtos.ChatRequest;
import com.fichier.traitement.dtos.GroupChatRequest;
import com.fichier.traitement.dtos.ListUserByLength;
import com.fichier.traitement.entity.AppUser;
import com.fichier.traitement.entity.Chat;
import com.fichier.traitement.entity.Chatg;
import com.fichier.traitement.entity.GroupByChat;

import java.io.IOException;
import java.util.List;

public interface ChatService {
    Chat saveChat(ChatRequest chatRequest);
    List<Chat> listChat(Long idDes, Long idExp);
    List<Chat> listChats();
    List<Chatg> listChatGroups();
    List<Chatg> listChatByGroups(Long idGroup);
    List<GroupByChat> listGroup();
    GroupByChat saveGroup(GroupChatRequest request);
    Chatg saveChatg(ChatGroupRequest request);
    String addUserByGroup(Long idGroup,Long idUser);
    List<AppUser> listUserByGroup(Long id);
    List<AppUser> listUserNoIsGroup(Long id);
    List<ListUserByLength> ListUserByLength(Long id);
    void mofidifier(Long idDes,Long idExp);

}
