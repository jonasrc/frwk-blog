package com.frwk.jonasrc.techtest.service;

import com.frwk.jonasrc.techtest.exception.EmptyContentException;
import com.frwk.jonasrc.techtest.model.Post;
import java.util.List;

public interface PostService {
    public List<Post> findAll();
    public Post findById(long id);
    public Post create(Post post) throws EmptyContentException;
    public void deleteById(long id);
}
