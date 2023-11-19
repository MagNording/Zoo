import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;

public class AnimalSaver {

    public void saveAnimals(List<Animal> animals, String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(animals);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}