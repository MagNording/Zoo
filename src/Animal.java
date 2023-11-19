import java.io.Serial;
import java.io.Serializable;

public class Animal implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private String name;
    private int age;
    private String species;


    public Animal(String name, int age, String species) {
        this.name = name;
        this.age = age;
        this.species = species;
    }

    public String getName() {
        return this.name;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        if (age > 0) {
            this.age = age;
        } else System.out.println("Åldern bör inte vara noll.");

    }

    @Override
    public String toString() {
        return String.format("Namn: %-9s Art: %-14s Ålder: %d", this.name, this.species, this.age);
    }

}