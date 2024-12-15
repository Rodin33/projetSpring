package com.fichier.traitement.web;

import com.fichier.traitement.dtos.*;
import com.fichier.traitement.entity.AppUser;
import com.fichier.traitement.entity.Chat;
import com.fichier.traitement.entity.Chatg;
import com.fichier.traitement.entity.GroupByChat;
import com.fichier.traitement.repository.AppUserRepository;
import com.fichier.traitement.service.ChatService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin("*")
@RequestMapping("/api/v1")
public class ChatController {
    private ChatService chatService;
    private AppUserRepository userRepository;
    @PostMapping("/save_Chat")
    public Chat saveChat(@RequestBody ChatRequest chatRequest){
        return chatService.saveChat(chatRequest);
    }
    @PostMapping("/save_ChatGroup")
    public Chatg saveChatGroup(@RequestBody ChatGroupRequest chatRequest){
        return chatService.saveChatg(chatRequest);
    }
    @PostMapping("/save_Group")
    public GroupByChat saveGroup(@RequestBody GroupChatRequest request){
        return chatService.saveGroup(request);
    }
    @PostMapping("/list_Chat")
    public List<Chat> listChat(@RequestBody GetChat getChat){
        return chatService.listChat(getChat.getIdDes(),getChat.getIdExp());
    }
    @PostMapping("/add_UserByGroup")
    public ResponseEntity<String> addUserByGroup(@RequestBody AddUserByGroup request){
        String response= chatService.addUserByGroup(request.getIdGroup(),request.getIdUser());
        return ResponseEntity.ok().body(response);
    }
    @GetMapping("/list_Chats")
    public List<Chat> listChats(){
        return chatService.listChats();
    }
    @GetMapping("/list_ChatsGroup")
    public List<Chatg> listChatGroup(){
        return chatService.listChatGroups();
    }
    @GetMapping("/list_ChatsByGroup/{idGroup}")
    public List<Chatg> listChaBytGroup(@PathVariable("idGroup")Long idGroup){
        return chatService.listChatByGroups(idGroup);
    }
    @GetMapping("/list_Group")
    public List<GroupByChat> listGroup(){
        return chatService.listGroup();
    }

    @GetMapping("/list_userByChat/{id}")
    public List<AppUser> listUserByGroup(@PathVariable("id")Long id){
        return chatService.listUserByGroup(id);
    }
    @GetMapping("/list_userNoByChat/{id}")
    public List<AppUser> listUserNoByGroup(@PathVariable("id")Long id){
        return chatService.listUserNoIsGroup(id);
    }
    @GetMapping("/list_UserIsLength/{id}")
    public List<ListUserByLength> listUserByLength(@PathVariable("id")Long id){
        return chatService.ListUserByLength(id);
    }
    @GetMapping("/modifierStatMessage")
    public void mod(@RequestParam("idDes")Long idDes,@RequestParam("idExp")Long idExp){
        chatService.mofidifier(idDes,idExp);
    }

}
