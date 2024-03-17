package com.example.javafx1;

import javafx.scene.Node;
import javafx.scene.Parent;

import java.util.LinkedList;

public class BFSNodeSearch extends Parent{
    static LinkedList<Parent> array = new LinkedList<>();
    public static Parent findNode(Parent root, String name){
        array.add(root);
        while(!array.isEmpty()){
            Parent currentNode = array.removeFirst();
            for(Node node : currentNode.getChildrenUnmodifiable()){
                if(node.getId() != null && node.getId().equals(name))
                    return (Parent) node;
                else if(node instanceof Parent)
                    array.addLast((Parent) node);
            }
        }
        return null;
    }
}
