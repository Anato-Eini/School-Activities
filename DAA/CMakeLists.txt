cmake_minimum_required(VERSION 3.27.8)
project(CodeChum-Activities)

set(CMAKE_CXX_STANDARD 23)

add_subdirectory("AVL Trees")

add_executable(HashTables
        "LA2 - Hash Tables/hashtable.h"
        "LA2 - Hash Tables/main.cpp"
)
