package org.example;

import static spark.Spark.*;

public class SparkWebServer {

    public static void main(String[] args){
        port(getPort());
        staticFiles.location("/public");
        get("/roundrobin", (req,res) -> {
            String log = req.queryParams("log");
            return LogService.getLogs(log);
        });
    }

    private static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567;
    }

}