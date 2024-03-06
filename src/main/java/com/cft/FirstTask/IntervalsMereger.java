package com.cft.FirstTask;

import com.cft.FirstTask.model.CharInterval;
import com.cft.FirstTask.model.IntInterval;

import java.util.*;

public class IntervalsMereger {
    public IntervalsMereger(){}

    public List<CharInterval> MeregeCharIntervals(List<CharInterval> intervals)
    {
        List<IntInterval> intIntervals = new ArrayList<IntInterval>();
        for(int i = 0; i < intervals.size(); i++)
        {
            intIntervals.add(new IntInterval((int)intervals.get(i).getStart(), (int)intervals.get(i).getEnd()));
        }

        intIntervals = MeregeIntIntervals(intIntervals);

        List<CharInterval> output = new ArrayList<CharInterval>();
        for(int i = 0; i < intIntervals.size(); i++)
        {
            output.add(new CharInterval((char)intIntervals.get(i).getStart(), (char)intIntervals.get(i).getEnd()));
            System.out.println(new CharInterval((char)intIntervals.get(i).getStart(), (char)intIntervals.get(i).getEnd()));
        }

        return  output;
    }

    public List<IntInterval> MeregeIntIntervals(List<IntInterval> intervals)
    {
        List<IntInterval> sortIntervals = new ArrayList<IntInterval>();


        while (!intervals.isEmpty())
        {
            IntInterval minStart = intervals.get(0);
            for (int i = 0; i < intervals.size(); i++)
            {
                if(intervals.get(i).getStart() < minStart.getStart()) minStart = intervals.get(i);
            }
            intervals.remove(minStart);
            sortIntervals.add(minStart);
            System.out.print(minStart.getStart() + " ");
        }



        int changeLength = 0;

        for (int i = 0; i < sortIntervals.size() - 1; i++)
        {
            System.out.print(sortIntervals.get(i).getEnd() + " => " + sortIntervals.get(i + 1).getStart());
            if(sortIntervals.get(i).getEnd() >= sortIntervals.get(i + 1).getStart())
            {
                sortIntervals.get(i).setEnd(sortIntervals.get(i + 1).getEnd());
                sortIntervals.remove(sortIntervals.get(i + 1));
                System.out.println(sortIntervals);
                i--;
            }
        }
        return  sortIntervals;
    }
}
