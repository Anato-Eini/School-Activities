package Bingo;

import java.util.ArrayList;
import java.util.List;

public abstract class BingoPattern implements Runnable{
        BingoCard card;
        List<BingoChecker> checkers;
        public BingoPattern(BingoCard card) {
            this.card = card;
            checkers = new ArrayList<>();
        }

    @Override
    public void run() {
        List<Thread> threads = new ArrayList<>();
        for(BingoChecker checker: checkers){
            threads.add(new Thread(checker));
        }

        for(Thread t: threads){
            t.start();
        }

        for(Thread t: threads){
            try {
                t.join();
            }catch (InterruptedException e){
                System.out.println("Card " + card.id + " loses");
                for(Thread thd: threads){
                    thd.interrupt();
                }
                return;
            }
        }
        System.out.println("Card won: " + card.id);
        synchronized (BingoGame.results){
            BingoGame.isBingo = true;
        }
    }

    public static class BingoPatternPlus extends BingoPattern{
            public BingoPatternPlus(BingoCard card){
                super(card);
                checkers.add(new BingoChecker.BingoRowChecker(card, 2));
                checkers.add(new BingoChecker.BingoColumnChecker(card, 2));
            }
        }

        public static class BingoPatternHash extends BingoPattern{
            public BingoPatternHash(BingoCard card){
                super(card);
                checkers.add(new BingoChecker.BingoColumnChecker(card, 1));
                checkers.add(new BingoChecker.BingoColumnChecker(card, 3));
                checkers.add(new BingoChecker.BingoRowChecker(card, 1));
                checkers.add(new BingoChecker.BingoRowChecker(card, 3));
            }
        }
}
