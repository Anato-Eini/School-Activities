import javax.swing.*;
import Entities.*;
import java.util.Scanner;
public class Main extends JFrame {
    public static void main(String[] args) { //sorry testing sa ko sa text
        /*game app = new game();
        app.setTitle("UGANG LEGENDS");
        app.setSize(1000,500);
        app.setDefaultCloseOperation(EXIT_ON_CLOSE);
        app.setVisible(true);
        System.out.println("I love intellij"); */
        System.out.println("Welcome to ugang legends");
        GameBehavior gb = new GameBehavior();
        Entity currentPlayer = gb.characters.party.get(0);
        int choice;
        boolean gameOver = false;
        Scanner scanner = new Scanner(System.in);
        while(!gameOver){
            System.out.print("Enter choice (0 - Switch, 1 - Skill1, 2 - Skill2, 3 -  Skill3, 4- Skill4)");
            choice = scanner.nextInt();
            switch(choice){
                case 0:
                    System.out.print("Switch to which player (0-3)");
                    choice = scanner.nextInt();
                    currentPlayer = gb.characters.party.get(choice);
                    break;
                case 1:
                    if(gb.boss.getDebuff() != null){
                        if(gb.boss.getDebuff().getDebuffName().equals("Stun")){
                            currentPlayer.skill1(gb.boss);
                            gb.boss.getDebuff().setTurnsApplied(gb.boss.getDebuff().getTurnsApplied() + 1);
                            if(gb.boss.getDebuff().getTurnsApplied() >= gb.boss.getDebuff().getDuration()){
                                gb.boss.setDebuff(null);
                            }
                            if(gb.boss.isDead()){
                                System.out.println("You win");
                                gameOver = true;
                            }
                            System.out.println(currentPlayer);
                            System.out.println(gb.boss);

                            break;
                        } else{
                            gb.boss.setHp(gb.boss.getHp() - 50);
                            gb.boss.getDebuff().setTurnsApplied(gb.boss.getDebuff().getTurnsApplied() + 1);
                            if(gb.boss.getDebuff().getTurnsApplied() >= gb.boss.getDebuff().getDuration()){
                                gb.boss.setDebuff(null);
                            }
                        }
                    }
                    //spacer
                    if(currentPlayer.getDebuff() != null){
                        if(currentPlayer.getDebuff().getDebuffName().equals("Stun")){
                            bossAttack(gb,currentPlayer);
                            currentPlayer.getDebuff().setTurnsApplied(currentPlayer.getDebuff().getTurnsApplied() + 1);
                            if(currentPlayer.getDebuff().getTurnsApplied() >= currentPlayer.getDebuff().getDuration()){
                                currentPlayer.setDebuff(null);
                            }
                            if(currentPlayer.isDead()){
                                System.out.println("You lose");
                                gameOver = true;
                            }
                            System.out.println(currentPlayer);
                            System.out.println(gb.boss);
                            break;
                        } else{
                            currentPlayer.setHp(currentPlayer.getHp() - 50);
                            currentPlayer.getDebuff().setTurnsApplied(currentPlayer.getDebuff().getTurnsApplied() + 1);
                            if(currentPlayer.getDebuff().getTurnsApplied() >= currentPlayer.getDebuff().getDuration()){
                                currentPlayer.setDebuff(null);
                            }
                        }
                    }
                    if(currentPlayer.getSpeed() > gb.boss.getSpeed()){
                        currentPlayer.skill1(gb.boss);
                        if(gb.boss.isDead()){
                            System.out.println("You win");
                            gameOver = true;
                        }
                        bossAttack(gb,currentPlayer);
                        if(currentPlayer.isDead()){
                            System.out.println("You lose");
                            gameOver = true;
                        }
                    } else{
                        bossAttack(gb,currentPlayer);
                        if(currentPlayer.isDead()){
                            System.out.println("You lose");
                            gameOver = true;
                        }
                        currentPlayer.skill1(gb.boss);
                        if(gb.boss.isDead()){
                            System.out.println("You win");
                            gameOver = true;
                        }
                    }
                    System.out.println(currentPlayer);
                    System.out.println(gb.boss);
                    break;
                case 2:
                    if(gb.boss.getDebuff() != null){
                        if(gb.boss.getDebuff().getDebuffName().equals("Stun")){
                            currentPlayer.skill2(gb.boss);
                            gb.boss.getDebuff().setTurnsApplied(gb.boss.getDebuff().getTurnsApplied() + 1);
                            if(gb.boss.getDebuff().getTurnsApplied() >= gb.boss.getDebuff().getDuration()){
                                gb.boss.setDebuff(null);
                            }
                            if(gb.boss.isDead()){
                                System.out.println("You win");
                                gameOver = true;
                            }
                            System.out.println(currentPlayer);
                            System.out.println(gb.boss);
                            break;
                        } else{
                            gb.boss.setHp(gb.boss.getHp() - 50);
                            gb.boss.getDebuff().setTurnsApplied(gb.boss.getDebuff().getTurnsApplied() + 1);
                            if(gb.boss.getDebuff().getTurnsApplied() >= gb.boss.getDebuff().getDuration()){
                                gb.boss.setDebuff(null);
                            }
                        }
                    }
                    //spacer
                    if(currentPlayer.getDebuff() != null){
                        if(currentPlayer.getDebuff().getDebuffName().equals("Stun")){
                            bossAttack(gb,currentPlayer);
                            currentPlayer.getDebuff().setTurnsApplied(currentPlayer.getDebuff().getTurnsApplied() + 1);
                            if(currentPlayer.getDebuff().getTurnsApplied() >= currentPlayer.getDebuff().getDuration()){
                                currentPlayer.setDebuff(null);
                            }
                            if(currentPlayer.isDead()){
                                System.out.println("You lose");
                                gameOver = true;
                            }
                            System.out.println(currentPlayer);
                            System.out.println(gb.boss);
                            break;
                        } else{
                            currentPlayer.setHp(currentPlayer.getHp() - 50);
                            currentPlayer.getDebuff().setTurnsApplied(currentPlayer.getDebuff().getTurnsApplied() + 1);
                            if(currentPlayer.getDebuff().getTurnsApplied() >= currentPlayer.getDebuff().getDuration()){
                                currentPlayer.setDebuff(null);
                            }
                        }
                    }
                    if(currentPlayer.getSpeed() > gb.boss.getSpeed()){
                        currentPlayer.skill2(gb.boss);
                        if(gb.boss.isDead()){
                            System.out.println("You win");
                            gameOver = true;
                        }
                        bossAttack(gb,currentPlayer);
                        if(currentPlayer.isDead()){
                            System.out.println("You lose");
                            gameOver = true;
                        }
                    } else{
                        bossAttack(gb,currentPlayer);
                        if(currentPlayer.isDead()){
                            System.out.println("You lose");
                            gameOver = true;
                        }
                        currentPlayer.skill2(gb.boss);
                        if(gb.boss.isDead()){
                            System.out.println("You win");
                            gameOver = true;
                        }
                    }
                    System.out.println(currentPlayer);
                    System.out.println(gb.boss);
                    break;
                case 3:
                    if(gb.boss.getDebuff() != null){
                        if(gb.boss.getDebuff().getDebuffName().equals("Stun")){
                            currentPlayer.skill3(gb.characters);
                            gb.boss.getDebuff().setTurnsApplied(gb.boss.getDebuff().getTurnsApplied() + 1);
                            if(gb.boss.getDebuff().getTurnsApplied() >= gb.boss.getDebuff().getDuration()){
                                gb.boss.setDebuff(null);
                            }
                            if(gb.boss.isDead()){
                                System.out.println("You win");
                                gameOver = true;
                            }
                            System.out.println(currentPlayer);
                            System.out.println(gb.boss);
                            break;
                        } else{
                            gb.boss.setHp(gb.boss.getHp() - 50);
                            gb.boss.getDebuff().setTurnsApplied(gb.boss.getDebuff().getTurnsApplied() + 1);
                            if(gb.boss.getDebuff().getTurnsApplied() >= gb.boss.getDebuff().getDuration()){
                                gb.boss.setDebuff(null);
                            }
                        }
                    }
                    //spacer
                    if(currentPlayer.getDebuff() != null){
                        if(currentPlayer.getDebuff().getDebuffName().equals("Stun")){
                            bossAttack(gb,currentPlayer);
                            currentPlayer.getDebuff().setTurnsApplied(currentPlayer.getDebuff().getTurnsApplied() + 1);
                            if(currentPlayer.getDebuff().getTurnsApplied() >= currentPlayer.getDebuff().getDuration()){
                                currentPlayer.setDebuff(null);
                            }
                            if(currentPlayer.isDead()){
                                System.out.println("You lose");
                                gameOver = true;
                            }
                            System.out.println(currentPlayer);
                            System.out.println(gb.boss);
                            break;
                        } else{
                            currentPlayer.setHp(currentPlayer.getHp() - 50);
                            currentPlayer.getDebuff().setTurnsApplied(currentPlayer.getDebuff().getTurnsApplied() + 1);
                            if(currentPlayer.getDebuff().getTurnsApplied() >= currentPlayer.getDebuff().getDuration()){
                                currentPlayer.setDebuff(null);
                            }
                        }
                    }
                    if(currentPlayer.getSpeed() > gb.boss.getSpeed()){
                        currentPlayer.skill3(gb.characters);
                        bossAttack(gb,currentPlayer);
                        if(currentPlayer.isDead()){
                            System.out.println("You lose");
                            gameOver = true;
                        }
                    } else{
                        bossAttack(gb,currentPlayer);
                        if(currentPlayer.isDead()){
                            System.out.println("You lose");
                            gameOver = true;
                        }
                        currentPlayer.skill3(gb.characters);
                    }
                    System.out.println(currentPlayer);
                    System.out.println(gb.boss);
                    break;
                case 4:
                    if(gb.boss.getDebuff() != null){
                        if(gb.boss.getDebuff().getDebuffName().equals("Stun")){
                            currentPlayer.skill4(gb.boss);
                            gb.boss.getDebuff().setTurnsApplied(gb.boss.getDebuff().getTurnsApplied() + 1);
                            if(gb.boss.getDebuff().getTurnsApplied() >= gb.boss.getDebuff().getDuration()){
                                gb.boss.setDebuff(null);
                            }
                            if(gb.boss.isDead()){
                                System.out.println("You win");
                                gameOver = true;
                            }
                            System.out.println(currentPlayer);
                            System.out.println(gb.boss);
                            break;
                        } else{
                            gb.boss.setHp(gb.boss.getHp() - 50);
                            gb.boss.getDebuff().setTurnsApplied(gb.boss.getDebuff().getTurnsApplied() + 1);
                            if(gb.boss.getDebuff().getTurnsApplied() >= gb.boss.getDebuff().getDuration()){
                                gb.boss.setDebuff(null);
                            }
                        }
                    }
                    //spacer
                    if(currentPlayer.getDebuff() != null){
                        if(currentPlayer.getDebuff().getDebuffName().equals("Stun")){
                            bossAttack(gb,currentPlayer);
                            currentPlayer.getDebuff().setTurnsApplied(currentPlayer.getDebuff().getTurnsApplied() + 1);
                            if(currentPlayer.getDebuff().getTurnsApplied() >= currentPlayer.getDebuff().getDuration()){
                                currentPlayer.setDebuff(null);
                            }
                            if(currentPlayer.isDead()){
                                System.out.println("You lose");
                                gameOver = true;
                            }
                            System.out.println(currentPlayer);
                            System.out.println(gb.boss);
                            break;
                        } else{
                            currentPlayer.setHp(currentPlayer.getHp() - 50);
                            currentPlayer.getDebuff().setTurnsApplied(currentPlayer.getDebuff().getTurnsApplied() + 1);
                            if(currentPlayer.getDebuff().getTurnsApplied() >= currentPlayer.getDebuff().getDuration()){
                                currentPlayer.setDebuff(null);
                            }
                        }
                    }
                    if(currentPlayer.getSpeed() > gb.boss.getSpeed()){
                        currentPlayer.skill4(gb.boss);
                        if(gb.boss.isDead()){
                            System.out.println("You win");
                            gameOver = true;
                        }
                        bossAttack(gb,currentPlayer);
                        if(currentPlayer.isDead()){
                            System.out.println("You lose");
                            gameOver = true;
                        }
                    } else{
                        bossAttack(gb,currentPlayer);
                        if(currentPlayer.isDead()){
                            System.out.println("You lose");
                            gameOver = true;
                        }
                        currentPlayer.skill4(gb.boss);
                        if(gb.boss.isDead()){
                            System.out.println("You win");
                            gameOver = true;
                        }
                    }
                    System.out.println(currentPlayer);
                    System.out.println(gb.boss);
                    break;
            }

        }
    }
    public static void bossAttack(GameBehavior gb, Entity currentPlayer){
        int min = 1; // Minimum value of range
        int max = 4; // Maximum value of range
        System.out.println(currentPlayer.getName() + " " + currentPlayer.getHp());

        int random_int = (int)Math.floor(Math.random() * (max - min + 1) + min);
        System.out.println(random_int);

        switch(random_int){
            case 1:
                gb.boss.skill1(currentPlayer);
                break;
            case 2:
                gb.boss.skill2(currentPlayer);
                break;
            case 3:
                gb.boss.skill3(gb.characters);
                break;
            case 4:
                gb.boss.skill4(currentPlayer);
                break;
        }
        System.out.println(currentPlayer.getHp());
    }
}