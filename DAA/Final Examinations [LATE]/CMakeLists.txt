cmake_minimum_required(VERSION 3.28.1)

project(CodeChum-Activities)

set(CMAKE_CXX_STANDARD 26)

add_executable(Sorting_on_Lists
        "Sorting on Lists/list.h"
        "Sorting on Lists/main.cpp"
        "Sorting on Lists/arraylist.h"
        "Sorting on Lists/linkedlist.h")
add_executable(Quick_Sort
        "Quick Sort/main.cpp"
)
add_executable(Merge_Sort "Merge Sort/main.cpp"
        "Merge Sort/list.h"
        "Merge Sort/linkedlist.h")
add_executable(PolyHash_Quadratic
        "PolyHash Quadratic/main.cpp"
        "PolyHash Quadratic/hashtable.h"
)
add_executable(Splay_Trees
        "Splay Trees/main.cpp"
        "Splay Trees/tree.h"
        "Splay Trees/node.h"
)

add_executable(Graphs_-_Adjacency_List
        "Graphs - Adjacency List/main.cpp"
        "Graphs - Adjacency List/vertex.h"
        "Graphs - Adjacency List/graph.h"
        "Graphs - Adjacency List/graphlist.h"
)

add_executable(Shortest_Path
        "Shortest Path/main.cpp"
        "Shortest Path/graph.h"
        "Shortest Path/graphmatrix.h"
)

add_executable(Red-Black_Trees_Part_2_1
        "Red-Black Trees Part 2_1/main.cpp"
        "Red-Black Trees Part 2_1/tree.h"
        "Red-Black Trees Part 2_1/node.h")