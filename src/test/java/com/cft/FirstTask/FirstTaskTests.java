package com.cft.FirstTask;

import com.cft.FirstTask.model.CharInterval;
import com.cft.FirstTask.model.IntInterval;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class FirstTaskTests {

	@Test
	void checkDeserializer() {
		IntervalDeserializer deserializer = new IntervalDeserializer();
		String requestBody = "{ [ [1, 2], [3, 4], [5, 6] ] }";
		List<IntInterval> intIntervals = deserializer.DeserializeToListIntInterval(requestBody);
		if(
				intIntervals.get(0).getStart() == 1
				&& intIntervals.get(0).getEnd() == 2
				&& intIntervals.get(1).getStart() == 3
				&& intIntervals.get(1).getEnd() == 4
				&& intIntervals.get(2).getStart() == 5
				&& intIntervals.get(2).getEnd() == 6
		) System.out.println("IntDeserializer test sucsessful!");
		else System.out.println("IntDeserializer test failed!");

		requestBody = "{ [ [\"a\", \"b\"], [\"c\",\"d\"], [\"e\", \"f\"] ] }";
		List<CharInterval> charIntervals = deserializer.DeserializeToListCharInterval(requestBody);
		if(
				charIntervals.get(0).getStart() == 'a'
				&& charIntervals.get(0).getEnd() == 'b'
				&& charIntervals.get(1).getStart() == 'c'
				&& charIntervals.get(1).getEnd() == 'd'
				&& charIntervals.get(2).getStart() == 'e'
				&& charIntervals.get(2).getEnd() == 'f'
		) System.out.println("CharDeserializer test sucsessful!");
		else System.out.println("CharDeserializer test failed!");
	}

	void checkMereger()
	{
		IntervalsMereger mereger = new IntervalsMereger();
		List<IntInterval> intIntervals = new ArrayList<>();
		intIntervals.add(new IntInterval(1, 5));
		intIntervals.add(new IntInterval(5, 7));
		List<IntInterval> intMeregedIntervals = mereger.MeregeIntIntervals(intIntervals);
		if(
				intMeregedIntervals.get(0).getStart() == 1
				&& intMeregedIntervals.get(0).getEnd() == 7
		)	System.out.println("IntMereger test sucsessful!");
		else System.out.println("IntMereger test failed!");

		List<CharInterval> charIntervals = new ArrayList<>();
		charIntervals.add(new CharInterval('a', 'e'));
		charIntervals.add(new CharInterval('c', 'h'));
		List<CharInterval> charMeregedIntervals = mereger.MeregeCharIntervals(charIntervals);
		if(
				charMeregedIntervals.get(0).getStart() == 'a'
				&& charMeregedIntervals.get(0).getEnd() == 'h'
		)	System.out.println("CharMereger test sucsessful!");
		else System.out.println("CharMereger test failed!");
	}
}
