import UserUtils.UserInput;
import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import static UserUtils.UserInput.print;
import static UserUtils.UserInput.println;

public class Main {
    private static final String filename = "animals.ser";
    public static List<Animal> allAnimals = new ArrayList<>();

    public static void main(String[] args) {
        loadAnimal();

        greeting();
        menuChoices();
        endSequence();
    }

    public static void greeting() {
        println("Välkommen till Zoo App!");
    }

    public static void menuChoices() {
        boolean exitMenu = false;
        do {
            menu();
            int menuChoice = UserInput.readInt();

            switch (menuChoice) {
                case 0 -> exitMenu = exitProgram(); // = true;
                case 1 -> addNewAnimal();
                case 2 -> removeAnimal();
                case 3 -> displayAllAnimals();
            }
        } while (!exitMenu);
    }

    public static void menu() {
        println("\n0. Avsluta programmet.");
        println("1. Lägg till ett djur.");
        println("2. Ta bort ett djur.");
        println("3. Visa samtliga djur.");
        print("\nAnge ditt menyval:");
    }

    public static void addNewAnimal() {
        String nameInput = addName();
        int ageInput = addAge();
        String speciesInput = addSpecies();
        
        Animal animal = new Animal(nameInput, ageInput, speciesInput);
        allAnimals.add(animal);
        saveAnimal();
        System.out.println(speciesInput + " har lagts till.");
    }
    
    public static String addName() {
        print("Vad kallas djuret: ");
        return UserInput.capitalize(UserInput.readString());
    }
    public static int addAge() {
        print("Djurets ålder: ");
        return UserInput.readInt();
    }
    public static String addSpecies() {
        print("Ange djurart: ");
         return UserInput.capitalize(UserInput.readString());
    }

    public static void removeAnimal() {
        println("Ange djuret du vill ta bort: ");
        String animalToRemove = UserInput.readString();
        if (animalToRemove.isEmpty()) {
            println("Ingen djur angivet. Ingen ändring har gjorts.");
            return;
        }
        Iterator<Animal> iterator = allAnimals.iterator();
        while (iterator.hasNext()) {
            Animal animal = iterator.next();
            String animalName = animal.getName();
            if (animalName.toLowerCase().startsWith(animalToRemove.toLowerCase())) {
                println(String.valueOf(animal));

                println("Är du säker på att du vill ta bort djuret? (j/n): ");
                String confirmation = UserInput.readString();
                if (confirmation.equalsIgnoreCase("j")) {
                    iterator.remove();
                    println(UserInput.capitalize(animalName) + " har tagits bort.");
                } else {
                    println("Inget djur har tagits bort.");
                }
                return;
            }
        }
        println("Inget matchande djur hittades.");
    }

    public static void saveAnimal() {
        try {
            // Spara objekten till fil
            AnimalSaver saver = new AnimalSaver();
            saver.saveAnimals(allAnimals, filename);
        } catch (Exception e) {
            // Hantera andra eventuella undantag här
            System.err.println("Ett oväntat fel uppstod: " + e.getMessage());
        }
    }

    public static void loadAnimal() {
        try {
            File file = new File(filename);
            if (file.exists() && !file.isDirectory()) {
                // Filen finns, ladda djuren
                AnimalLoader loader = new AnimalLoader();
                allAnimals = loader.loadAnimals(filename);
            } else {
                // Filen finns inte, starta med en tom lista
                allAnimals = new ArrayList<>();
            }
        } catch (Exception e) {
            // Hantera eventuellt undantag här
            System.err.println("Ett fel uppstod vid laddning av djur: " + e.getMessage());
            allAnimals = new ArrayList<>();
        }
    }

    public static void displayAllAnimals() {
        // Sortera listan först, innan iterationen börjar
        allAnimals.sort(Comparator.comparing(Animal::getName));

        // Sedan iterera över den sorterade listan
        for (Animal animal : allAnimals) {
            println(animal.toString());
        }
    }

    public static boolean exitProgram() {
        println("Vill du verkligen avsluta?");
        return UserInput.readJaNej();
    }

    public static void endSequence() {
        saveAnimal();
        println("Tack för användande!");
        println("Programmet avslutas...");
    }
}
