package org.docryze.dubboui.site;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author docryze
 */
@SpringBootApplication(scanBasePackages = "org.docryze.dubboui")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }
}
