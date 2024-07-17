package com.forohub.foro.model;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.forohub.foro.dto.CommentDto;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
@Table(name = "topics")
public class Topic implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "topicid")
    private Integer topicId;
    private String course;
    private String title;
    private String description;
    //Relation with User
    @ManyToOne
    @JoinColumn(name = "userid")
    private User user;
    //Relation with Comment
    @OneToMany(mappedBy = "topic", cascade= CascadeType.ALL)
    @JsonIgnore
    private List<Comment> comments;

    public Topic() {
    }

    public Topic(Integer topicId, String course, String title, String description, User user, List<Comment> comments) {
        this.topicId = topicId;
        this.course = course;
        this.title = title;
        this.description = description;
        this.user = user;
        this.comments = comments;
    }    

    public Integer getTopicId() {
        return topicId;
    }

    public void setTopicId(Integer topicId) {
        this.topicId = topicId;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<CommentDto> getComments() {
        return comments.stream().map(comment -> new CommentDto(
            comment.getUser().getUser(),
            comment.getTopic().getTitle(),
            comment.getCommentId(),
            comment.getComment()
        )).collect(Collectors.toList());
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    @Override
    public String toString(){
        return "Topic{" +
                "topicId=" + topicId +
                ", course='" + course + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", user=" + user +
                ", comments=" + comments +
                '}';
    }

}
