package main;

import beans.Cat;
import beans.Dog;
import beans.Human;
import beans.Parrot;
import config.ProjConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    public static void main(String[] args) {
        var context = new AnnotationConfigApplicationContext(ProjConfig.class);

        Human h = context.getBean(Human.class);
        h.setParrot1(context.getBean(Parrot.class));
        h.setParrot2(context.getBean(Parrot.class));
        h.setCat(context.getBean(Cat.class));
        h.setDog(context.getBean(Dog.class));
        h.setName("Sergey");
        h.getParrot1().setName("Cracky");
        h.getParrot2().setName("Rocky");
        h.getCat().setName("BorisBritva");
        h.getDog().setName("Hachiko");
        System.out.println("Person's name: " + h.getName());
        System.out.println("Person's first parrot: " + h.getParrot1().getName());
        System.out.println("Person's second parrot: " + h.getParrot2().getName());
        System.out.println("Person's cat: " + h.getCat().getName());
        System.out.println("Person's dog: " + h.getDog().getName());
    }
}