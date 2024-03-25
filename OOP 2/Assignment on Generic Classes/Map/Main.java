package Map;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //For testing generic classes
        Scanner sc = new Scanner(System.in);
        MyMap<Integer, Integer> map = new MyMap<>();
        System.out.println("---Initially set Key-Value pair to both Integer--- [Change Objects to your liking]\n");
        int choice;
        while (true){
            Integer result;
            System.out.print("1-Add 2-Get 3-Remove 4-Print 5-Exit: ");
            choice = sc.nextInt();
            switch (choice){
                case 1:
                    System.out.print("Enter key and value: ");
                    map.put(sc.nextInt(), sc.nextInt());
                    break;
                case 2:
                    System.out.print("Enter key: ");
                    result = map.get(sc.nextInt());
                    if(result == null)
                        System.out.println("Key does not exist");
                    else
                        System.out.println(STR."Value is \{result}");
                    break;
                case 3:
                    System.out.print("Enter key: ");
                    result = map.remove(sc.nextInt());
                    if(result == null)
                        System.out.println("Key does not exist");
                    else
                        System.out.println(STR."Value is \{result}");
                    break;
                case 4:
                    System.out.println(map);
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid Operation");
            }
            System.out.println();
        }
    }
}
