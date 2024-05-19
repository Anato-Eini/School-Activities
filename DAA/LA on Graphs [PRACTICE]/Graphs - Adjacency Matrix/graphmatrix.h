#include <cstdlib>
#include <iostream>
#include <set>
#include "graph.h"
using namespace std;

class GraphMatrix : public Graph {
    int matrix[10][10];
    char s_vertices[10];
    int num_vert;
    int s_edges[100];
    int num_edge;

public:
    GraphMatrix() {
        num_vert = 0;
        num_edge = 0;
    }

    int numVertices() {
        return num_vert;
    }

    char* vertices() {
        if(num_vert == 0)
            return nullptr;
        char* returner = new char[num_vert];
        for(int i = 0; i < num_vert; i++)
            returner[i] = s_vertices[i];
        return returner;
    }

    int numEdges() {
        return num_edge;
    }

    int* edges() {
        if(num_edge == 0)
            return nullptr;
        int* returner = new int[num_edge];
        for(int i = 0; i < num_vert; i++)
            returner[i] = s_edges[i];
        return returner;
    }

    int getEdge(char u, char v)  {
        for(int i = 0; i < num_vert; i++)
            if(s_vertices[i] == u)
                for(int j = 0; j < num_vert; j++)
                    if(s_vertices[j] == v)
                        return matrix[i][j];
        return 0;
    }

    char* endVertices(int e)  {
        char* returner = new char [2];
        returner[0] = returner[1] = '-';
        for(int i = 0; i < num_vert; i++){
            for(int j = 0; j < num_vert; j++){
                if(matrix[i][j] == e){
                    returner[0] = s_vertices[i];
                    returner[1] = s_vertices[j];
                    return returner;
                }
            }
        }
        return returner;
    }

    char opposite(char v, int e)  {
        int indexOf;
        for(indexOf = 0; indexOf < num_vert; indexOf++)
            if(s_vertices[indexOf] == v) {
                for (int i = 0; i < num_vert; i++)
                    if (matrix[indexOf][i] == e)
                        return s_vertices[i];
                break;
            }

        for(int i = 0; i < num_vert; i++)
            if(matrix[i][indexOf] == e)
                return s_vertices[i];
        return '-';
    }

    int outDegree(char v)  {
        int counter = 0;
        for(int i = 0; i < num_vert; i++)
            if(s_vertices[i] == v)
                for (int j = 0; j < num_vert; j++)
                    if (matrix[i][j] != 0)
                        counter++;
        return counter;
    }

    int inDegree(char v)  {
        int indexOf, counter = 0;
        for(indexOf = 0; indexOf < num_vert; indexOf++)
            if(s_vertices[indexOf] == v)
                break;
        for(int i = 0; i < num_vert; i++)
            if(matrix[i][indexOf] != 0)
                counter++;
        return counter;
    }

    int* outgoingEdges(char v) {
        int* outEdges = new int[10];
        int index = 0;
        for(int i = 0; i < num_vert; i++)
            if(s_vertices[i] == v)
                for(int j = 0; j < num_vert; j++)
                    if(matrix[i][j] != 0)
                        outEdges[index++] = matrix[i][j];
        return outEdges;
    }

    int* incomingEdges(char v) {
        int* inEdges = new int[10], index = 0, indexOf;
        for(indexOf = 0; indexOf < num_vert; indexOf++)
            if(s_vertices[indexOf] == v)
                break;
        for(int i = 0; i < num_vert; i++)
            if(matrix[i][indexOf] != 0)
                inEdges[index++] = matrix[i][indexOf];
        return inEdges;
    }

    bool insertVertex(char x)  {
        if(num_vert == 10)
            return false;

        s_vertices[num_vert] = x;
        for(int i = 0; i < 10; i++)
            matrix[i][num_vert] = matrix[num_vert][i] = 0;

        num_vert++;
        return true;
    }

    bool insertEdge(char u, char v, int x)  {
        if(num_edge == 100)
            return false;
        for(int i = 0; i < num_vert; i++)
            if(s_vertices[i] == u)
                for(int j = 0; j < num_vert; j++)
                    if(s_vertices[j] == v){
                        if(matrix[i][j] != 0)
                            return false;
                        s_edges[num_edge++] = matrix[i][j] = x;
                        return true;
                    }
        return false;
    }

    int removeVertex(char v) {
        int indexOf, counter = 0;
        for(indexOf = 0; indexOf < num_vert; indexOf++)
            if(s_vertices[indexOf] == v)
                break;

        for(int i = 0; i < num_vert; i++)
            if(matrix[i][indexOf] != 0) {
                removeEdge(matrix[i][indexOf]);
                counter++;
            }

        for(int i = 0; i < num_vert; i++)
            for(int j = indexOf + 1; j < num_vert; j++)
                matrix[i][j - 1] = matrix[i][j];

        for(int i = 0; i < num_vert - 1; i++)
            if(matrix[indexOf][i] != 0) {
                removeEdge(matrix[indexOf][i]);
                counter++;
            }

        for(++indexOf; indexOf < num_vert; indexOf++) {
            for (int j = 0; j < num_vert - 1; j++)
                matrix[indexOf - 1][j] = matrix[indexOf][j];

            s_vertices[indexOf - 1] = s_vertices[indexOf];
        }

        num_vert--;
        return counter;
    }

    bool removeEdge(int e)  {
        for(int i = 0; i < num_vert; i++)
            for(int j = 0; j < num_vert; j++)
                if(matrix[i][j] == e){
                    matrix[i][j] = 0;
                    for(int k = num_edge - 1; k >= 0; k--)
                        if(s_edges[k] == e) {
                            for (int l = k + 1; l < num_edge; l++)
                                s_edges[l - 1] = s_edges[l];
                            break;
                        }
                    num_edge--;
                    return true;
                }
        return false;
    }

    void print() {
        cout << "\t";
        for (int i = 0; i < num_vert; i++) {
            cout << s_vertices[i] << "\t";
        }
        cout << endl;
        for (int i = 0; i < num_vert; i++) {
            cout << s_vertices[i] << "\t";
            for (int j = 0; j < num_vert; j++) {
                if (matrix[i][j] != 0) {
                    cout << matrix[i][j];
                }
                cout << "\t";
            }
            cout << endl;
        }
    }
};