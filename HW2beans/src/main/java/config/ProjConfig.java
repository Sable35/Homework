package config;

import beans.Cat;
import beans.Dog;
import beans.Human;
import beans.Parrot;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "beans")
public class ProjConfig {

    @Bean
    public Parrot parrot1() {
        Parrot p = new Parrot();
        p.setName("Cracky");
        return p;
    }

    @Bean
    public Parrot parrot2() {
        Parrot p = new Parrot();
        p.setName("Rocky");
        return p;
    }
    @Bean
    public Cat cat() {
        Cat c = new Cat();
        c.setName("BorisBritva");
        return c;
    }

    @Bean
    public Dog dog() {
        Dog d = new Dog();
        d.setName("Hachiko");
        return d;
    }

    @Bean
    public Human human() {
        Human h = new Human();
        h.setName("Sergey");
        h.setParrot1(parrot1());
        h.setParrot2(parrot2());
        h.setDog(dog());
        h.setCat(cat());
        return h;
    }
}
