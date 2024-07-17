package com.forohub.foro.model;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.forohub.foro.dto.CommentDto;
import com.forohub.foro.dto.TopicDto;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userid")
    private Integer userId;
    private String user;
    private String password;
    //Relation with Topic
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Topic> topics;
    //Relation with Comment
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Comment> comments;

    public User() {
    }

    public User(Integer userId, String user, String password) {
        this.userId = userId;
        this.user = user;
        this.password = password;
    }

    public User(Integer userId, String user, String password, List<Topic> topics, List<Comment> comments) {
        this.userId = userId;
        this.user = user;
        this.password = password;
        this.topics = topics;
        this.comments = comments;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<TopicDto> getTopics() {
        return topics.stream().map(topic -> new TopicDto(
                topic.getTopicId(),
                topic.getUser().getUser(),
                topic.getCourse(),
                topic.getTitle(),
                topic.getDescription(),
                null
        )).collect(Collectors.toList());
    }

    public void setTopics(List<Topic> topics) {
        this.topics = topics;
    }

    public List<CommentDto> getComments() {
        return comments.stream().map(comment -> new CommentDto(
                comment.getTopic().getUser().getUser(),
                comment.getTopic().getTitle(),
                comment.getCommentId(),
                comment.getComment()
        )).collect(Collectors.toList());
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return "User{"
                + "userId=" + userId
                + ", user='" + user + '\''
                + ", password='" + password + '\''
                + ", topics=" + topics
                + ", comments=" + comments
                + '}';
    }

}
