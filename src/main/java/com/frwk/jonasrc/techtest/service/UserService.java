package com.frwk.jonasrc.techtest.service;

import com.frwk.jonasrc.techtest.dto.UserDTO;
import com.frwk.jonasrc.techtest.exception.EmailExistsException;
import com.frwk.jonasrc.techtest.model.User;

import java.util.List;

public interface UserService {
    public List<User> findAll();
    public User findById(long id);
    public User create(UserDTO userDTO) throws EmailExistsException;
    public void deleteById(long id);
}
