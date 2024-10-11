package Entities;

public class DamageBuff extends Buff{
    DamageBuff(int original){
        setOriginal(original);
        setBuffed((int) (getOriginal() * 1.5));
        setBuffDuration(5);
        setBuffTurnsApplied(0);
        setBuffName("Damage Buff");
    }
}
