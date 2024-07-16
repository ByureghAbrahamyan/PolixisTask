package com.example.polixis_task.Controller;

import com.example.polixis_task.Repository.MessageRepository;
import com.example.polixis_task.Service.KafkaProducerService;
import com.example.polixis_task.Model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class KafkaProducerController {

    private final KafkaProducerService kafkaProducerService;

    @Autowired
    public KafkaProducerController(KafkaProducerService kafkaProducerService, MessageRepository messageRepository) {
        this.kafkaProducerService = kafkaProducerService;
    }

    @PostMapping("/send")
    public ResponseEntity<String> sendMessageToKafka(@RequestBody Message message) {
        try {
            kafkaProducerService.sendMessage("my_topic", message);
            return ResponseEntity.ok("Message sent successfully to Kafka");
        } catch (Exception ex) {
            System.out.println("Error sending message to Kafka: " + ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to send message to Kafka");
        }
    }
}