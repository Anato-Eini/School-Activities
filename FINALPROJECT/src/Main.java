import javax.swing.*;
import Entities.*;
import java.util.Scanner;
public class Main extends JFrame {
    public static void main(String[] args) { //sorry testing sa ko sa text
        //comment this out
        game app = new game();
        app.setTitle("UGANG LEGENDS");
        app.setSize(1000,1000);
        app.setDefaultCloseOperation(EXIT_ON_CLOSE);
        app.setVisible(true);
        System.out.println("I love intellij");
        //

        System.out.println("Welcome to ugang legends");
        GameBehavior gb = new GameBehavior();
        Entity currentPlayer = gb.characters.party.get(0);
        int choice;
        boolean gameOver = false;
        gb.setTotalTime(0);
        Scanner scanner = new Scanner(System.in);
        while(!gameOver){
            gb.setTotalTime(gb.getTotalTime() + 1);
            System.out.print("Enter choice (0 - Switch, 1 - Skill1, 2 - Skill2, 3 -  Skill3, 4- Skill4): ");
            choice = scanner.nextInt();
            switch(choice){
                case 0:
                    if(currentPlayer.getSkill3cd() > 0){
                        currentPlayer.setSkill3cd(currentPlayer.getSkill3cd() - 1);
                        System.out.println("Cooldown skill 3: " + currentPlayer.getSkill3cd());
                    }
                    if(currentPlayer.getSkill4cd() > 0){
                        currentPlayer.setSkill4cd(currentPlayer.getSkill4cd() - 1);
                        System.out.println("Cooldown skill 4: " + currentPlayer.getSkill4cd());
                    }
                    if(currentPlayer.getBuff() != null){
                        if(currentPlayer.getBuff().getBuffName().equals("Damage Buff")){
                            if(currentPlayer.getBuff().getBuffTurnsApplied() == 0){
                                int before = currentPlayer.getBaseDmg();
                                currentPlayer.getBuff().setOriginal(before);
                                System.out.println("Before dmg buff: " + currentPlayer.getBaseDmg());
                                currentPlayer.setBaseDmg(currentPlayer.getBuff().getBuffed());
                                System.out.println("After dmg buff: " + currentPlayer.getBaseDmg());
                            }
                            currentPlayer.getBuff().setBuffTurnsApplied(currentPlayer.getBuff().getBuffTurnsApplied() + 1);
                            System.out.println("How many turns? " + currentPlayer.getBuff().getBuffTurnsApplied());
                            if(currentPlayer.getBuff().getBuffTurnsApplied() >= currentPlayer.getBuff().getBuffDuration()){
                                currentPlayer.setBaseDmg(currentPlayer.getBuff().getOriginal());
                                System.out.println("Buff is over, base dmg is " + currentPlayer.getBaseDmg());
                                currentPlayer.setBuff(null);
                            }
                        }
                    }
                    //
                    if(gb.boss.getDebuff() != null){
                        if(gb.boss.getDebuff().getDebuffName().equals("Stun")){
                            System.out.println("Boss stunned");
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
                            System.out.println("Boss poisoned");
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
                            System.out.println("You are stunned");
                            bossAttack(gb,currentPlayer);
                            currentPlayer.getDebuff().setTurnsApplied(currentPlayer.getDebuff().getTurnsApplied() + 1);
                            if(currentPlayer.getDebuff().getTurnsApplied() >= currentPlayer.getDebuff().getDuration()){
                                currentPlayer.setDebuff(null);
                            }
                            if(currentPlayer.isDead()){
                                System.out.println("You are dead");
                                if(gb.characters.isWipedOut()){
                                    System.out.println("You lose");
                                    gameOver = true;
                                } else{
                                    do{
                                        System.out.print("Switch to which player (0-3): ");
                                        choice = scanner.nextInt();
                                        currentPlayer = gb.characters.party.get(choice);
                                        if(currentPlayer.isDead()){
                                            System.out.println("Dead character, choose another");
                                        }
                                    }while(currentPlayer.isDead());
                                }
                            }
                            System.out.println(currentPlayer);
                            System.out.println(gb.boss);
                            break;
                        } else{
                            System.out.println("You are currently poisoned");
                            currentPlayer.setHp(currentPlayer.getHp() - 50);
                            currentPlayer.getDebuff().setTurnsApplied(currentPlayer.getDebuff().getTurnsApplied() + 1);
                            if(currentPlayer.getDebuff().getTurnsApplied() >= currentPlayer.getDebuff().getDuration()){
                                currentPlayer.setDebuff(null);
                            }
                            if(currentPlayer.isDead()){
                                System.out.println("You are dead");
                                if(gb.characters.isWipedOut()){
                                    System.out.println("You lose");
                                    gameOver = true;
                                }
                            }
                        }
                    }
                    do{
                        System.out.print("Switch to which player (0-3): ");
                        choice = scanner.nextInt();
                        currentPlayer = gb.characters.party.get(choice);
                        if(currentPlayer.isDead()){
                            System.out.println("Dead character, choose another");
                        }
                    }while(currentPlayer.isDead());
                    bossAttack(gb,currentPlayer);
                    if(currentPlayer.isDead()){
                        System.out.println("You are dead");
                        if(gb.characters.isWipedOut()){
                            System.out.println("You lose");
                            gameOver = true;
                        } else{
                            do{
                                System.out.print("Switch to which player (0-3): ");
                                choice = scanner.nextInt();
                                currentPlayer = gb.characters.party.get(choice);
                                if(currentPlayer.isDead()){
                                    System.out.println("Dead character, choose another");
                                }
                            }while(currentPlayer.isDead());
                        }
                    }
                    break;
                case 1:
                    if(currentPlayer.getSkill3cd() > 0){
                        currentPlayer.setSkill3cd(currentPlayer.getSkill3cd() - 1);
                        System.out.println("Cooldown skill 3: " + currentPlayer.getSkill3cd());
                    }
                    if(currentPlayer.getSkill4cd() > 0){
                        currentPlayer.setSkill4cd(currentPlayer.getSkill4cd() - 1);
                        System.out.println("Cooldown skill 4: " + currentPlayer.getSkill4cd());
                    }
                    if(currentPlayer.getBuff() != null){
                        if(currentPlayer.getBuff().getBuffName().equals("Damage Buff")){
                            if(currentPlayer.getBuff().getBuffTurnsApplied() == 0){
                                int before = currentPlayer.getBaseDmg();
                                currentPlayer.getBuff().setOriginal(before);
                                System.out.println("Before dmg buff: " + currentPlayer.getBaseDmg());
                                currentPlayer.setBaseDmg(currentPlayer.getBuff().getBuffed());
                                System.out.println("After dmg buff: " + currentPlayer.getBaseDmg());
                            }
                            currentPlayer.getBuff().setBuffTurnsApplied(currentPlayer.getBuff().getBuffTurnsApplied() + 1);
                            System.out.println("How many turns? " + currentPlayer.getBuff().getBuffTurnsApplied());
                            if(currentPlayer.getBuff().getBuffTurnsApplied() >= currentPlayer.getBuff().getBuffDuration()){
                                currentPlayer.setBaseDmg(currentPlayer.getBuff().getOriginal());
                                System.out.println("Buff is over, base dmg is " + currentPlayer.getBaseDmg());
                                currentPlayer.setBuff(null);
                            }
                        }
                    }
                    //
                    if(gb.boss.getDebuff() != null){
                        if(gb.boss.getDebuff().getDebuffName().equals("Stun")){
                            System.out.println("Boss stunned");
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
                            System.out.println("Boss poisoned");
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
                            System.out.println("You are stunned");
                            bossAttack(gb,currentPlayer);
                            currentPlayer.getDebuff().setTurnsApplied(currentPlayer.getDebuff().getTurnsApplied() + 1);
                            if(currentPlayer.getDebuff().getTurnsApplied() >= currentPlayer.getDebuff().getDuration()){
                                currentPlayer.setDebuff(null);
                            }
                            if(currentPlayer.isDead()){
                                System.out.println("You are dead");
                                if(gb.characters.isWipedOut()){
                                    System.out.println("You lose");
                                    gameOver = true;
                                } else{
                                    do{
                                        System.out.print("Switch to which player (0-3): ");
                                        choice = scanner.nextInt();
                                        currentPlayer = gb.characters.party.get(choice);
                                        if(currentPlayer.isDead()){
                                            System.out.println("Dead character, choose another");
                                        }
                                    }while(currentPlayer.isDead());
                                }
                            }
                            System.out.println(currentPlayer);
                            System.out.println(gb.boss);
                            break;
                        } else{
                            System.out.println("You are currently poisoned");
                            currentPlayer.setHp(currentPlayer.getHp() - 50);
                            currentPlayer.getDebuff().setTurnsApplied(currentPlayer.getDebuff().getTurnsApplied() + 1);
                            if(currentPlayer.getDebuff().getTurnsApplied() >= currentPlayer.getDebuff().getDuration()){
                                currentPlayer.setDebuff(null);
                            }
                            if(currentPlayer.isDead()){
                                System.out.println("You are dead");
                                if(gb.characters.isWipedOut()){
                                    System.out.println("You lose");
                                    gameOver = true;
                                } else{
                                    do{
                                        System.out.print("Switch to which player (0-3): ");
                                        choice = scanner.nextInt();
                                        currentPlayer = gb.characters.party.get(choice);
                                        if(currentPlayer.isDead()){
                                            System.out.println("Dead character, choose another");
                                        }
                                    }while(currentPlayer.isDead());
                                }
                            }
                        }
                    }
                    //spacer
                    //spacer
                    if(currentPlayer.getSpeed() > gb.boss.getSpeed()){
                        currentPlayer.skill1(gb.boss);
                        if(gb.boss.isDead()){
                            System.out.println("You win");
                            gameOver = true;
                        }
                        bossAttack(gb,currentPlayer);
                        if(currentPlayer.isDead()){
                            System.out.println("You are dead");
                            if(gb.characters.isWipedOut()){
                                System.out.println("You lose");
                                gameOver = true;
                            } else{
                                do{
                                    System.out.print("Switch to which player (0-3): ");
                                    choice = scanner.nextInt();
                                    currentPlayer = gb.characters.party.get(choice);
                                    if(currentPlayer.isDead()){
                                        System.out.println("Dead character, choose another");
                                    }
                                }while(currentPlayer.isDead());
                            }
                        }
                    } else{
                        bossAttack(gb,currentPlayer);
                        if(currentPlayer.isDead()){
                            System.out.println("You are dead");
                            if(gb.characters.isWipedOut()){
                                System.out.println("You lose");
                                gameOver = true;
                            } else{
                                do{
                                    System.out.print("Switch to which player (0-3): ");
                                    choice = scanner.nextInt();
                                    currentPlayer = gb.characters.party.get(choice);
                                    if(currentPlayer.isDead()){
                                        System.out.println("Dead character, choose another");
                                    }
                                }while(currentPlayer.isDead());
                            }
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
                    if(currentPlayer.getSkill3cd() > 0){
                        currentPlayer.setSkill3cd(currentPlayer.getSkill3cd() - 1);
                        System.out.println("Cooldown skill 3: " + currentPlayer.getSkill3cd());
                    }
                    if(currentPlayer.getSkill4cd() > 0){
                        currentPlayer.setSkill4cd(currentPlayer.getSkill4cd() - 1);
                        System.out.println("Cooldown skill 4: " + currentPlayer.getSkill4cd());
                    }
                    if(currentPlayer.getBuff() != null){
                        if(currentPlayer.getBuff().getBuffName().equals("Damage Buff")){
                            if(currentPlayer.getBuff().getBuffTurnsApplied() == 0){
                                int before = currentPlayer.getBaseDmg();
                                currentPlayer.getBuff().setOriginal(before);
                                System.out.println("Before dmg buff: " + currentPlayer.getBaseDmg());
                                currentPlayer.setBaseDmg(currentPlayer.getBuff().getBuffed());
                                System.out.println("After dmg buff: " + currentPlayer.getBaseDmg());
                            }
                            currentPlayer.getBuff().setBuffTurnsApplied(currentPlayer.getBuff().getBuffTurnsApplied() + 1);
                            System.out.println("How many turns? " + currentPlayer.getBuff().getBuffTurnsApplied());
                            if(currentPlayer.getBuff().getBuffTurnsApplied() >= currentPlayer.getBuff().getBuffDuration()){
                                currentPlayer.setBaseDmg(currentPlayer.getBuff().getOriginal());
                                System.out.println("Buff is over, base dmg is " + currentPlayer.getBaseDmg());
                                currentPlayer.setBuff(null);
                            }
                        }
                    }
                    if(gb.boss.getDebuff() != null){
                        if(gb.boss.getDebuff().getDebuffName().equals("Stun")){
                            System.out.println("Boss stunned");
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
                            System.out.println("Boss poisoned");
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
                            System.out.println("You are stunned");
                            bossAttack(gb,currentPlayer);
                            currentPlayer.getDebuff().setTurnsApplied(currentPlayer.getDebuff().getTurnsApplied() + 1);
                            if(currentPlayer.getDebuff().getTurnsApplied() >= currentPlayer.getDebuff().getDuration()){
                                currentPlayer.setDebuff(null);
                            }
                            if(currentPlayer.isDead()){
                                System.out.println("You are dead");
                                if(gb.characters.isWipedOut()){
                                    System.out.println("You lose");
                                    gameOver = true;
                                } else{
                                    do{
                                        System.out.print("Switch to which player (0-3): ");
                                        choice = scanner.nextInt();
                                        currentPlayer = gb.characters.party.get(choice);
                                        if(currentPlayer.isDead()){
                                            System.out.println("Dead character, choose another");
                                        }
                                    }while(currentPlayer.isDead());
                                }
                            }
                            System.out.println(currentPlayer);
                            System.out.println(gb.boss);
                            break;
                        } else{
                            System.out.println("You are poisoned");
                            currentPlayer.setHp(currentPlayer.getHp() - 50);
                            currentPlayer.getDebuff().setTurnsApplied(currentPlayer.getDebuff().getTurnsApplied() + 1);
                            if(currentPlayer.getDebuff().getTurnsApplied() >= currentPlayer.getDebuff().getDuration()){
                                currentPlayer.setDebuff(null);
                            }
                            if(currentPlayer.isDead()){
                                System.out.println("You are dead");
                                if(gb.characters.isWipedOut()){
                                    System.out.println("You lose");
                                    gameOver = true;
                                } else{
                                    do{
                                        System.out.print("Switch to which player (0-3): ");
                                        choice = scanner.nextInt();
                                        currentPlayer = gb.characters.party.get(choice);
                                        if(currentPlayer.isDead()){
                                            System.out.println("Dead character, choose another");
                                        }
                                    }while(currentPlayer.isDead());
                                }
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
                            System.out.println("You are dead");
                            if(gb.characters.isWipedOut()){
                                System.out.println("You lose");
                                gameOver = true;
                            } else{
                                do{
                                    System.out.print("Switch to which player (0-3): ");
                                    choice = scanner.nextInt();
                                    currentPlayer = gb.characters.party.get(choice);
                                    if(currentPlayer.isDead()){
                                        System.out.println("Dead character, choose another");
                                    }
                                }while(currentPlayer.isDead());
                            }
                        }
                    } else{
                        bossAttack(gb,currentPlayer);
                        if(currentPlayer.isDead()){
                            System.out.println("You are dead");
                            if(gb.characters.isWipedOut()){
                                System.out.println("You lose");
                                gameOver = true;
                            } else{
                                do{
                                    System.out.print("Switch to which player (0-3): ");
                                    choice = scanner.nextInt();
                                    currentPlayer = gb.characters.party.get(choice);
                                    if(currentPlayer.isDead()){
                                        System.out.println("Dead character, choose another");
                                    }
                                }while(currentPlayer.isDead());
                            }
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
                    if(currentPlayer.getSkill4cd() > 0){
                        currentPlayer.setSkill4cd(currentPlayer.getSkill4cd() - 1);
                        System.out.println("Cooldown skill 4: " + currentPlayer.getSkill4cd());
                    }
                    if(currentPlayer.getBuff() != null){
                        if(currentPlayer.getBuff().getBuffName().equals("Damage Buff")){
                            if(currentPlayer.getBuff().getBuffTurnsApplied() == 0){
                                int before = currentPlayer.getBaseDmg();
                                currentPlayer.getBuff().setOriginal(before);
                                System.out.println("Before dmg buff: " + currentPlayer.getBaseDmg());
                                currentPlayer.setBaseDmg(currentPlayer.getBuff().getBuffed());
                                System.out.println("After dmg buff: " + currentPlayer.getBaseDmg());
                            }
                            currentPlayer.getBuff().setBuffTurnsApplied(currentPlayer.getBuff().getBuffTurnsApplied() + 1);
                            System.out.println("How many turns? " + currentPlayer.getBuff().getBuffTurnsApplied());
                            if(currentPlayer.getBuff().getBuffTurnsApplied() >= currentPlayer.getBuff().getBuffDuration()){
                                currentPlayer.setBaseDmg(currentPlayer.getBuff().getOriginal());
                                System.out.println("Buff is over, base dmg is " + currentPlayer.getBaseDmg());
                                currentPlayer.setBuff(null);
                            }
                        }
                    }
                    if(gb.boss.getDebuff() != null){
                        if(gb.boss.getDebuff().getDebuffName().equals("Stun")){
                            if(currentPlayer.getSkill3cd() > 0){
                                currentPlayer.setSkill3cd(currentPlayer.getSkill3cd() - 1);
                                System.out.println("Cooldown skill 3: " + currentPlayer.getSkill3cd());
                            }
                            System.out.println("Boss stunned");
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
                            System.out.println("Boss poisoned");
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
                            if(currentPlayer.getSkill3cd() > 0){
                                currentPlayer.setSkill3cd(currentPlayer.getSkill3cd() - 1);
                                System.out.println("Cooldown skill 3: " + currentPlayer.getSkill3cd());
                            }
                            System.out.println("You are stunned");
                            bossAttack(gb,currentPlayer);
                            currentPlayer.getDebuff().setTurnsApplied(currentPlayer.getDebuff().getTurnsApplied() + 1);
                            if(currentPlayer.getDebuff().getTurnsApplied() >= currentPlayer.getDebuff().getDuration()){
                                currentPlayer.setDebuff(null);
                            }
                            if(currentPlayer.isDead()){
                                System.out.println("You are dead");
                                if(gb.characters.isWipedOut()){
                                    System.out.println("You lose");
                                    gameOver = true;
                                } else{
                                    do{
                                        System.out.print("Switch to which player (0-3): ");
                                        choice = scanner.nextInt();
                                        currentPlayer = gb.characters.party.get(choice);
                                        if(currentPlayer.isDead()){
                                            System.out.println("Dead character, choose another");
                                        }
                                    }while(currentPlayer.isDead());
                                }
                            }
                            System.out.println(currentPlayer);
                            System.out.println(gb.boss);
                            break;
                        } else{
                            System.out.println("You are poisoned");
                            currentPlayer.setHp(currentPlayer.getHp() - 50);
                            currentPlayer.getDebuff().setTurnsApplied(currentPlayer.getDebuff().getTurnsApplied() + 1);
                            if(currentPlayer.getDebuff().getTurnsApplied() >= currentPlayer.getDebuff().getDuration()){
                                currentPlayer.setDebuff(null);
                            }
                            if(currentPlayer.isDead()){
                                System.out.println("You are dead");
                                if(gb.characters.isWipedOut()){
                                    System.out.println("You lose");
                                    gameOver = true;
                                } else{
                                    do{
                                        System.out.print("Switch to which player (0-3): ");
                                        choice = scanner.nextInt();
                                        currentPlayer = gb.characters.party.get(choice);
                                        if(currentPlayer.isDead()){
                                            System.out.println("Dead character, choose another");
                                        }
                                    }while(currentPlayer.isDead());
                                }
                            }
                        }
                    }
                    if(currentPlayer.getSkill3cd() > 0){
                        currentPlayer.setSkill3cd(currentPlayer.getSkill3cd() - 1);
                        System.out.println("Cooldown skill 3: " + currentPlayer.getSkill3cd());
                        gb.setTotalTime(gb.getTotalTime() - 1);
                        break;
                    }
                    System.out.println("Successfully using skill");
                    if(currentPlayer.getSpeed() > gb.boss.getSpeed()){
                        currentPlayer.skill1(gb.boss);
                        bossAttack(gb,currentPlayer);
                        if(currentPlayer.isDead()){
                            System.out.println("You are dead");
                            if(gb.characters.isWipedOut()){
                                System.out.println("You lose");
                                gameOver = true;
                            } else{
                                do{
                                    System.out.print("Switch to which player (0-3): ");
                                    choice = scanner.nextInt();
                                    currentPlayer = gb.characters.party.get(choice);
                                    if(currentPlayer.isDead()){
                                        System.out.println("Dead character, choose another");
                                    }
                                }while(currentPlayer.isDead());
                            }
                        }
                    } else{
                        bossAttack(gb,currentPlayer);
                        if(currentPlayer.isDead()){
                            System.out.println("You are dead");
                            if(gb.characters.isWipedOut()){
                                System.out.println("You lose");
                                gameOver = true;
                            } else{
                                do{
                                    System.out.print("Switch to which player (0-3): ");
                                    choice = scanner.nextInt();
                                    currentPlayer = gb.characters.party.get(choice);
                                    if(currentPlayer.isDead()){
                                        System.out.println("Dead character, choose another");
                                    }
                                }while(currentPlayer.isDead());
                            }
                        }
                        currentPlayer.skill3(gb.characters);
                    }


                    currentPlayer.setSkill3cd(currentPlayer.getSkill3RealCd());
                    System.out.println("This skill is now in cooldown");
                    System.out.println(currentPlayer);
                    System.out.println(gb.boss);
                    break;
                case 4:
                    if(currentPlayer.getSkill3cd() > 0){
                        currentPlayer.setSkill3cd(currentPlayer.getSkill3cd() - 1);
                        System.out.println("Cooldown skill 3: " + currentPlayer.getSkill3cd());
                    }
                    if(currentPlayer.getBuff() != null){
                        if(currentPlayer.getBuff().getBuffName().equals("Damage Buff")){
                            if(currentPlayer.getBuff().getBuffTurnsApplied() == 0){
                                int before = currentPlayer.getBaseDmg();
                                currentPlayer.getBuff().setOriginal(before);
                                System.out.println("Before dmg buff: " + currentPlayer.getBaseDmg());
                                currentPlayer.setBaseDmg(currentPlayer.getBuff().getBuffed());
                                System.out.println("After dmg buff: " + currentPlayer.getBaseDmg());
                            }
                            currentPlayer.getBuff().setBuffTurnsApplied(currentPlayer.getBuff().getBuffTurnsApplied() + 1);
                            System.out.println("How many turns? " + currentPlayer.getBuff().getBuffTurnsApplied());
                            if(currentPlayer.getBuff().getBuffTurnsApplied() >= currentPlayer.getBuff().getBuffDuration()){
                                currentPlayer.setBaseDmg(currentPlayer.getBuff().getOriginal());
                                System.out.println("Buff is over, base dmg is " + currentPlayer.getBaseDmg());
                                currentPlayer.setBuff(null);
                            }
                        }
                    }
                    if(gb.boss.getDebuff() != null){
                        if(gb.boss.getDebuff().getDebuffName().equals("Stun")){
                            if(currentPlayer.getSkill4cd() > 0){
                                currentPlayer.setSkill4cd(currentPlayer.getSkill4cd() - 1);
                                System.out.println("Cooldown skill 4: " + currentPlayer.getSkill4cd());
                            }
                            System.out.println("Boss stunned");
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
                            System.out.println("Boss poisoned");
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
                            if(currentPlayer.getSkill4cd() > 0){
                                currentPlayer.setSkill4cd(currentPlayer.getSkill4cd() - 1);
                                System.out.println("Cooldown skill 4: " + currentPlayer.getSkill4cd());
                            }
                            System.out.println("You are stunned");
                            bossAttack(gb,currentPlayer);
                            currentPlayer.getDebuff().setTurnsApplied(currentPlayer.getDebuff().getTurnsApplied() + 1);
                            if(currentPlayer.getDebuff().getTurnsApplied() >= currentPlayer.getDebuff().getDuration()){
                                currentPlayer.setDebuff(null);
                            }
                            if(currentPlayer.isDead()){
                                System.out.println("You are dead");
                                if(gb.characters.isWipedOut()){
                                    System.out.println("You lose");
                                    gameOver = true;
                                } else{
                                    do{
                                        System.out.print("Switch to which player (0-3): ");
                                        choice = scanner.nextInt();
                                        currentPlayer = gb.characters.party.get(choice);
                                        if(currentPlayer.isDead()){
                                            System.out.println("Dead character, choose another");
                                        }
                                    }while(currentPlayer.isDead());
                                }
                            }
                            System.out.println(currentPlayer);
                            System.out.println(gb.boss);
                            break;
                        } else{
                            System.out.println("You are poisoned");
                            currentPlayer.setHp(currentPlayer.getHp() - 50);
                            currentPlayer.getDebuff().setTurnsApplied(currentPlayer.getDebuff().getTurnsApplied() + 1);
                            if(currentPlayer.getDebuff().getTurnsApplied() >= currentPlayer.getDebuff().getDuration()){
                                currentPlayer.setDebuff(null);
                            }
                            if(currentPlayer.isDead()){
                                System.out.println("You are dead");
                                if(gb.characters.isWipedOut()){
                                    System.out.println("You lose");
                                    gameOver = true;
                                } else{
                                    do{
                                        System.out.print("Switch to which player (0-3): ");
                                        choice = scanner.nextInt();
                                        currentPlayer = gb.characters.party.get(choice);
                                        if(currentPlayer.isDead()){
                                            System.out.println("Dead character, choose another");
                                        }
                                    }while(currentPlayer.isDead());
                                }
                            }
                        }
                    }
                    if(currentPlayer.getSkill4cd() > 0){
                        currentPlayer.setSkill4cd(currentPlayer.getSkill4cd() - 1);
                        System.out.println("Cooldown skill 4: " + currentPlayer.getSkill4cd());
                        gb.setTotalTime(gb.getTotalTime() - 1);
                        break;
                    }
                    System.out.println("Successfully using skill");
                    if(currentPlayer.getSpeed() > gb.boss.getSpeed()){
                        currentPlayer.skill4(gb.boss);
                        if(gb.boss.isDead()){
                            System.out.println("You win");
                            gameOver = true;
                        }
                        bossAttack(gb,currentPlayer);
                        if(currentPlayer.isDead()){
                            System.out.println("You are dead");
                            if(gb.characters.isWipedOut()){
                                System.out.println("You lose");
                                gameOver = true;
                            } else{
                                do{
                                    System.out.print("Switch to which player (0-3): ");
                                    choice = scanner.nextInt();
                                    currentPlayer = gb.characters.party.get(choice);
                                    if(currentPlayer.isDead()){
                                        System.out.println("Dead character, choose another");
                                    }
                                }while(currentPlayer.isDead());
                            }
                        }
                    } else{
                        bossAttack(gb,currentPlayer);
                        if(currentPlayer.isDead()){
                            System.out.println("You are dead");
                            if(gb.characters.isWipedOut()){
                                System.out.println("You lose");
                                gameOver = true;
                            } else{
                                do{
                                    System.out.print("Switch to which player (0-3): ");
                                    choice = scanner.nextInt();
                                    currentPlayer = gb.characters.party.get(choice);
                                    if(currentPlayer.isDead()){
                                        System.out.println("Dead character, choose another");
                                    }
                                }while(currentPlayer.isDead());
                            }
                        }
                        currentPlayer.skill4(gb.boss);
                        if(gb.boss.isDead()){
                            System.out.println("You win");
                            gameOver = true;
                        }
                    }

                    currentPlayer.setSkill4cd(currentPlayer.getSkill4RealCd());
                    System.out.println("This skill will now be in cooldown");
                    System.out.println(currentPlayer);
                    System.out.println(gb.boss);
                    break;
            }

        }
        System.out.println("Total turns: " + gb.getTotalTime());
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