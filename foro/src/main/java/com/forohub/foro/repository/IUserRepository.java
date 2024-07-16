package com.forohub.foro.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.forohub.foro.dto.UserDto;
import com.forohub.foro.model.User;

@Repository
public interface IUserRepository extends JpaRepository<User, Integer> {

    @Query(value="SELECT u.userId, u.user, u.password, t.topicid, t.course, t.title, t.description"
            + " FROM comments c INNER JOIN topics t ON t.topicid = c.topicid" 
            + " INNER JOIN users u ON u.userid = c.userid", nativeQuery=true)
    List<UserDto> findUsersWithTopicsAndComments();

    
}
