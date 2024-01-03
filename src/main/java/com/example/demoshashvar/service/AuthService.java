package com.example.demoshashvar.service;

import com.example.demoshashvar.dto.AccountDTO;
import com.example.demoshashvar.entity.User;
import com.example.demoshashvar.repo.UserRepositary;
import com.example.demoshashvar.util.ResultMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepositary userRepositary;

    public ResultMessage createAccount(AccountDTO accountDTO) {
        boolean r = checkDTO(accountDTO);
        if (!r) {
            return new ResultMessage(false, "Fields wrong");
        }
        if (userRepositary.existsByFirstNameAndSurName(accountDTO.getFirstName(),
                accountDTO.getSurName())) {
            return new ResultMessage(false, "FIrstName and SurnameLi user exist");
        }
        User account = mapper(accountDTO);
        userRepositary.save(account);
        return new ResultMessage(true, accountDTO);
    }

    private User mapper(AccountDTO accountDTO) {
        User user = new User();
        user.setPassword(accountDTO.getPassword());
        user.setFirstName(accountDTO.getFirstName());
        user.setSurName(accountDTO.getSurName());
        return user;
    }

    private boolean checkDTO(AccountDTO accountDTO) {
        if (accountDTO.getFirstName().isBlank()) return false;
        if (accountDTO.getSurName().isBlank()) return false;
        return !accountDTO.getPassword().isBlank();
    }

    public ResultMessage getUsers() {
        List<User> all = userRepositary.findAll();
        if (all.isEmpty()) {
            return new ResultMessage(false, "Users not exists");
        } else return new ResultMessage(true, all);
    }

    public ResultMessage getUser(UUID id) {
        Optional<User> byId = userRepositary.findById(id);
        User user = byId.orElse(null);
        if (user == null) {
            return new ResultMessage(false, "Bunaqa user yo'q");
        } else return new ResultMessage(true, user);
    }

    public ResultMessage isBlocked(UUID id) {
        Optional<User> byId = userRepositary.findById(id);
        User user = byId.orElse(null);
        if (user == null) {
            return new ResultMessage(false, "Bunaqa user yo'q");
        } else {
            user.setBlocked(true);
            userRepositary.save(user);
            return new ResultMessage(true, id + " li User bloklandi");
        }
    }
}
