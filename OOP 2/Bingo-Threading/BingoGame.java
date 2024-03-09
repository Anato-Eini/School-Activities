package Bingo;

import java.util.*;

public class BingoGame implements Runnable{
    List<BingoCard> cards;
    protected static boolean[] results;
    protected static boolean isBingo;
    protected static ArrayList<Integer> chosenNumbers;
    public BingoGame(){
        results = new boolean[76];
        results[0] = true;
        chosenNumbers = new ArrayList<>();
        chosenNumbers.add(0);
    }


    @Override
    public void run() {
        Scanner sc = new Scanner(System.in);
        System.out.print("How many players? ");
        int count = sc.nextInt();
        cards = new ArrayList<>();
        char patternChosen;
        for(int i = 0; i < count; i++){
            cards.add(new BingoCard(i + 1));
        }
        for(BingoCard card: cards){
            System.out.println("Card " + card.id);
            System.out.println(card);
        }
        List<Thread> threads = new ArrayList<>();
        System.out.print("Enter pattern: ");
        patternChosen = sc.next().charAt(0);
        for(BingoCard card: cards){
            switch (patternChosen){
                case '+':
                    threads.add(new Thread(new BingoPattern.BingoPatternPlus(card)));
                    break;
                case '#':
                    threads.add(new Thread(new BingoPattern.BingoPatternHash(card)));
                    break;
                default:
                    System.out.println("Incorrect Pattern");
                    return;
            }
            threads.getLast().start();
        }
        Random random = new Random();
        synchronized (results){
            while (!isBingo) {
                int randomNumber;
                do {
                    randomNumber = random.nextInt(75) + 1;
                } while (results[randomNumber]);
                results[randomNumber] = true;
                chosenNumbers.add(randomNumber);
                System.out.println("Chosen number: " + randomNumber);
                Collections.sort(chosenNumbers);
                System.out.println(chosenNumbers);

                try {
                    results.notifyAll();
                    results.wait(10);
                } catch (InterruptedException e) {
                }
            }
        }
        System.out.println(chosenNumbers);
        System.out.println();
        for(Thread checker: threads){
            checker.interrupt();
        }
    }
}
