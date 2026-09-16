import java.util.Map;

public class Student {
    public String name;
    public Map<String, Boolean> characteristics;

    public Student(String name, Map<String, Boolean> characteristics) {
        this.name = name;
        this.characteristics = characteristics;
    }
}