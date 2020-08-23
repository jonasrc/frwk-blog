package com.frwk.jonasrc.techtest.dto;

public class CommentDTO {
    private Long id;

    private String content;

    private String creationDate;

    private UserDTO user;

    private PostDTO post;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public PostDTO getPost() { return post; }

    public void setPost(PostDTO post) { this.post = post; }
}
