import java.util.ArrayList;

public class Queue {
    ArrayList<Character> queue;
    public Queue(){
        queue = new ArrayList<>();
    }
    public void enqueue(Character character){
        queue.add(character);
    }

    public Character dequeue(){
        Character returner = null;
        if(!queue.isEmpty()){
            returner = queue.get(0);
            queue.remove(0);
        }else {
            System.out.println("Empty Queue");
        }
        return returner;
    }
}
