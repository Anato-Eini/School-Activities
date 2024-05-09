cmake_minimum_required(VERSION 3.28)
project(Red_Black)

set(CMAKE_CXX_STANDARD 26)

add_executable(Red_Black main.cpp
        Node.h
        RedBlack.cpp
        RedBlack.h)
