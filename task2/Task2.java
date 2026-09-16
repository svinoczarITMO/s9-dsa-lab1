import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;



class Task2{
    static String[] headers; // name: 0, characteristics: 1-7
    public static void main(String[] args) {
        File formData = new File("data.csv");
        
        try (Scanner fScanner = new Scanner(formData)) {
            headers = fScanner.nextLine().replace("Пол", "ТЫ ЖЕНЩИНА?").split(","); // 0-7, charctrstcs 1-7
            
        } catch (FileNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }

        Util util = new Util(headers);
        Node bt = util.generateBinaryTree();
        Scanner scanner = new Scanner(System.in);

        for (int h = 1; h < 8; h++) {
            System.out.println(headers[h]);
            String answer = scanner.next();
            if (answer.toLowerCase().equals("да")) {
                bt = bt.left;
            } else {
                bt = bt.right;
                if (headers[h].contains("животные")) {
                    h++;     
                }
            }
        }

        System.out.println(bt.val);
    }
}