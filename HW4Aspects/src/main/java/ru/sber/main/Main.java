package ru.sber.main;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.sber.config.AppConfig;
import ru.sber.services.OnlyFellowsService;
import ru.sber.services.OnlyFellowsServiceInterface;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        var context = new AnnotationConfigApplicationContext(AppConfig.class);
        var fellowservice = context.getBean(OnlyFellowsServiceInterface.class);

        fellowservice.authorizeCheckFellow("rerth", "t34t");
        fellowservice.authorizeCheckFellow(null, "t34t");
        fellowservice.setImages(new ArrayList<String>());
        fellowservice.setImages(new ArrayList<String>(Arrays.asList("afge", "ber", "cee", "derg")));
        fellowservice.subscribeFellow();

    }
}
