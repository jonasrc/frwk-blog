package com.frwk.jonasrc.techtest.service;

import com.frwk.jonasrc.techtest.model.Album;
import java.util.List;

public interface AlbumService {
    public List<Album> findAll();
    public Album findById(Long id);
    public Album create(Album album);
    public void deleteById(long id);
}
