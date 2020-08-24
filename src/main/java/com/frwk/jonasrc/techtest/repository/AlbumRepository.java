package com.frwk.jonasrc.techtest.repository;

import com.frwk.jonasrc.techtest.model.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlbumRepository extends JpaRepository<Album, Long> {}
