package com.example.Model;
import com.example.Controllers.battleMenuController;

import java.util.ArrayList;
public class gameMaster {
    public static GameBehavior gb = new GameBehavior();
    public static Entity currentPlayer = gb.characters.party.get(0);
    public static int choice;




    public static void start(){
        gb.setTotalTime(0);
    }

    public static void newTurn(){
        gb.setTotalTime(gb.getTotalTime() + 1);
    }

    public static class GameOverWrapper{
        public boolean state;
        public void setState(boolean state){
            this.state = state;
        }
    }






}
