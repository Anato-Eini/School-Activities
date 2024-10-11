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
        GameOverWrapper gameOver = new GameOverWrapper();
        gameOver.setState(false);
        gb.setTotalTime(0);
        Scanner scanner = new Scanner(System.in);
        while(!gameOver.state){
            gb.setTotalTime(gb.getTotalTime() + 1);
            System.out.print("Enter choice (0 - Switch, 1 - Skill1, 2 - Skill2, 3 -  Skill3, 4- Skill4): ");
            choice = scanner.nextInt();
            switch(choice){
                case 0:
                    checkSkill3Cd(currentPlayer);
                    checkSkill4Cd(currentPlayer);
                    handlePlayerBuffs(currentPlayer);
                    //
                    handleBossDebuff(gb,currentPlayer,gameOver);
                    if(gb.boss.getDebuff() != null){
                        if(gb.boss.getDebuff().getDebuffName().equals("Stun")){
                            break;
                        }
                    }
                    //spacer
                    currentPlayer = handlePlayerDebuffs(gb,currentPlayer,gameOver);
                    if(currentPlayer.getDebuff() != null){
                        if(currentPlayer.getDebuff().getDebuffName().equals("Stun")){
                            break;
                        }
                    }
                    currentPlayer = switchCharacters(gb, currentPlayer);
                    bossAttack(gb,currentPlayer);
                    if(currentPlayer.isDead()){
                        currentPlayer = handlePlayerDeath(gb,currentPlayer,gameOver);
                    }
                    System.out.println(currentPlayer);
                    System.out.println(gb.boss);
                    break;
                case 1:
                    checkSkill3Cd(currentPlayer);
                    checkSkill4Cd(currentPlayer);
                    handlePlayerBuffs(currentPlayer);
                    //
                    handleBossDebuff(gb,currentPlayer,gameOver);
                    if(gb.boss.getDebuff() != null){
                        if(gb.boss.getDebuff().getDebuffName().equals("Stun")){
                            break;
                        }
                    }
                    //spacer
                    currentPlayer = handlePlayerDebuffs(gb,currentPlayer,gameOver);
                    if(currentPlayer.getDebuff() != null){
                        if(currentPlayer.getDebuff().getDebuffName().equals("Stun")){
                            break;
                        }
                    }
                    //spacer
                    //spacer
                    if(currentPlayer.getSpeed() > gb.boss.getSpeed()){
                        currentPlayer.skill1(gb.boss);
                        if(gb.boss.isDead()){
                            System.out.println("You win");
                            gameOver.setState(true);
                        }
                        bossAttack(gb,currentPlayer);
                        if(currentPlayer.isDead()){
                            currentPlayer = handlePlayerDeath(gb,currentPlayer,gameOver);
                        }
                    } else{
                        bossAttack(gb,currentPlayer);
                        if(currentPlayer.isDead()){
                            currentPlayer = handlePlayerDeath(gb,currentPlayer,gameOver);
                        }
                        currentPlayer.skill1(gb.boss);
                        if(gb.boss.isDead()){
                            System.out.println("You win");
                            gameOver.setState(true);
                        }
                    }
                    System.out.println(currentPlayer);
                    System.out.println(gb.boss);
                    break;
                case 2:
                    checkSkill3Cd(currentPlayer);
                    checkSkill4Cd(currentPlayer);
                    handlePlayerBuffs(currentPlayer);
                    handleBossDebuff(gb,currentPlayer,gameOver);
                    if(gb.boss.getDebuff() != null){
                        if(gb.boss.getDebuff().getDebuffName().equals("Stun")){
                            break;
                        }
                    }
                    //spacer
                    currentPlayer = handlePlayerDebuffs(gb,currentPlayer,gameOver);
                    if(currentPlayer.getDebuff() != null){
                        if(currentPlayer.getDebuff().getDebuffName().equals("Stun")){
                            break;
                        }
                    }
                    if(currentPlayer.getSpeed() > gb.boss.getSpeed()){
                        currentPlayer.skill2(gb.boss);
                        if(gb.boss.isDead()){
                            System.out.println("You win");
                            gameOver.setState(true);
                        }
                        bossAttack(gb,currentPlayer);
                        if(currentPlayer.isDead()){
                            currentPlayer = handlePlayerDeath(gb,currentPlayer,gameOver);
                        }
                    } else{
                        bossAttack(gb,currentPlayer);
                        if(currentPlayer.isDead()){
                            currentPlayer = handlePlayerDeath(gb,currentPlayer,gameOver);
                        }
                        currentPlayer.skill2(gb.boss);
                        if(gb.boss.isDead()){
                            System.out.println("You win");
                            gameOver.setState(true);
                        }
                    }
                    System.out.println(currentPlayer);
                    System.out.println(gb.boss);
                    break;
                case 3:
                    checkSkill4Cd(currentPlayer);
                    handlePlayerBuffs(currentPlayer);
                    handleBossDebuff(gb,currentPlayer,gameOver);
                    if(gb.boss.getDebuff() != null){
                        if(gb.boss.getDebuff().getDebuffName().equals("Stun")){
                            break;
                        }
                    }
                    //spacer
                    currentPlayer = handlePlayerDebuffs(gb,currentPlayer,gameOver);
                    if(currentPlayer.getDebuff() != null){
                        if(currentPlayer.getDebuff().getDebuffName().equals("Stun")){
                            break;
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
                            currentPlayer = handlePlayerDeath(gb,currentPlayer,gameOver);
                        }
                    } else{
                        bossAttack(gb,currentPlayer);
                        if(currentPlayer.isDead()){
                            currentPlayer = handlePlayerDeath(gb,currentPlayer,gameOver);
                        }
                        currentPlayer.skill3(gb.characters);
                    }
                    currentPlayer.setSkill3cd(currentPlayer.getSkill3RealCd());
                    System.out.println("This skill is now in cooldown");
                    System.out.println(currentPlayer);
                    System.out.println(gb.boss);
                    break;
                case 4:
                    checkSkill3Cd(currentPlayer);
                    handlePlayerBuffs(currentPlayer);
                    handleBossDebuff(gb,currentPlayer,gameOver);
                    if(gb.boss.getDebuff() != null){
                        if(gb.boss.getDebuff().getDebuffName().equals("Stun")){
                            break;
                        }
                    }
                    //spacer
                    currentPlayer = handlePlayerDebuffs(gb,currentPlayer,gameOver);
                    if(currentPlayer.getDebuff() != null){
                        if(currentPlayer.getDebuff().getDebuffName().equals("Stun")){
                            break;
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
                            gameOver.setState(true);
                        }
                        bossAttack(gb,currentPlayer);
                        if(currentPlayer.isDead()){
                           currentPlayer =  handlePlayerDeath(gb,currentPlayer,gameOver);
                        }
                    } else{
                        bossAttack(gb,currentPlayer);
                        if(currentPlayer.isDead()){
                            currentPlayer = handlePlayerDeath(gb,currentPlayer,gameOver);
                        }
                        currentPlayer.skill4(gb.boss);
                        if(gb.boss.isDead()){
                            System.out.println("You win");
                            gameOver.setState(true);

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


        int random_int = (int)Math.floor(Math.random() * (max - min + 1) + min);


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

    }
    public static void checkSkill3Cd(Entity curr){
        if(curr.getSkill3cd() > 0){
            curr.setSkill3cd(curr.getSkill3cd() - 1);
            System.out.println("Cooldown skill 3: " + curr.getSkill3cd());
        }
    }
    public static void checkSkill4Cd(Entity curr){
        if(curr.getSkill4cd() > 0){
            curr.setSkill4cd(curr.getSkill4cd() - 1);
            System.out.println("Cooldown skill 4: " + curr.getSkill4cd());
        }
    }
    public static void handlePlayerBuffs(Entity curr){
        if(curr.getBuff() != null){
            if(curr.getBuff().getBuffName().equals("Damage Buff")){
                if(curr.getBuff().getBuffTurnsApplied() == 0){
                    int before = curr.getBaseDmg();
                    curr.getBuff().setOriginal(before);
                    System.out.println("Before dmg buff: " + curr.getBaseDmg());
                    curr.setBaseDmg(curr.getBuff().getBuffed());
                    System.out.println("After dmg buff: " + curr.getBaseDmg());
                }
                curr.getBuff().setBuffTurnsApplied(curr.getBuff().getBuffTurnsApplied() + 1);
                System.out.println("How many turns? " + curr.getBuff().getBuffTurnsApplied());
                if(curr.getBuff().getBuffTurnsApplied() >= curr.getBuff().getBuffDuration()){
                    curr.setBaseDmg(curr.getBuff().getOriginal());
                    System.out.println("Buff is over, base dmg is " + curr.getBaseDmg());
                    curr.setBuff(null);
                }
            }
        }
    }

    public static Entity switchCharacters(GameBehavior gameBehavior, Entity curr){
        int choice;
        Entity switchedCharacter = curr;
        Scanner scanner = new Scanner(System.in);
        do{
            System.out.print("Switch to which player (0-3): ");
            choice = scanner.nextInt();
            switchedCharacter = gameBehavior.characters.party.get(choice);
            if(switchedCharacter.isDead()){
                System.out.println("Dead character, choose another");
            }
        }while(switchedCharacter.isDead());
        return switchedCharacter;
    }
    public static Entity handlePlayerDebuffs(GameBehavior gb, Entity currentPlayer, GameOverWrapper gameOver){
        if(currentPlayer.getDebuff() != null){
            if(currentPlayer.getDebuff().getDebuffName().equals("Stun")){
                System.out.println("You are stunned");
                bossAttack(gb,currentPlayer);
                currentPlayer.getDebuff().setTurnsApplied(currentPlayer.getDebuff().getTurnsApplied() + 1);
                if(currentPlayer.getDebuff().getTurnsApplied() >= currentPlayer.getDebuff().getDuration()){
                    currentPlayer.setDebuff(null);
                }
                if(currentPlayer.isDead()){
                    handlePlayerDeath(gb,currentPlayer,gameOver);
                }
                System.out.println(currentPlayer);
                System.out.println(gb.boss);
            } else{
                System.out.println("You are currently poisoned");
                currentPlayer.setHp(currentPlayer.getHp() - 50);
                currentPlayer.getDebuff().setTurnsApplied(currentPlayer.getDebuff().getTurnsApplied() + 1);
                if(currentPlayer.getDebuff().getTurnsApplied() >= currentPlayer.getDebuff().getDuration()){
                    currentPlayer.setDebuff(null);
                }
                if(currentPlayer.isDead()){
                    currentPlayer = handlePlayerDeath(gb,currentPlayer,gameOver);
                }
            }
        }
        return currentPlayer;
    }
    public static Entity handlePlayerDeath(GameBehavior gb, Entity currentPlayer, GameOverWrapper gameOver){
        System.out.println("You are dead");
        if(gb.characters.isWipedOut()){
            System.out.println("You lose");
            gameOver.setState(true);
        } else{
            currentPlayer = switchCharacters(gb,currentPlayer);
        }
        return currentPlayer;
    }

    public static void handleBossDebuff(GameBehavior gb, Entity currentPlayer, GameOverWrapper gameOver){
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
                    gameOver.setState(true);
                }
                System.out.println(currentPlayer);
                System.out.println(gb.boss);
            } else{
                System.out.println("Boss poisoned");
                gb.boss.setHp(gb.boss.getHp() - 50);
                gb.boss.getDebuff().setTurnsApplied(gb.boss.getDebuff().getTurnsApplied() + 1);
                if(gb.boss.getDebuff().getTurnsApplied() >= gb.boss.getDebuff().getDuration()){
                    gb.boss.setDebuff(null);
                }
            }
        }
    }
    public static class GameOverWrapper{
        public boolean state;
        public void setState(boolean state){
            this.state = state;
        }
    }
}