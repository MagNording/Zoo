import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.List;

public class AnimalLoader {

    @SuppressWarnings("unchecked")
    public List<Animal> loadAnimals(String filename) {
        List<Animal> animals = null;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            animals = (List<Animal>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return animals;
    }
}