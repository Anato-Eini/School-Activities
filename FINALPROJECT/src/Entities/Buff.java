package Entities;

public abstract class Buff {
    private int original;
    private int buffed;
    private String buffName;
    private int buffTurnsApplied;
    private int buffDuration;


    public int getOriginal() {
        return original;
    }

    public Buff setOriginal(int original) {
        this.original = original;
        return this;
    }

    public int getBuffed() {
        return buffed;
    }

    public Buff setBuffed(int buffed) {
        this.buffed = buffed;
        return this;
    }

    public String getBuffName() {
        return buffName;
    }

    public Buff setBuffName(String buffName) {
        this.buffName = buffName;
        return this;
    }

    public int getBuffTurnsApplied() {
        return buffTurnsApplied;
    }

    public Buff setBuffTurnsApplied(int buffTurnsApplied) {
        this.buffTurnsApplied = buffTurnsApplied;
        return this;
    }

    public int getBuffDuration() {
        return buffDuration;
    }

    public Buff setBuffDuration(int buffDuration) {
        this.buffDuration = buffDuration;
        return this;
    }


}
