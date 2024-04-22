cmake_minimum_required(VERSION 3.27.8)
project(DAA-Activities)

set(CMAKE_CXX_STANDARD 23)

add_executable(AVL_Trees_P1-Rotate
        "AVL Trees/AVL Trees - 01 Rotate (zigleft_zigright)/main.cpp"
        "AVL Trees/AVL Trees - 01 Rotate (zigleft_zigright)/node.h"
        "AVL Trees/AVL Trees - 01 Rotate (zigleft_zigright)/bst.h"
        "AVL Trees/AVL Trees - 01 Rotate (zigleft_zigright)/mybinarytree.h"
        "AVL Trees/AVL Trees - 01 Rotate (zigleft_zigright)/binarytree.h"
)

add_executable(AVL_Trees_P2-Restructure
        "AVL Trees/AVL Trees - 02 Restructure/main.cpp"
        "AVL Trees/AVL Trees - 02 Restructure/node.h"
        "AVL Trees/AVL Trees - 02 Restructure/bst.h"
        "AVL Trees/AVL Trees - 02 Restructure/mybinarytree.h"
        "AVL Trees/AVL Trees - 02 Restructure/binarytree.h"
)

add_executable(AVL_Trees_P3-InsertDelete
        "AVL Trees/AVL Trees - 03 Insert-Delete/main.cpp"
        "AVL Trees/AVL Trees - 03 Insert-Delete/node.h"
        "AVL Trees/AVL Trees - 03 Insert-Delete/bst.h"
        "AVL Trees/AVL Trees - 03 Insert-Delete/mybinarytree.h"
        "AVL Trees/AVL Trees - 03 Insert-Delete/binarytree.h"
)