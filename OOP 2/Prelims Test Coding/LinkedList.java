package Codes;

public class LinkedList<T> {
    class Node {
        public T element;
        public Node next;
        Node(T element, Node next){
            this.element = element;
            this.next = next;
        }
    }
    private Node head, tail;
    int size;
    public LinkedList(){
        head = null;
        tail = null;
        size = 0;
    }
    public void add(T element){
        if(head == null){
            tail = head = new Node(element, null);
        }else{
            tail = tail.next = new Node(element, null);
        }
        size++;
    }

    public T set(T element, int pos) {
        if(pos < 1 || pos > size)
            throw new IllegalArgumentException("Illegal Position");
        Node curr = head;
        int i = 1;
        while(i < pos - 1){
            curr = curr.next;
            i++;
        }
        T out;
        out = curr.element;
        curr.element = element;
        return out;
    }
}
