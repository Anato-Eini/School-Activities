cmake_minimum_required(VERSION 3.28.1)
project(CodeChum-Activities)

set(CMAKE_CXX_STANDARD 26)

add_executable(ArrayList_Dynamic "Array List (Dynamic) /arraylist.h"
        "Array List (Dynamic) /list.h"
        "Array List (Dynamic) /main.cpp"
        "Linked List (Doubly)/list.h")
add_executable(LinkedList_Doubly "Linked List (Doubly) /main.cpp"
        "Linked List (Doubly)/list.h"
        "Linked List (Doubly)/linkedlist.h"
        "Linked List (Doubly)/node.h")
