cmake_minimum_required(VERSION 3.28.1)

project(CodeChum-Activities)

set(CMAKE_CXX_STANDARD 26)

add_executable(Sorting_on_Lists
        "Sorting on Lists/list.h"
        "Sorting on Lists/main.cpp"
        "Sorting on Lists/arraylist.h"
        "Sorting on Lists/linkedlist.h")
