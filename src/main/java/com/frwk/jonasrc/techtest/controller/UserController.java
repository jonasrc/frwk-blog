package com.frwk.jonasrc.techtest.controller;

import com.frwk.jonasrc.techtest.dto.UserDTO;
import com.frwk.jonasrc.techtest.exception.EmailExistsException;
import com.frwk.jonasrc.techtest.model.User;
import com.frwk.jonasrc.techtest.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@Api(tags = "Usuários", value = "uookController", description="Cadastro e listagem de usuários do blog")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/users")
    @ApiOperation(value = "Buscar lista completa de usuários do blog")
    public ResponseEntity<List<UserDTO>> findAll() {
        List<User> userList = userService.findAll();
        List<UserDTO> userDTOList = userList.stream().map(this::convertToDTO).collect(Collectors.toList());
        return ResponseEntity.ok().body(userDTOList);
    }

    @PostMapping("/users")
    @ApiOperation(value = "Cadastro de novos usuários do blog")
    public ResponseEntity<UserDTO> create(@RequestBody UserDTO userDTO) throws EmailExistsException {
        User newUser = userService.create(userDTO);
        UserDTO newUserDTO = convertToDTO(newUser);
        return ResponseEntity.created(URI.create(newUserDTO.getId().toString())).body(newUserDTO);
    }

    private UserDTO convertToDTO(User user) {
        return modelMapper.map(user, UserDTO.class);
    }

    private User convertToEntity(UserDTO userDTO) {
        return modelMapper.map(userDTO, User.class);
    }
}
