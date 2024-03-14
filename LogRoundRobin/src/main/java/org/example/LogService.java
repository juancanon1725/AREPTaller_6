package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class LogService {

    private static final String USER_AGENT = "Mozilla/5.0";

    private  static int current_index = 0;

    private static String[] backServices = {"http://logservices-1:6001/logs?log=","http://logservices-2:6001/logs?log=","http://logservices-3:6001/logs?log="};
    public static List<String> getLogs(String message) throws IOException, MalformedURLException, ProtocolException {
        String GET_URL = roundRobin() + message;
        URL obj = new URL(GET_URL);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", USER_AGENT);

        int responseCode = con.getResponseCode();
        System.out.println("GET Response Code :: " + responseCode);

        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String inputLine;
            List<String> response = new ArrayList<>();

            while ((inputLine = in.readLine()) != null) {
                response.add(inputLine);
            }
            in.close();

            // print result
            System.out.println(response.toString());
            System.out.println("GET DONE");
            return response;

        } else {
            System.out.println("GET request not worked");
        }

        return null;
    }


    private static String roundRobin(){
        current_index = (current_index + 1) % backServices.length;
        System.out.println( "Server : "  + backServices[current_index]);
        return backServices[current_index];


    }

}
