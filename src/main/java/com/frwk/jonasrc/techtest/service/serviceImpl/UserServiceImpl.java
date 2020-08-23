package com.frwk.jonasrc.techtest.service.serviceImpl;

import com.frwk.jonasrc.techtest.dto.UserDTO;
import com.frwk.jonasrc.techtest.exception.EmailExistsException;
import com.frwk.jonasrc.techtest.exception.UserNotFoundException;
import com.frwk.jonasrc.techtest.model.User;
import com.frwk.jonasrc.techtest.repository.UserRepository;
import com.frwk.jonasrc.techtest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<User> findAll() {
        return repository.findAll();
    }

    @Override
    public User findById(long id) {
        User user = repository.findById(id).orElse(null);
        if(user == null) {
            throw new UserNotFoundException(id);
        }
        return user;
    }

    @Override
    public User create(User user) throws EmailExistsException {
        if (emailExists(user.getEmail())) {
            throw new EmailExistsException(user.getEmail());
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return repository.save(user);
    }

    @Override
    public void deleteById(long id) {
        repository.deleteById(id);
    }

    private boolean emailExists(String email) {
        return repository.findAll().stream().anyMatch(element -> element.getEmail().equals(email));
    }
}
