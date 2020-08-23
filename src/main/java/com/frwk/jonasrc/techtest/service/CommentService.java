package com.frwk.jonasrc.techtest.service;

import com.frwk.jonasrc.techtest.exception.EmptyContentException;
import com.frwk.jonasrc.techtest.model.Comment;
import com.frwk.jonasrc.techtest.model.Post;

import java.util.List;

public interface CommentService {
    public List<Comment> findAllByPost(Post post);
    public Comment create(Comment comment) throws EmptyContentException;
    public void deleteById(long id);
}
