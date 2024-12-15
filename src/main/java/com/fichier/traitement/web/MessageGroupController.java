package com.fichier.traitement.web;

import com.fichier.traitement.dtos.MessageGroupRequest;
import com.fichier.traitement.dtos.MessageRequest;
import com.fichier.traitement.entity.Message;
import com.fichier.traitement.entity.MessageGroup;
import com.fichier.traitement.service.MessageGroupService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin("*")
@RequestMapping("/api/v1")
public class MessageGroupController {
    private MessageGroupService service;
    @PostMapping("/createMessageGroup")
    public Long saveMessageGroup(@RequestBody MessageGroupRequest request){

        return service.saveMessageGroup(request);
    }
    @GetMapping("/list_messageGroup")
    public List<MessageGroup> getGoup(){
        return service.listeMessageGroup();
    }

}

