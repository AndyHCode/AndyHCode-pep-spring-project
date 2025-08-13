package com.example.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Message;
import com.example.repository.AccountRepository;
import com.example.repository.MessageRepository;


@Service
public class MessageService {
    
    MessageRepository messageRepository;
    AccountRepository accountRepository;
    @Autowired
    public MessageService(MessageRepository messageRepository, AccountRepository accountRepository){
        this.messageRepository = messageRepository;
        this.accountRepository = accountRepository;

    }

    public Message createMessage (Message message){
        if (message.getMessageText() == ""){
            throw new IllegalArgumentException("Message is empty");
        }
        if (message.getMessageText().length() > 255){
            throw new IllegalArgumentException("Message is too long");
        }
        if (accountRepository.findById(message.getPostedBy()).isEmpty()){
            throw new IllegalArgumentException("User does not exist");
        }
        return messageRepository.save(message);
    }

    public Message getMessageById(Integer messageId){
        Optional<Message> tempMessage = messageRepository.findById(messageId);
        if (tempMessage.isEmpty()){
            return null;
        }
        return tempMessage.get();


    }

    public Integer deleteMessageById(Integer messageId){
        if(messageRepository.findById(messageId).isEmpty()){
            return null;
        }
        messageRepository.deleteById(messageId);
        return Integer.valueOf(1);
    }

    public List<Message> getAllMessages(){
        return messageRepository.findAll();
    }

    public List<Message> getAllMessagesByAccountId(Integer accountId){
        return messageRepository.findByPostedBy(accountId);
    }

    public Integer updateMessageById(Integer messageId, Message message){
        Message tempMessage = getMessageById(messageId);
        if (tempMessage == null){
            throw new IllegalArgumentException("Message does not exist");
        }
        tempMessage.setMessageText(message.getMessageText());
        createMessage(tempMessage);
        
        return Integer.valueOf(1);
    }

}
