package com.example.Reception.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class ReceptionController {
    @KafkaListener(topics = "BOOK-APPOINTMENT",groupId = "group_id")
    public void bookappointmentconsumer(String message) {

        System.out.println("message = " + message);
    }


    @Autowired
    KafkaTemplate<String,String> kafkaTemplate;
    public static final String TOPIC="AppointmentConfirmation";
    @GetMapping("/Appointmentconfirmation/{message}")
    public String processBookAppoinment(@PathVariable("message")final String message) {
        kafkaTemplate.send("AppointmentConfirmation", message);
        return message+" :Book appointment completed";}
}
