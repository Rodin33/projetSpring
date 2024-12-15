package com.fichier.traitement.web;

import com.fichier.traitement.dtos.MessageRequest;
import com.fichier.traitement.entity.Message;
import com.fichier.traitement.repository.MessageRepository;
import com.fichier.traitement.service.MessageService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin("*")
@RequestMapping("/api/v1")
public class MessageController {
    private MessageService service;
    @PostMapping("/createMessage")
    public Long saveMessage(@RequestBody MessageRequest request){

        return service.saveMessage(request);
    }
    @GetMapping("/list_message")
    public List<Message> getMessaqge(){
        return service.listeOMessage();
    }

}
