package com.sanbinit.planet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.spring.annotation.MapperScan;

@RestController
@SpringBootApplication
@MapperScan(basePackages = "com.sanbinit.planet.dao.mapper")
public class PlanetApplication {

    private Logger logger = LoggerFactory.getLogger(PlanetApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(PlanetApplication.class, args);
    }

}
