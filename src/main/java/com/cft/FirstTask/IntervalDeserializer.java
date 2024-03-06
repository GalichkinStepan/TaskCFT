package com.cft.FirstTask;

import com.cft.FirstTask.model.CharInterval;
import com.cft.FirstTask.model.IntInterval;
import jakarta.servlet.http.HttpServletRequest;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;

public class IntervalDeserializer {
    public IntervalDeserializer()
    {

    }

    public String GetStringBodyReqest(HttpServletRequest request)
    {
        StringBuilder requestBody = new StringBuilder();
        try {
            BufferedReader reader = request.getReader();
            String line;
            while ((line = reader.readLine()) != null) {
                requestBody.append(line);
            }
        } catch (Exception ex) {
            System.out.println("Cant reach reqest reader");
            return "";
        }
        return requestBody.toString();
    }

    public List<IntInterval> DeserializeToListIntInterval(String reqestBody)//{[[1, 2], [3, 4], [5, 6]]}
    {
        List<IntInterval> output = new ArrayList<IntInterval>();

        reqestBody = reqestBody.replace(" ", "");//{[[1,2],[3,4],[5,6]]}

        String breket = "[";
        String invBreket = "]";

        reqestBody = reqestBody.substring(2, reqestBody.length() - 2);//[1,2],[3,4],[5,6]

        while(!reqestBody.isEmpty())
        {
            int startInd = reqestBody.indexOf(breket) + 1;
            int endInd = reqestBody.indexOf(invBreket);
            int commaInd = reqestBody.indexOf(',');
            try
            {
                int startInterval = Integer.parseInt(reqestBody.substring(startInd, commaInd));
                int endInterval= Integer.parseInt(reqestBody.substring(commaInd + 1, endInd));;
                output.add(new IntInterval(startInterval, endInterval));
            }
            catch (Exception ex)
            {
                throw(ex); // Deserialization ERROR
                //break;
            }

            try
            {
                reqestBody = reqestBody.substring(endInd + 2);
            }
            catch (Exception ex)
            {
                break;
            }
        }

        return output;
    }

    public List<CharInterval> DeserializeToListCharInterval(String reqestBody)//{ [["a", "b"], ["c", "d"], ["e", "f"]] }
    {
        List<CharInterval> output = new ArrayList<CharInterval>();

        reqestBody = reqestBody.replace(" ", "");//{[["a","b"],["c","d"],["e","f"]]}

        String breket = "[" ;
        String invBreket = "]";

        reqestBody = reqestBody.substring(2, reqestBody.length() - 2);//["a","b"],["c","d"],["e","f"]

        while(!reqestBody.isEmpty())
        {
            int startInd = reqestBody.indexOf(breket) + 1;
            int endInd = reqestBody.indexOf(invBreket);
            int zapInd = reqestBody.indexOf(',');
            try
            {
                char startInterval = reqestBody.substring(startInd + 1, zapInd - 1).charAt(0);
                char endInterval= reqestBody.substring(zapInd + 2, endInd - 1).charAt(0);
                output.add(new CharInterval(startInterval, endInterval));
            }
            catch (Exception ex)
            {
                throw(ex); // Deserilization ERROR
                //break;
            }

            try
            {
                reqestBody = reqestBody.substring(endInd + 2);
            }
            catch (Exception ex)
            {
                break;
            }
        }

        return output;

    }
}
