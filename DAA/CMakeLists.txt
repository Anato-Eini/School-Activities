cmake_minimum_required(VERSION 3.28.1)
project(CodeChum-Activities)

set(CMAKE_CXX_STANDARD 26)

add_subdirectory("AVL Trees")

add_subdirectory("LA1 - List ADT")

add_subdirectory("Final Examinations [LATE]")

add_executable(HashTables
        "LA2 - Hash Tables/hashtable.h"
        "LA2 - Hash Tables/main.cpp"
)
