cmake_minimum_required(VERSION 3.28)

project(Graph)

set(CMAKE_CXX_STANDARD 26)

add_executable(Adjacency_Matrix
        "Graphs - Adjacency Matrix/main.cpp"
        "Graphs - Adjacency Matrix/graph.h"
        "Graphs - Adjacency Matrix/graphmatrix.h"
)
