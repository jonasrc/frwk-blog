package com.frwk.jonasrc.techtest.controller;

import com.frwk.jonasrc.techtest.dto.PostDTO;
import com.frwk.jonasrc.techtest.dto.UserDTO;
import com.frwk.jonasrc.techtest.exception.EmailExistsException;
import com.frwk.jonasrc.techtest.exception.EmptyContentException;
import com.frwk.jonasrc.techtest.exception.UserNotFoundException;
import com.frwk.jonasrc.techtest.model.Post;
import com.frwk.jonasrc.techtest.model.User;
import com.frwk.jonasrc.techtest.service.PostService;
import com.frwk.jonasrc.techtest.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@Api(tags = "Postagens", value = "postController", description="Cadastro, listagem e deleção de postagens do blog")
public class PostController {
    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/posts")
    @ApiOperation(value = "Buscar lista completa de postagens do blog")
    public ResponseEntity<List<PostDTO>> findAll() {
        List<Post> postList = postService.findAll();
        List<PostDTO> postDTOList = postList.stream().map(this::convertToDTO).collect(Collectors.toList());
        return ResponseEntity.ok().body(postDTOList);
    }

    @PostMapping("/posts")
    @ApiOperation(value = "Cadastro de novas postagens no blog")
    public ResponseEntity<PostDTO> create(
            @ApiParam(value = "Postagem a ser criada", required = true)
            @RequestBody PostDTO postDTO) throws EmptyContentException {
        Post newPost = postService.create(convertToEntity(postDTO));
        PostDTO newPostDTO = convertToDTO(newPost);
        return ResponseEntity.created(URI.create(newPostDTO.getId().toString())).body(newPostDTO);
    }

    private PostDTO convertToDTO(Post post) {
        return modelMapper.map(post, PostDTO.class);
    }

    private Post convertToEntity(PostDTO postDTO) {
        Post post = modelMapper.map(postDTO, Post.class);
        User user = userService.findById(postDTO.getUser().getId());
        post.setUser(user);
        return post;
    }
}
