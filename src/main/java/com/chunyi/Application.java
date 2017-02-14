package com.chunyi;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by 黄春怡 on 2017/2/9.
 */
@SpringBootApplication
@EnableScheduling
public class Application {

    public static void main(String[] args){
        SpringApplication.run(Application.class,args);
    }
}
