package com.cft.FirstTask.controller;

import java.net.http.HttpResponse;
import java.util.*;

import com.cft.FirstTask.IntervalDeserializer;
import com.cft.FirstTask.IntervalsMereger;
import com.cft.FirstTask.model.CharInterval;
import com.cft.FirstTask.repository.CharIntervalRepository;
import com.cft.FirstTask.repository.IntIntervalRepository;
import jakarta.servlet.http.HttpServletRequest;
import netscape.javascript.JSException;
import netscape.javascript.JSObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
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

	@Autowired
	HttpServletRequest request;

	@RequestMapping(value = "/merege", method = RequestMethod.POST, consumes = "application/json")
	//PostMapping("/merege")
	public ResponseEntity<String> createIntIntervals(@RequestParam(name="kind") String kind) {

		IntervalDeserializer deserializer = new IntervalDeserializer();
		IntervalsMereger mereger = new IntervalsMereger();

		String requestBody = deserializer.GetStringBodyReqest(request);


		if(kind.equals("digits"))
		{
			List<IntInterval> intervals = deserializer.DeserializeToListIntInterval(requestBody);
			List<IntInterval> meregedIntervals = mereger.MeregeIntIntervals(intervals);

			for (int i = 0; i < meregedIntervals.size(); i++)
			{
				intIntervalRepository.save(meregedIntervals.get(i));
			}
		} else if (kind.equals("letters")) {

			List<CharInterval> intervals = deserializer.DeserializeToListCharInterval(requestBody);
			List<CharInterval> meregedIntervals = mereger.MeregeCharIntervals(intervals);

			//charIntervalRepository.save(meregedIntervals.get(0));

			for (int i = 0; i < meregedIntervals.size(); i++)
			{
				charIntervalRepository.save(meregedIntervals.get(i));
			}
			requestBody = "letters";

		} else requestBody = "nothing";
		return new ResponseEntity<>(requestBody, HttpStatus.OK);

	}

	@GetMapping("/min")
	public ResponseEntity<String> findMinInterval(@RequestParam("kind") String kind)
	{
		String out;
		if(kind.equals("digits"))
		{
			IntInterval minInterval = intIntervalRepository.findMinInterval();
			out = minInterval.toString();
		} else if (kind.equals("letters")) {
			CharInterval minInterval = charIntervalRepository.findMinInterval();
			out = minInterval.toString();
		} else return new ResponseEntity<>("BadGateway", HttpStatus.BAD_GATEWAY);

		return new ResponseEntity<>(out, HttpStatus.OK);
	}



	@GetMapping("/int/{id}")
	public ResponseEntity<IntInterval> getIntIntervalById(@PathVariable("id") long id) {
		Optional<IntInterval> intervalData = intIntervalRepository.findById(id);
		intIntervalRepository.save(new IntInterval(1,2));
		if (intervalData.isPresent()) {
			return new ResponseEntity<>(intervalData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}

