package com.example.Model;

public abstract class Debuff {
    private String debuffName;
    private int turnsApplied;
    private int duration;

    public String getDebuffName() {
        return debuffName;
    }

    public int getTurnsApplied() {
        return turnsApplied;
    }

    public Debuff setTurnsApplied(int turnsApplied) {
        this.turnsApplied = turnsApplied;
        return this;
    }

    Debuff setDebuffName(String debuffName) {
        this.debuffName = debuffName;
        return this;
    }

    public int getDuration() {
        return duration;
    }

    public Debuff setDuration(int duration) {
        this.duration = duration;
        return this;
    }
}
