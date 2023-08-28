package com.great.app.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import jakarta.servlet.http.HttpServletRequest;
import static net.logstash.logback.argument.StructuredArguments.keyValue;
//import uuid
import java.util.UUID;

@RestController
public class HelloWorldController {

    //get request
    @Autowired
    private HttpServletRequest request;

    // @Autowired
    Logger logger = LoggerFactory.getLogger(HelloWorldController.class);
    @Autowired
    private Environment env;

    @GetMapping("/")
    public String helloWorld() {
        //generate transaction id
        String transactionId = UUID.randomUUID().toString();
        
        logger.info("Request received"
        ,keyValue("transactionId", transactionId)
        ,keyValue("remoteAddr", request.getRemoteAddr())
        ,keyValue("User-Agent", request.getHeader("User-Agent"))
        );
        return "<html><head></head><body>"
        + "<h1>This is a web template</h1>"
        + "Transaction id: " + transactionId
        + "<p>Your request is processed by pod: "
        + env.getProperty("POD_NAME", "Unset")
        + "</p>"
        + "<footer><p><i>Edit this file at /src/main/java/com/great/app/controllers/HelloWorldController.java</i></p></footer>"
        + "</body></html>";
    }
}