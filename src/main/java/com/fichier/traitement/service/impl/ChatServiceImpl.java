package com.fichier.traitement.service.impl;


import com.fichier.traitement.dtos.ChatGroupRequest;
import com.fichier.traitement.dtos.ChatRequest;
import com.fichier.traitement.dtos.GroupChatRequest;
import com.fichier.traitement.dtos.ListUserByLength;
import com.fichier.traitement.entity.*;
import com.fichier.traitement.repository.AppUserRepository;
import com.fichier.traitement.repository.ChatRepository;
import com.fichier.traitement.repository.ChatgRepository;
import com.fichier.traitement.repository.GroupByChatRepository;
import com.fichier.traitement.service.ChatService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.*;

@Service
@Transactional
@AllArgsConstructor
public class ChatServiceImpl implements ChatService {
    private ChatRepository repository;
    private AppUserRepository userRepository;
    private GroupByChatRepository groupChatRepository;
    private ChatgRepository chatgRepository;
    @Override
    public Chat saveChat(ChatRequest chatRequest) {
        Chat chat=new Chat();
        chat.setMessage(chatRequest.getMessage());
        chat.setStatusDes(1);
        chat.setStatusExp(1);
        chat.setDateMsg(chatRequest.getDateMsg());
        chat.setIdExp(chatRequest.getIdExp());
        AppUser userDes=userRepository.findById(chatRequest.getIdUserDes()).get();
        AppUser userExp=userRepository.findById(chatRequest.getIdExp()).get();
        chat.setUserDes(userDes);
        chat.setIdes(chatRequest.getIdUserDes());
        chat.setUserExp(userExp);

        return repository.save(chat);
    }

    @Override
    public List<Chat> listChat(Long idDes,Long idExp) {
        AppUser userDes=userRepository.findById(idDes).get();
        AppUser userExp=userRepository.findById(idExp).get();
        return repository.findByUserDesAndUserExpAndStatusExp(userDes,userExp,0);
    }

    @Override
    public List<Chat> listChats() {

        return repository.findAll();
    }

    @Override
    public List<Chatg> listChatGroups() {
        return chatgRepository.findAll();
    }

    @Override
    public List<Chatg> listChatByGroups(Long idGroup) {
        return chatgRepository.findByIdGroup(idGroup);
    }

    @Override
    public List<GroupByChat> listGroup() {
        return groupChatRepository.findAll();
    }


    @Override
    public GroupByChat saveGroup(GroupChatRequest request) {
        GroupByChat groupChat=new GroupByChat();
        groupChat.setNom(request.getNom());
        return groupChatRepository.save(groupChat);
    }

    @Override
    public Chatg saveChatg(ChatGroupRequest request) {
        Chatg chat=new Chatg();
        chat.setDateMsg(request.getDateMsg());
        chat.setIdGroup(request.getIdGroup());
        chat.setMessage(request.getMessage());
        AppUser user=userRepository.findById(request.getIdUser()).get();
        chat.setUser(user);
        return chatgRepository.save(chat);
    }

    @Override
    public String addUserByGroup(Long idGroup, Long idUser) {
        AppUser user=userRepository.findById(idUser).get();
        GroupByChat groupByChat=groupChatRepository.findById(idGroup).get();
        groupByChat.getUsers().add(user);
        return "Operation effectuer";
    }

    @Override
    public List<AppUser> listUserByGroup(Long id) {
        GroupByChat group=groupChatRepository.findById(id).get();
        List<AppUser> list= group.getUsers();
        return list;
    }

    @Override
    public List<AppUser> listUserNoIsGroup(Long id) {
        GroupByChat group=groupChatRepository.findById(id).get();
        List<AppUser> listIsGroup= group.getUsers();
        List<AppUser> listUser=userRepository.findAll();
        Set<Long> idSet = new HashSet<>();
        List<AppUser> differenceUser = new ArrayList<>();

        // Ajouter tous les identifiants de listIsGroup dans le Set
        for (AppUser user : listIsGroup) {
            idSet.add(user.getId());
        }

        // Parcourir listUser et ajouter les objets dont l'identifiant n'est pas présent dans le Set
        for (AppUser user : listUser) {
            if (!idSet.contains(user.getId())) {
                differenceUser.add(user);
            }
        }
        return differenceUser;
    }

    @Override
    public List<ListUserByLength> ListUserByLength(Long id) {
        AppUser userDes=userRepository.findById(id).get();
        List<Object[]> results=repository.listMessageLength(userDes);
        List<ListUserByLength> listUserByLengthss=new ArrayList<>();
        for (Object[] resultat:results){
            Long idExp=(Long) resultat[0];
            Long sumMots=(Long) resultat[1];
            ListUserByLength listUserByLength=new ListUserByLength();
            listUserByLength.setIdExp(idExp);
            listUserByLength.setSumMessage(sumMots);
            listUserByLengthss.add(listUserByLength);
        }
        return listUserByLengthss;
    }

    @Override
    public void mofidifier(Long idDes, Long idExp) {
        List<Chat> listNonVue=repository.findByIdesAndIdExpAndStatusExp(idDes,idExp,1);
        listNonVue.forEach(chat -> {
            chat.setStatusExp(0);
            repository.save(chat);
        });
    }


}
