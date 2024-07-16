package com.forohub.foro.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "commentid")
    private Integer commentid;
    //Relation with Topic
    @ManyToOne()
    @JoinColumn(name = "topicid")
    private Topic topic;
    //Relation with User
    @ManyToOne()
    @JoinColumn(name = "userid")
    private User user;
    private String comment;

    public Comment() {
    }

    public Comment(Integer commentid, Topic topic, User user, String comment) {
        this.commentid = commentid;
        this.topic = topic;
        this.user = user;
        this.comment = comment;
    }

    public Integer getCommentid() {
        return commentid;
    }

    public void setCommentid(Integer commentid) {
        this.commentid = commentid;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

}
