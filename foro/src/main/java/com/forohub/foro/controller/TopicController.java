package com.forohub.foro.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.forohub.foro.dto.TopicDto;
import com.forohub.foro.model.Topic;
import com.forohub.foro.service.TopicService;

import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/topics")
public class TopicController {
    private final TopicService topicService;

    public TopicController(TopicService topicService) {
        this.topicService = topicService;
    }

    //Get all topics
    @GetMapping("/getTopics")
    public ResponseEntity<List<TopicDto>> getTopics() {
        List<TopicDto> topics = topicService.getAllTopics();
        return ResponseEntity.ok(topics);
    }

    //Get topic by id
    @GetMapping("/getTopicById/{id}")
    public ResponseEntity<TopicDto> getTopicById(@PathVariable Integer id) {
        TopicDto topic = topicService.getTopicById(id);
        return ResponseEntity.ok(topic);
    }

    //Create topic
    @PostMapping("/createTopic/{userId}")
    public ResponseEntity<Topic> createTopic(@PathVariable Integer userId, @RequestBody Topic topic) {
        Topic newTopic = topicService.createTopic(userId, topic);
        return ResponseEntity.ok(newTopic);
    }

    //Update topic
    @PutMapping("updateTopic/{userId}")
    public ResponseEntity<Topic> updateTopic(@PathVariable Integer userId, @RequestBody Topic topic) {
        //TODO: process PUT request
        Topic updatedTopic = topicService.updateTopic(userId, topic);
        return ResponseEntity.ok(updatedTopic);
    }

    //Delete topic
    @DeleteMapping("/deleteTopic/{userId}")
    public String deleteUser(@PathVariable Integer userId, @RequestBody Map<String, Integer> jsonTopic) {
        Integer topicId = jsonTopic.get("topicId");
        System.out.println("Deleting topic with id: " + topicId);
        return topicService.deleteTopic(userId, topicId);
    }

}
