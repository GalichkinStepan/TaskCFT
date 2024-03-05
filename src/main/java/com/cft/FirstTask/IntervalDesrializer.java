package com.cft.FirstTask;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.BufferedReader;

public class IntervalDesrializer {
    public  IntervalDesrializer()
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
}
