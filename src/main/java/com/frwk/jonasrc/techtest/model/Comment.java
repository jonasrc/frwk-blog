package com.frwk.jonasrc.techtest.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
import java.time.LocalDate;

@Entity
@Table(name = "comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "comment_seq_gen")
    @SequenceGenerator(name = "comment_seq_gen", sequenceName = "comment_id_seq")
    private Long id;

    @NotBlank
    private String content;

    @NotBlank
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss", locale = "UTC-03")
    private LocalDate creationDate;

    @ManyToOne(fetch = FetchType.LAZY)
    private User commentUser;

    @ManyToOne(fetch = FetchType.LAZY)
    private Post commentPost;

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

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public User getCommentUser() {
        return commentUser;
    }

    public void setCommentUser(User commentUser) {
        this.commentUser = commentUser;
    }

    public Post getCommentPost() {
        return commentPost;
    }

    public void setCommentPost(Post commentPost) {
        this.commentPost = commentPost;
    }
}
