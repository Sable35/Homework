package main;

import beans.Human;
import config.ProjConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    public static void main(String[] args) {
        var context = new AnnotationConfigApplicationContext(ProjConfig.class);

        Human h = context.getBean(Human.class);
        System.out.println("Person's name: " + h.getName());
        System.out.println("Person's first parrot: " + h.getParrot1().getName());
        System.out.println("Person's second parrot: " + h.getParrot2().getName());
        System.out.println("Person's cat: " + h.getCat().getName());
        System.out.println("Person's dog: " + h.getDog().getName());
    }
}