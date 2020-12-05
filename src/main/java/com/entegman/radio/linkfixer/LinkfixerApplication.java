package com.entegman.radio.linkfixer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
//
@EnableScheduling
@SpringBootApplication
public class LinkfixerApplication {

    public static void main(String[] args) {
        SpringApplication.run(LinkfixerApplication.class, args);
    }

}
