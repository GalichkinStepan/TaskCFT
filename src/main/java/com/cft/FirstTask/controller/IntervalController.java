package com.cft.FirstTask.controller;

import java.util.*;

import com.cft.FirstTask.IntervalDeserializer;
import com.cft.FirstTask.IntervalsMereger;
import com.cft.FirstTask.repository.CharIntervalRepository;
import com.cft.FirstTask.repository.IntIntervalRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cft.FirstTask.model.IntInterval;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api/v1/intervals")
public class IntervalController {

	@Autowired
	IntIntervalRepository intIntervalRepository;

	@Autowired
	CharIntervalRepository charIntervalRepository;

	@PostMapping("/merege")
	public ResponseEntity<HttpStatus> createIntIntervals(@RequestParam("kind") String kind,  HttpServletRequest request) {


		IntervalDeserializer deserializer = new IntervalDeserializer();
		IntervalsMereger mereger = new IntervalsMereger();

		String requestBody = deserializer.GetStringBodyReqest(request);
		if(kind == "digits")
		{
			List<IntInterval> intervals = deserializer.DeserializeToListIntInterval(requestBody);
			List<IntInterval> meregedIntervals = mereger.MeregeIntIntervals(intervals);

			for (int i = 0; i < meregedIntervals.size(); i++)
			{
				intIntervalRepository.save(meregedIntervals.get(i));
			}

		} else if (kind == "letters") {

		} else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		return new ResponseEntity<>(HttpStatus.OK);

	}

	@GetMapping("/int/{id}")
	public ResponseEntity<IntInterval> getIntIntervalById(@PathVariable("id") long id) {
		Optional<IntInterval> intervalData = intIntervalRepository.findById(id);

		if (intervalData.isPresent()) {
			return new ResponseEntity<>(intervalData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}

