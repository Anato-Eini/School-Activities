package Bingo;


import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class BingoCard {
    int[][] nums;
    int id;
    public BingoCard(int id) {
        this.id = id;
        nums = new int[5][5];
        Random random = new Random();

        for(int col = 0; col < 5; col++){
            randomizeNumbers(random, col, 15 * col + 1, 15 * (col + 1));
        }
        nums[2][2] = 0;
    }

    public void randomizeNumbers(Random random, int column, int min, int max){
        Set<Integer> integers = new HashSet<>();
        for(int row = 0; row < 5; row++){
            int randomNumber;
            do{
                randomNumber = random.nextInt(max - min + 1) + min;
            }while(integers.contains(randomNumber));
            integers.add(randomNumber);
            nums[row][column] = randomNumber;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(int row = 0; row < 5; row++){
            for(int col = 0; col < 5; col++){
                sb.append(nums[row][col]).append("\t");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
