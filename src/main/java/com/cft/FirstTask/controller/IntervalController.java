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
	public ResponseEntity<HttpStatus> createIntIntervals(@RequestParam(name="kind") String kind) {

		IntervalDeserializer deserializer = new IntervalDeserializer();
		IntervalsMereger mereger = new IntervalsMereger();

		String requestBody = deserializer.GetStringBodyReqest(request);

		if(kind.equals("digits"))
		{
			List<IntInterval> intervals = deserializer.DeserializeToListIntInterval(requestBody);

			for(int i = 0; i < intervals.size();i++) // Проверка некоторректных интервалов
				if(intervals.get(i).getStart() > intervals.get(i).getEnd())
					return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

			List<IntInterval> meregedIntervals = mereger.MeregeIntIntervals(intervals);

			for (int i = 0; i < meregedIntervals.size(); i++)
			{
				intIntervalRepository.save(meregedIntervals.get(i));
			}
		} else if (kind.equals("letters")) {

			List<CharInterval> intervals = deserializer.DeserializeToListCharInterval(requestBody);

			for(int i = 0; i < intervals.size();i++) // Проверка некоторректных интервалов
				if((int)intervals.get(i).getStart() > (int)intervals.get(i).getEnd())
					return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

			List<CharInterval> meregedIntervals = mereger.MeregeCharIntervals(intervals);

			for (int i = 0; i < meregedIntervals.size(); i++)
			{
				charIntervalRepository.save(meregedIntervals.get(i));
			}
		} else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping("/min")
	public ResponseEntity<String> findMinInterval(@RequestParam("kind") String kind)
	{
		String output;
		if(kind.equals("digits"))
		{
			try
			{
				IntInterval minInterval = intIntervalRepository.findMinInterval();
				output = minInterval.toString();
			}
			catch (Exception ex)
			{
				if(intIntervalRepository.findAll().isEmpty())
					return new ResponseEntity<>("Table is Empty", HttpStatus.NOT_FOUND);
				else
					return new ResponseEntity<>("Impossible find min (inner intersection)", HttpStatus.NOT_FOUND);
			}

		} else if (kind.equals("letters")) {
			try
			{
				CharInterval minInterval = charIntervalRepository.findMinInterval();
				output = minInterval.toString();
			}
			catch (Exception exception)
			{
				if(charIntervalRepository.findAll().isEmpty())
					return new ResponseEntity<>("Table is Empty", HttpStatus.NOT_FOUND);
				else
					return new ResponseEntity<>("Impossible find min (inner intersection)", HttpStatus.NOT_FOUND);
			}

		} else return new ResponseEntity<>("BadRequest", HttpStatus.BAD_REQUEST);

		return new ResponseEntity<>(output, HttpStatus.OK);
	}
}

