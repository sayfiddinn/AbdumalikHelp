package com.example.demoshashvar.controller;


import com.example.demoshashvar.dto.AccountDTO;
import com.example.demoshashvar.service.AuthService;
import com.example.demoshashvar.util.ResultMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequestMapping("api/account")
@RestController
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/create")
    public HttpEntity<?> createAccount(@RequestBody AccountDTO accountDTO) {
        ResultMessage resultMessage = authService.createAccount(accountDTO);
        return ResponseEntity.status(
                resultMessage.getSuccess() ?
                        HttpStatus.ACCEPTED :
                        HttpStatus.CONFLICT
        ).body(resultMessage);
    }

    @GetMapping("/users")
    public HttpEntity<?> getUsers() {
        ResultMessage resultMessage = authService.getUsers();
        return ResponseEntity.status(
                resultMessage.getSuccess() ?
                        HttpStatus.OK :
                        HttpStatus.NOT_FOUND
        ).body(resultMessage);
    }

    @GetMapping("/user/{Id}")
    public HttpEntity<?> getUser(@PathVariable UUID Id) {
        ResultMessage resultMessage = authService.getUser(Id);
        return ResponseEntity.status(
                resultMessage.getSuccess() ?
                        HttpStatus.OK :
                        HttpStatus.NOT_FOUND
        ).body(resultMessage);
    }
    @PutMapping("/user/{Id}")
    public HttpEntity<?> UserBlocked(@PathVariable UUID Id){
        ResultMessage resultMessage = authService.isBlocked(Id);
        return ResponseEntity.status(
                resultMessage.getSuccess() ?
                        HttpStatus.OK :
                        HttpStatus.NOT_FOUND
        ).body(resultMessage);
    }
}
