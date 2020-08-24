package com.frwk.jonasrc.techtest.controller;

import com.frwk.jonasrc.techtest.dto.CommentDTO;
import com.frwk.jonasrc.techtest.dto.PostDTO;
import com.frwk.jonasrc.techtest.exception.EmptyContentException;
import com.frwk.jonasrc.techtest.model.Comment;
import com.frwk.jonasrc.techtest.model.Post;
import com.frwk.jonasrc.techtest.model.User;
import com.frwk.jonasrc.techtest.service.CommentService;
import com.frwk.jonasrc.techtest.service.PostService;
import com.frwk.jonasrc.techtest.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@Api(tags = "Postagens, Comentários", value = "postController", description="Cadastro, listagem e deleção de postagens e comentários do blog")
public class PostController {
    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/posts")
    @ApiOperation(value = "Buscar lista completa de postagens do blog")
    public ResponseEntity<List<PostDTO>> findAll() {
        List<Post> postList = postService.findAll();
        List<PostDTO> postDTOList = postList.stream().map(this::convertPostToDTO).collect(Collectors.toList());
        return ResponseEntity.ok().body(postDTOList);
    }

    @PostMapping("/posts")
    @ApiOperation(value = "Cadastro de novas postagens no blog")
    public ResponseEntity<PostDTO> create(
            @ApiParam(value = "Postagem a ser criada", required = true)
            @RequestBody PostDTO postDTO) throws EmptyContentException {
        Post newPost = postService.create(convertPostToEntity(postDTO));
        PostDTO newPostDTO = convertPostToDTO(newPost);
        return ResponseEntity.created(URI.create(newPostDTO.getId().toString())).body(newPostDTO);
    }

    @GetMapping("/posts/{postId}/comments")
    @ApiOperation(value = "Buscar lista completa de comentários de uma postagem")
    public ResponseEntity<List<CommentDTO>> findComments(
            @ApiParam(value = "ID do livro ao qual o comentário se refere", required = true)
            @PathVariable Long postId) {
        Post post = postService.findById(postId);
        List<Comment> commentList = commentService.findAllByPost(post);
        List<CommentDTO> commentDTOList = commentList
                .stream().map(this::convertCommentToDTO).collect(Collectors.toList());
        return ResponseEntity.ok().body(commentDTOList);
    }

    @PostMapping("/posts/{postId}/comments")
    @ApiOperation(value = "Postar comentário")
    public ResponseEntity<CommentDTO> postComment(
            @ApiParam(value = "ID do livro ao qual o comentário se refere", required = true)
            @PathVariable String postId,
            @ApiParam(value = "Comentário a ser criado", required = true)
            @RequestBody CommentDTO commentDTO) throws EmptyContentException {
        Comment newComment = commentService.create(convertCommentToEntity(commentDTO));
        return ResponseEntity.ok().body(convertCommentToDTO(newComment));
    }

    private PostDTO convertPostToDTO(Post post) {
        return modelMapper.map(post, PostDTO.class);
    }

    private CommentDTO convertCommentToDTO(Comment comment) {
        return modelMapper.map(comment, CommentDTO.class);
    }

    private Post convertPostToEntity(PostDTO postDTO) {
        Post post = modelMapper.map(postDTO, Post.class);
        User user = userService.findById(postDTO.getUser().getId());
        post.setUser(user);
        return post;
    }

    private Comment convertCommentToEntity(CommentDTO commentDTO) {
        Comment comment = modelMapper.map(commentDTO, Comment.class);
        User user = userService.findById(commentDTO.getUser().getId());
        comment.setCommentUser(user);
        return comment;
    }
}
