package com.frwk.jonasrc.techtest.service.serviceImpl;

import com.frwk.jonasrc.techtest.exception.AlbumNotFoundException;
import com.frwk.jonasrc.techtest.model.Album;
import com.frwk.jonasrc.techtest.repository.AlbumRepository;
import com.frwk.jonasrc.techtest.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlbumServiceImpl implements AlbumService {
    @Autowired
    private AlbumRepository repository;

    @Override
    public List<Album> findAll() {
        return repository.findAll();
    }

    @Override
    public Album findById(Long id) {
        Album album = repository.findById(id).orElse(null);
        if(album == null) {
            throw new AlbumNotFoundException(id);
        }
        return album;
    }

    @Override
    public Album create(Album album) { return repository.save(album); }

    @Override
    public void deleteById(long id) {
        repository.deleteById(id);
    }
}
