package com.forohub.foro.dto;

import java.util.List;
import com.forohub.foro.model.Comment;
import com.forohub.foro.model.Topic;

public record UserDto(
    Integer userId,
    String user,
    String password,
    List<Topic> topics,
    List<Comment> comments
) {}
