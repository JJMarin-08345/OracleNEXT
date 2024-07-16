package com.forohub.foro.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.forohub.foro.model.Topic;

public interface ITopicRepository extends JpaRepository<Topic, Integer> {
}
