package Bingo;

public abstract class BingoChecker implements Runnable{
    BingoCard card;
    int index;
    public BingoChecker(BingoCard card){
        this.card = card;
    }

    public static class BingoRowChecker extends BingoChecker{
        public BingoRowChecker(BingoCard card, int row) {
            super(card);
            index = row;
        }

        @Override
        public void run() {
            for(int col = 0; col < 5; col++){
                int num = card.nums[index][col];
                System.out.println("Waiting for num " + num);
                while(!BingoGame.chosenNumbers.contains(num)){
                    synchronized (BingoGame.results){
                        try {
                            BingoGame.results.wait();
                        } catch (InterruptedException e) {
                            return;
                        }
                    }
                }

            }
            System.out.println("Card " + card.id + " finishes row " + index);
            System.out.println(card);
        }
    }
    public static class BingoColumnChecker extends BingoChecker{
        public BingoColumnChecker(BingoCard card, int col){
            super(card);
            index = col;
        }

        @Override
        public void run() {
            for(int row = 0; row < 5; row++){
                int num = card.nums[row][index];
                System.out.println("Waiting for num " + num);
                while(!BingoGame.chosenNumbers.contains(num)){
                    synchronized (BingoGame.results){
                        try {
                            BingoGame.results.wait();
                        } catch (InterruptedException e) {
                            return;
                        }
                    }
                }
            }
            System.out.println("Card " + card.id + " finishes col " + index);
            System.out.println(card);
        }
    }
}
