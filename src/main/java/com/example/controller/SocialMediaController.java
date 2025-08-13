package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Account;
import com.example.entity.Message;
import com.example.service.AccountService;
import com.example.service.MessageService;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
@RestController
public class SocialMediaController {
    AccountService accountService;
    MessageService messageService;

    @Autowired
    public SocialMediaController(AccountService accountService, MessageService messageService){
        this.accountService = accountService;
        this.messageService = messageService;
    }

    @PostMapping("/register")
    public Account postRegister(@RequestBody Account account){
        return accountService.registerUser(account);
    }

    @PostMapping("/login")
    public Account postLogin(@RequestBody Account account){
        return accountService.loginUser(account);
    }

    @PostMapping("/messages")
    public Message postMessage(@RequestBody Message message){
        return messageService.createMessage(message);

    }

    @GetMapping("/messages")
    public List<Message> getMessages(){
        return messageService.getAllMessages();

    }

    @GetMapping("/messages/{messageId}")
    public Message getMessageById(@PathVariable Integer messageId){

        return messageService.getMessageById(messageId);
    }
    
    @DeleteMapping("/messages/{messageId}")
    public Integer deleteMessageById(@PathVariable Integer messageId){

        return messageService.deleteMessageById(messageId);
    }

    @GetMapping("/accounts/{accountId}/messages")
    public List<Message> getAllMessagesByAccountId(@PathVariable Integer accountId){
        return messageService.getAllMessagesByAccountId(accountId);

    }

   @PatchMapping("/messages/{messageId}")
   public Integer postMessageById(@PathVariable Integer messageId, @RequestBody Message message){
        return messageService.updateMessageById(messageId, message);

   }


}
