package com.challenge.topcoder.javaspringchildhooddream.controller;

import com.challenge.topcoder.javaspringchildhooddream.entity.TrainEntity;
import com.challenge.topcoder.javaspringchildhooddream.repository.TrainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class TrainController {

    @Autowired
    TrainRepository trainRepository;

    @GetMapping("/trains")
    public ResponseEntity<List<TrainEntity>> getAllTrains(@RequestParam(required = false) String name) {
        try {
            List<TrainEntity> trains = new ArrayList<TrainEntity>();

            if (name == null)
                trainRepository.findAll().forEach(trains::add);
            else
                trainRepository.findByNameContaining(name).forEach(trains::add);

            if (trains.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(trains, HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity("invalid endpoint", HttpStatus.METHOD_NOT_ALLOWED);
        }
    }

    @GetMapping("/trains/{id}")
    public ResponseEntity<TrainEntity> getTrainById(@PathVariable("id") long id) {
        Optional<TrainEntity> trainData = trainRepository.findById(id);

        if (trainData.isPresent()) {
            return new ResponseEntity<>(trainData.get(), HttpStatus.OK);
        }
        else {
            return new ResponseEntity("train not found", HttpStatus.NOT_FOUND);
        }
    }
}
