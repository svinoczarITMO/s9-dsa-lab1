# Лабораторная работа №1 (знакомство с графами)

## Задание 1
Написать программу для бинарного поиска. Результатом должно быть количество шагов, которое потребуется, чтобы найти загаданное число из массива.

### Решение:
```java
private static int binarySearch(int[] arr, int target) {
    int left = 0;
    int right = arr.length - 1;
    int steps = 0;

    while (left <= right) {
        int mid = (left + right) / 2;
        steps++;

        if (arr[mid] == target) {
            return steps;
        }

        if (arr[mid] < target) {
            left = mid + 1;
        } else {
            right = mid - 1;
        }
    }

    return -1;
}
```
Полный код: [Task1](/Task1.java)

## Задание 2
Для своей учебной группы составить словарь, который будет описывать характеристики каждого из студентов. Реализовать программу, которая по определенным характеристикам будет угадывать студента.
Пример выполнения:
```
>> Студент курит?
>> Нет.
>> Студент блондин?
>> Да.
>> …
>> …
>> Вы загадали Ивана Иванова. 
```

### Решение:

Main класс:
```java
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
```

Функция генерации бинарного дерева:
```java
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
```

Директория с кодом: [Task2 dir](/task2/)
Полный код генерации списка студентов и бинарного дерева: [Task 2](/task2/Util.java)  

...

## Задание 3
Составьте граф для задания №2. К какому типу относится данный граф?

### Решение:

...
