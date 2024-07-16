package com.forohub.foro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.forohub.foro.model.User;

@Repository
public interface IUserRepository extends JpaRepository<User, Integer> {
    User findByUser(String user);
}
