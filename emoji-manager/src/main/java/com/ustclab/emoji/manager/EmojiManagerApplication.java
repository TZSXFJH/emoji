package com.ustclab.emoji.manager;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.ustclab.emoji")
public class EmojiManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmojiManagerApplication.class, args);
    }

}
