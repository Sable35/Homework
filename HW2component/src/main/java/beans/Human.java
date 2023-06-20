package beans;

import org.springframework.stereotype.Component;

@Component
public class Human {

    private String name;
    private Parrot parrot1;
    private Parrot parrot2;
    private Dog dog;
    private Cat cat;

    public Parrot getParrot1() {
        return parrot1;
    }

    public void setParrot1(Parrot parrot1) {
        this.parrot1 = parrot1;
    }

    public Parrot getParrot2() {
        return parrot2;
    }

    public void setParrot2(Parrot parrot2) {
        this.parrot2 = parrot2;
    }

    public Dog getDog() {
        return dog;
    }

    public void setDog(Dog dog) {
        this.dog = dog;
    }

    public Cat getCat() {
        return cat;
    }

    public void setCat(Cat cat) {
        this.cat = cat;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
