package Codes;

public class Cat {
    public String name;
    public Cat(String name){
        this.name = name;
    }
    public boolean equals(Object obj){
        if(this == obj){
            return true;
        }
        if(obj instanceof String){
            return obj == name;
        }
        return false;
    }
    public String toString(){
        return name;
    }
}
