package com.frwk.jonasrc.techtest.repository;

import com.frwk.jonasrc.techtest.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {}
