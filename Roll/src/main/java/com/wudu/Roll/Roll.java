package com.wudu.Roll;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
@MapperScan("com.wudu.Roll.mapper")
public class Roll {
    
        public static void main(String[] args) {
                var context=SpringApplication.run(Roll.class, args);

        }

}
