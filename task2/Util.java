
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Util {
    private final String[] headers; // name: 0, characteristics: 1-7

    public Util (String[] headers) {
        this.headers = headers;
    }

    private List<Student> generateStudentsList() {
        File formData = new File("data.csv");
        List<Student> students = new ArrayList<>();
        
        try (Scanner fScanner = new Scanner(formData)) {
            fScanner.nextLine();
            while (fScanner.hasNextLine()) {
                String data = fScanner.nextLine();
                String[] params = data.split(",");
                HashMap<String, Boolean> characteristics = new HashMap<>();
                for (int i = 1; i < 8; i++) {
                    characteristics.put(headers[i], ("Да".equals(params[i]) || "Женский".equals(params[i])));
                }
                students.add(new Student(params[0], characteristics));
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return  students;
    }

    public Node generateBinaryTree() {
        List<Student> students = generateStudentsList();
        return dfsGenerateBinaryTree(students, 1);
    }

    private Node dfsGenerateBinaryTree(List<Student> students, int h) {
        if (students.isEmpty()) {
            return new Node("Студентов нет");
        }

        if (h < 8 && headers[h].contains("собака")) {
            String petHeader = headers[h - 1];
            boolean nobodyHasPets = students.stream()
                    .noneMatch(s -> Boolean.TRUE.equals(s.characteristics.get(petHeader)));
            
            if (nobodyHasPets) {
                h = 8;
            }
        }

        if (h >= 8) {
            String name = students.stream().map(s -> s.name).collect(Collectors.joining(", "));
            return new Node(name);
        }

        final String currentHeader = this.headers[h];
        Node currentNode = new Node(currentHeader);

        Map<Boolean, List<Student>> sortedStudents = students.stream()
                    .collect(Collectors.partitioningBy(s -> 
                        Boolean.TRUE.equals(s.characteristics.get(currentHeader))
                    )); 

        currentNode.left = dfsGenerateBinaryTree(sortedStudents.get(true), h + 1);
        currentNode.right = dfsGenerateBinaryTree(sortedStudents.get(false), h + 1);
        
        return currentNode;
        
    }
}