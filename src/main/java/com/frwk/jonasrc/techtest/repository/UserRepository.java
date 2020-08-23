package com.frwk.jonasrc.techtest.repository;

import com.frwk.jonasrc.techtest.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {}
