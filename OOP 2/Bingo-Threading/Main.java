package Bingo;

public class Main {
    public static void main(String[] args) {
        Thread t = new Thread(new BingoGame());
        t.start();
    }
}
