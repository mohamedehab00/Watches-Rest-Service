package com.rest.watchrestservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@SpringBootApplication(exclude = {SecurityAutoConfiguration. class})
@SpringBootApplication
public class WatchRestServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(WatchRestServiceApplication.class, args);
    }

}
