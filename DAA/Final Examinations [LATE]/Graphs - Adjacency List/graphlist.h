#include <cstdlib>
#include <iostream>
#include "graph.h"
#include "vertex.h"
using namespace std;

class GraphList : public Graph {
    Vertex** s_vertices;
    int num_vert;
    int s_edges[100];
    int num_edge;

public:
    GraphList() {
        s_vertices = (Vertex**) malloc( 10 * sizeof(Vertex*) );
        num_vert = 0;
        num_edge = 0;
    }

    int numVertices() {
        return num_vert;
    }

    char* vertices() {
        char *vertices = new char [num_vert];
        for(int i = 0; i < num_vert; i++)
            vertices[i] = s_vertices[i]->getName();
        return vertices;
    }

    int numEdges() {
        return num_edge;
    }

    int* edges() {
        int* edges = new int [num_edge];
        for(int i = 0; i < num_edge; i++)
            edges[i] = s_edges[i];
        return edges;
    }

    int getEdge(char u, char v)  {
        int indexU, indexV;

        for(indexU = 0; indexU < num_vert; indexU++)
            if(s_vertices[indexU]->getName() == u)
                break;

        for(indexV = 0; indexV < num_vert; indexV++)
            if(s_vertices[indexV]->getName() == v)
                break;

        int* outEdges = s_vertices[indexU]->getOutEdges(), sizeOutEdges = s_vertices[indexU]->getOutCount();
        for(int i = 0; i < sizeOutEdges; i++)
            if(s_vertices[indexV]->findIn(outEdges[i]))
                return outEdges[i];

        return 0;
    }

    char* endVertices(int e)  {
        char* endVertices = new char [2];
        endVertices[0] = endVertices[1] = '-';

        for(int i = 0; i < num_vert; i++)
            if(s_vertices[i]->findOut(e)){
                endVertices[0] = s_vertices[i]->getName();
                break;
            }

        for(int i = 0; i < num_vert; i++)
            if(s_vertices[i]->findIn(e)){
                endVertices[1] = s_vertices[i]->getName();
                break;
            }

        return endVertices;
    }

    char opposite(char v, int e)  {
        for(int i = 0; i < num_vert; i++)
            if((s_vertices[i]->findIn(e) || s_vertices[i]->findOut(e)) && s_vertices[i]->getName() != v)
                return s_vertices[i]->getName();
        return '-';
    }

    int outDegree(char v)  {
        for(int i = 0; i < num_vert; i++)
            if(s_vertices[i]->getName() == v)
                return s_vertices[i]->getOutCount();
        return 0;
    }

    int inDegree(char v)  {
        for(int i = 0; i < num_vert; i++)
            if(s_vertices[i]->getName() == v)
                return s_vertices[i]->getInCount();
        return 0;
    }

    int* outgoingEdges(char v) {
        for(int i = 0; i < num_vert; i++)
            if(s_vertices[i]->getName() == v)
                return s_vertices[i]->getOutEdges();
        return nullptr;
    }

    int* incomingEdges(char v) {
        for(int i = 0; i < num_vert; i++)
            if(s_vertices[i]->getName() == v)
                return s_vertices[i]->getInEdges();
        return nullptr;
    }

    bool insertVertex(char x)  {
        if(num_vert == 10 )
            return false;

        s_vertices[num_vert++] = new Vertex{x};
        return false;
    }

    bool insertEdge(char u, char v, int x)  {
        int indexU, indexV;
        for(indexU = 0; indexU < num_vert; indexU++)
            if(s_vertices[indexU]->getName() == u)
                break;

        for(indexV = 0; indexV < num_vert; indexV++)
            if(s_vertices[indexV]->getName() == v)
                break;

        if(getEdge(u, v) == 0){
            s_vertices[indexU]->addOutEdge(x);
            s_vertices[indexV]->addInEdge(x);
            s_edges[num_edge++] = x;
            return true;
        }else {
            cout << "NOT NULL\n";
            return false;
        }
    }

    int removeVertex(char v) {
        int indexV;
        for(indexV = 0; indexV < num_vert; indexV++)
            if(s_vertices[indexV]->getName() == v)
                break;

        int count = 0, countIn = s_vertices[indexV]->getInCount(), countOut = s_vertices[indexV]->getOutCount()
                , *outEdges = s_vertices[indexV]->getOutEdges(), *inEdges = s_vertices[indexV]->getInEdges();

        for(++indexV; indexV < num_vert; indexV++)
            s_vertices[indexV - 1] = s_vertices[indexV];

        num_vert--;

        for(int i = 0; i < num_vert; i++)
            for(int j = 0; j < countIn; j++)
                if(s_vertices[i]->removeOutEdge(inEdges[j])) {
                    for(int k = 1; k < num_edge; k++)
                        s_edges[k - 1] = s_edges[k];
                    num_edge--;
                    count++;
                }

        for(int i = 0; i < num_vert; i++)
            for(int j = 0; j < countOut; j++)
                if(s_vertices[i]->removeInEdge(outEdges[j])) {
                    for(int k = 1; k < num_edge; k++)
                        s_edges[k - 1] = s_edges[k];
                    num_edge--;
                    count++;
                }

        return count;
    }

    bool removeEdge(int e)  {
        for(int i = 0; i < num_vert; i++)
            if(s_vertices[i]->removeInEdge(e)){

                for(int j = 0; j < num_vert; j++) {
                    s_vertices[j]->removeOutEdge(e);
                }

                for(int k = 1; k < num_edge; k++)
                    s_edges[k - 1] = s_edges[k];

                num_edge--;

                return true;
            }
        return false;
    }

    void print() {
        for (int i = 0; i < num_vert; i++) {
            s_vertices[i]->toString();
            cout << endl;
        }
    }
};