package com.frwk.jonasrc.techtest.service.serviceImpl;

import com.frwk.jonasrc.techtest.exception.EmptyContentException;
import com.frwk.jonasrc.techtest.model.Comment;
import com.frwk.jonasrc.techtest.model.Post;
import com.frwk.jonasrc.techtest.repository.CommentRepository;
import com.frwk.jonasrc.techtest.repository.PostRepository;
import com.frwk.jonasrc.techtest.service.CommentService;
import com.frwk.jonasrc.techtest.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentRepository repository;

    @Override
    public List<Comment> findAllByPost(Post post) {
        return repository
                .findAll()
                .stream()
                .filter(element -> element.getCommentPost().equals(post))
                .collect(Collectors.toList());
    }

    @Override
    public Comment create(Comment comment) throws EmptyContentException {
        if (comment.getContent().isEmpty()) {
            throw new EmptyContentException();
        }

        return repository.save(comment);
    }

    @Override
    public void deleteById(long id) {
        repository.deleteById(id);
    }
}
