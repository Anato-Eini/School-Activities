# This file handles building each task as sub-project.
#
# Each task becomes an independent project in which you can experiment.
#
# Tasks are added as separate sub-projects so as compilation errors in
# one task don't affect another task.
cmake_minimum_required(VERSION 3.27.8)
project(CodeChum-Activities)

set(CMAKE_CXX_STANDARD 23)

add_executable(AVL_Trees_P1-Rotate
        "AVL Trees - 01 Rotate (zigleft_zigright)/main.cpp"
        "AVL Trees - 01 Rotate (zigleft_zigright)/node.h"
        "AVL Trees - 01 Rotate (zigleft_zigright)/bst.h"
        "AVL Trees - 01 Rotate (zigleft_zigright)/mybinarytree.h"
        "AVL Trees - 01 Rotate (zigleft_zigright)/binarytree.h"
)

add_executable(AVL_Trees_P2-Restructure
        "AVL Trees - 02 Restructure/main.cpp"
        "AVL Trees - 02 Restructure/node.h"
        "AVL Trees - 02 Restructure/bst.h"
        "AVL Trees - 02 Restructure/mybinarytree.h"
        "AVL Trees - 02 Restructure/binarytree.h"
)

add_executable(AVL_Trees_P3-InsertDelete
        "AVL Trees - 03 Insert-Delete/main.cpp"
        "AVL Trees - 03 Insert-Delete/node.h"
        "AVL Trees - 03 Insert-Delete/bst.h"
        "AVL Trees - 03 Insert-Delete/mybinarytree.h"
        "AVL Trees - 03 Insert-Delete/binarytree.h"
)