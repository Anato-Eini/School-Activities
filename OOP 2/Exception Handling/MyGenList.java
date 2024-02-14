package Codes;

public class MyGenList<T> {
    public T[] arr;
    public int size;
    public final int capacity = 5;
    public MyGenList(){
        arr = (T[]) new Object[capacity];
        size = 0;
    }
    public void add(T num) throws ArrayFullException {
        try{
            arr[size] = num;
            size++;
        } catch(ArrayIndexOutOfBoundsException e){
            throw new ArrayFullException("The array is full and " + num.toString() + " cannot be inserted.");
        }
    }

    public void addAt(int pos, T num) throws ArrayFullException {
        if(size == capacity){
            throw new ArrayFullException("The array is full and " + num.toString() + " cannot be inserted.");
        }
        if(pos < 1 || pos > size + 1){
            throw new InvalidPositionException("Position must be between 1 and " + (size + 1));
        }
        for(int i = size - 1; i >= pos - 1; i--){
            arr[i + 1] = arr[i];
        }
        arr[pos - 1] = num;
        size++;
    }

    public boolean remove(Object num){
        for(int i = 0; i < arr.length; i++){
            if(arr[i] != null && arr[i].equals(num)){
                for(int j = i; j < arr.length -1; j++){
                    arr[j] = arr[j + 1];
                }
                size--;
                return true;
            }
        }
        return false;
    }
    public T removeAt(int pos){
        if(pos < 1 || pos > size){
            throw new InvalidPositionException("Position must be between 1 and " + size);
        }
        T num = arr[pos - 1];
        for(int i = pos - 1; i < arr.length - 1; i++){
            arr[i] = arr[i + 1];
        }
        size--;
        return num;
    }

    public boolean contains(Object num){
        for(T t : arr){
            if(t != null && t.equals(num)){
                return true;
            }
        }
        return false;
    }
    public int size(){
        return size;
    }
    public T get(int pos){
        if(pos < 1 || pos > size){
            throw new InvalidPositionException("Position must be between 1 and " + size);
        }
        return arr[pos - 1];
    }
    public boolean isEmpty(){
        return size() == 0;
    }
    public T set(int pos, Object num){
        if(pos < 1 || pos > size){
            throw new InvalidPositionException("Position must be between 1 and " + size);
        }
        T returner = arr[pos - 1];
        arr[pos - 1] = (T) num;
        return returner;
    }
}
