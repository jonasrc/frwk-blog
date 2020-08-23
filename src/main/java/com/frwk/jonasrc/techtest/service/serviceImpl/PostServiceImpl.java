package com.frwk.jonasrc.techtest.service.serviceImpl;

import com.frwk.jonasrc.techtest.exception.EmailExistsException;
import com.frwk.jonasrc.techtest.exception.EmptyContentException;
import com.frwk.jonasrc.techtest.model.Post;
import com.frwk.jonasrc.techtest.model.User;
import com.frwk.jonasrc.techtest.repository.PostRepository;
import com.frwk.jonasrc.techtest.repository.UserRepository;
import com.frwk.jonasrc.techtest.service.PostService;
import com.frwk.jonasrc.techtest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostRepository repository;

    @Override
    public List<Post> findAll() {
        return repository.findAll();
    }

    @Override
    public Post findById(long id) {
        return repository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public Post create(Post post) throws EmptyContentException {
        if (post.getContent().isEmpty()) {
            throw new EmptyContentException();
        }

        return repository.save(post);
    }

    @Override
    public void deleteById(long id) {
        repository.deleteById(id);
    }
}
