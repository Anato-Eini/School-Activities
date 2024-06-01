#include <cstdlib>
#include <iostream>
using namespace std;

class Vertex {
    char name;
    int* out_edges;
    int* in_edges;
    int out_count;
    int in_count;

public:
    Vertex(char name) {
        this->name = name;
        out_edges = (int*) malloc(10);
        in_edges = (int*) malloc(10);
        out_count = 0;
        in_count = 0;
    }

    char getName() {
        return name;
    }

    bool findIn(int e){
        for(int i = 0; i < in_count; i++)
            if(in_edges[i] == e)
                return true;
        return false;
    }

    bool findOut(int e){
        for (int i = 0; i < out_count; ++i) {
            if(out_edges[i] == e)
                return true;
        }
        return false;
    }

    int* getOutEdges() {
        int* outEdges = new int[out_count];
        for(int i = 0; i < out_count; i++)
            outEdges[i] = out_edges[i];
        return outEdges;
    }

    int* getInEdges() {
        int* outEdges = new int[in_count];
        for(int i = 0; i < in_count; i++)
            outEdges[i] = in_edges[i];
        return outEdges;
    }

    int getInCount() {
        return in_count;
    }

    int getOutCount() {
        return out_count;
    }

    void addOutEdge(int e) {
        if(out_count == 10)
            return;
        out_edges[out_count++] = e;
    }

    void addInEdge(int e) {
        if(in_count == 10)
            return;
        in_edges[in_count++] = e;
    }

    bool removeOutEdge(int e) {
        for(int i = 0; i < out_count; i++){
            if(out_edges[i] == e){
                for(++i; i < out_count; i++)
                    out_edges[i - 1] = out_edges[i];
                out_count--;
                return true;
            }
        }
        return false;
    }

    bool removeInEdge(int e) {
        for(int i = 0; i < in_count; i++){
            if(in_edges[i] == e){
                for(++i; i < in_count; i++)
                    in_edges[i - 1] = in_edges[i];
                in_count--;
                return true;
            }
        }
        return false;
    }

    void toString() {
        cout << "Vertex: " << this->name << " | ";
        cout << "Outgoing: ";
        for (int i = 0; i < out_count; i++) {
            cout << out_edges[i];
            if (i < out_count-1) {
                cout << ", ";
            }
        }
        cout << " | Incoming: ";
        for (int i = 0; i < in_count; i++) {
            cout << in_edges[i];
            if (i < in_count-1) {
                cout << ", ";
            }
        }
    }
};