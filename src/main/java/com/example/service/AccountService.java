package com.example.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Account;
import com.example.exception.UserNotFoundException;
import com.example.exception.UsernameAlreadyExistsException;
import com.example.repository.AccountRepository;


@Service
public class AccountService {
    @Autowired
    AccountRepository accountRepository;


    public  Account registerUser(Account account){

        if (account.getPassword().length() < 4){
            throw new IllegalArgumentException("Password length must be greater then 3");
        }

        if (account.getUsername().isBlank()){
            throw new IllegalArgumentException("User must not be empty");
        }
        if(accountRepository.findByUsername(account.getUsername()).isPresent()){
            throw new UsernameAlreadyExistsException(account.getUsername());
        }
        return accountRepository.save(account);
    }

    public Account loginUser (Account account){
        Optional <Account> tempAccount = accountRepository.findByUsernameAndPassword( account.getUsername(),account.getPassword());
        if (tempAccount.isPresent()){
            return tempAccount.get();
        }
        throw new UserNotFoundException();
    }

    


    
    
}
