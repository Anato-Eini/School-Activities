package com.example.Model;

public class GameBehavior {

    private int totalTime;
    public Boss boss;
    public Party characters = new Party();
    public GameBehavior() {
        boss = (Boss)new Boss().setName("Jay Vince Serato").setLevel(100).setHp(25000).setSpeed().setBaseDmg().setSkill3cd().setSkill4cd().setDebuffType("Stun");
        characters.party.add(new Tank().setName("James Kenneth Acabal").setHp(10000).setMaxHP(10000).setLevel(70).setSpeed().setBaseDmg().setSkill3cd().setSkill4cd().setDebuffType("Stun"));
        characters.party.add(new Fighter().setName("Jeremy Brad Lee").setHp(5000).setMaxHP(5000).setLevel(70).setSpeed().setBaseDmg().setSkill3cd().setSkill4cd().setDebuffType("Stun"));
        characters.party.add(new Mage().setName("Paul Jes Marc Flores").setHp(3500).setMaxHP(3500).setLevel(75).setSpeed().setBaseDmg().setSkill3cd().setSkill4cd().setDebuffType("Poison"));
        characters.party.add(new Support().setName("Tristan James Tolentino").setHp(5000).setMaxHP(5000).setLevel(73).setSpeed().setBaseDmg().setSkill3cd().setSkill4cd().setDebuffType("Poison"));
        this.totalTime = 0;
    }

    public int getTotalTime() {
        return totalTime;
    }

    public GameBehavior setTotalTime(int totalTime) {
        this.totalTime = totalTime;
        return this;
    }






}
