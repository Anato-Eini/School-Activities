package Arithmethic;

public class Arithmetic {
    private final Number num1, num2;

    public Arithmetic(Number num1, Number num2) {
        this.num1 = num1;
        this.num2 = num2;
        System.out.println(STR."Num1 is \{num1.getClass().getSimpleName()}");
        System.out.println(STR."Num2 is \{num2.getClass().getSimpleName()}");
    }
    public Number add(){
        return num1.doubleValue() + num2.doubleValue();
    }

    public Number subtract(){
        return num1.doubleValue() - num2.doubleValue();
    }

    public Number multiply(){
        return num1.doubleValue() * num2.doubleValue();
    }

    public Number divide(){
        return num1.doubleValue() / num2.doubleValue();
    }

    public Number getMin(){
        return Math.min(num1.doubleValue(), num2.doubleValue());
    }

    public Number getMax(){
        return Math.max(num1.doubleValue(), num2.doubleValue());
    }

}
