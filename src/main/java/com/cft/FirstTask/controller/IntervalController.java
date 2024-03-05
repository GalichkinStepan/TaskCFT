package com.cft.FirstTask.controller;

import java.util.Optional;

import com.cft.FirstTask.model.CharInterval;
import com.cft.FirstTask.repository.CharIntervalRepository;
import com.cft.FirstTask.repository.IntIntervalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cft.FirstTask.model.IntInterval;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class IntervalController {

	@Autowired
	IntIntervalRepository intIntervalRepository;

	@Autowired
	CharIntervalRepository charIntervalRepository;

	@GetMapping("/int/{id}")
	public ResponseEntity<IntInterval> getIntIntervalById(@PathVariable("id") long id) {
		Optional<IntInterval> intervalData = intIntervalRepository.findById(id);

		if (intervalData.isPresent()) {
			return new ResponseEntity<>(intervalData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/char/{id}")
	public ResponseEntity<CharInterval> getCharIntervalById(@PathVariable("id") long id) {
		Optional<CharInterval> intervalData = charIntervalRepository.findById(id);

		if (intervalData.isPresent()) {
			return new ResponseEntity<>(intervalData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}


}
