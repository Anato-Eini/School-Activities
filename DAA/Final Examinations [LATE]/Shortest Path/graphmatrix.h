#include <cstdlib>
#include <iostream>
#include <limits.h>
#include "graph.h"
using namespace std;

class GraphMatrix : public Graph {
    int matrix[10][10];
    char s_vertices[100];
    int num_vert;
    int s_edges[100];
    int num_edge;

    bool insertVertex(char x)  {
        s_vertices[num_vert++] = x;
        for(int i = 0; i < 10; i++)
            matrix[num_vert - 1][i] = 0;
        return true;
    }

    bool insertEdge(char u, char v, int x)  {
        int u_ind = 0, v_ind = 0;
        for (int i = 0; i < num_vert; i++) {
            if (u == s_vertices[i]) {
                u_ind = i;
            }
            if (v == s_vertices[i]) {
                v_ind = i;
            }
        }
        if (matrix[u_ind][v_ind] != 0) {
            cout << "Not null" << endl;
            return false;
        }
        matrix[u_ind][v_ind] = x;
        matrix[v_ind][u_ind] = x;
        s_edges[num_edge] = x;
        num_edge++;
        return true;
    }

    int minimumIndex(const int distance[], const bool visited[]){
        int minimumValue = INT_MAX, minIndex;

        for(int i = 0; i < num_vert; i++){
            if(distance[i] < minimumValue && !visited[i]){
                minimumValue = distance[i];
                minIndex = i;
            }
        }

        return minIndex;
    }

    int distance(char u, char v) {
        if(u == v)
            return 0;

        int distance[num_vert];
        bool visited[num_vert];

        for(int i = 0; i < num_vert; i++) {
            distance[i] = INT_MAX;
            visited[i] = false;
        }

        int indexU;
        for(indexU = 0; indexU < num_vert; indexU++)
            if(s_vertices[indexU] == u)
                break;

        distance[indexU] = 0;

        int size = num_vert - 1;
        for(int i = 0; i < size; i++){
            int minIndex = minimumIndex(distance, visited);
            visited[minIndex] = true;

            for(int i = 0; i < num_vert; i++)
                if(!visited[i] && matrix[minIndex][i] && distance[i] > distance[minIndex] + matrix[minIndex][i])
                    distance[i] = distance[minIndex] + matrix[minIndex][i];
        }

        for(int i = 0; i < num_vert; i++)
            if(s_vertices[i] == v)
                return distance[i] == INT_MAX ? -1 : distance[i];

        return -1;
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
                // cout << "i: " << i << " | j: " << j << endl;
                if (matrix[i][j] != 0) {
                    cout << matrix[i][j];
                }
                cout << "\t";
            }
            cout << endl;
        }
    }
};