package com.forohub.foro.dto;

import java.util.List;

public record UserDto(
    Integer userId,
    String user,
    String password,
    List<TopicDto> topics,
    List<CommentDto> comments
) {
    public UserDto(Integer userId, String user, String password) {
        this(userId, user, password, null, null);
    }
}
