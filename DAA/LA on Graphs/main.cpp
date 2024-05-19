#include <iostream>
#include "graphmatrix.h"
using namespace std;

int main(void) {
    char op, v, x;
    char orig, dest;
    int e;

    int num, i;
    char* verts;
    int* edges;
    Graph* graph = new GraphMatrix();
    do {
        cout << "Operation: ";
        cin >> op;
        switch (op) {
            case 'v':
                cin >> v;
                graph->insertVertex(v);
                break;
            case 'e':
                cin >> orig;
                cin >> dest;
                cin >> e;
                graph->insertEdge(orig, dest, e);
                break;
            case 'q':
                cout << graph->numVertices() << endl;
                break;
            case 'w':
                cout << graph->numEdges() << endl;
                break;
            case 'V':
                verts = graph->vertices();
                num = graph->numVertices();
                cout << "List of Vertices: ";
                for (int i = 0; i < num; i++) {
                    cout << verts[i];
                    if (i < num-1) {
                        cout << ", ";
                    }
                }
                cout << endl;
                break;
            case 'E':
                edges = graph->edges();
                num = graph->numEdges();
                cout << "List of Edges: ";
                for (i = 0; i < num; i++) {
                    cout << edges[i];
                    if (i < num-1) {
                        cout << ", ";
                    }
                }
                cout << endl;
                break;
            case 'g':
                cin >> orig;
                cin >> dest;
                cout << graph->getEdge(orig, dest) << endl;
                break;
            case 'n':
                cin >> e;
                verts = graph->endVertices(e);
                cout << "Origin: " << verts[0] << " | Destination: " << verts[1] << endl;
                break;
            case 'l':
                cin >> v;
                cin >> e;
                cout << graph->opposite(v, e) << endl;
                break;
            case 'o':
                cin >> orig;
                cout << graph->outDegree(orig) << endl;
                break;
            case 'i':
                cin >> dest;
                cout << graph->inDegree(dest) << endl;
                break;
            case 'O':
                cin >> orig;
                edges = graph->outgoingEdges(orig);
                num = graph->outDegree(orig);
                cout << "List of Outgoing Edges from " << orig << ": ";
                for (i = 0; i < num; i++) {
                    cout << edges[i];
                    if (i < num-1) {
                        cout << ", ";
                    }
                }
                cout << endl;
                break;
            case 'I':
                cin >> dest;
                edges = graph->incomingEdges(dest);
                num = graph->inDegree(dest);
                cout << "List of Incoming Edges to " << dest << ": ";
                for (i = 0; i < num; i++) {
                    cout << edges[i];
                    if (i < num-1) {
                        cout << ", ";
                    }
                }
                cout << endl;
                break;
            case 'r':
                cin >> e;
                cout << graph->removeEdge(e) << endl;
                break;
            case 'R':
                cin >> v;
                cout << graph->removeVertex(v) << endl;
                break;
            case 'p':
                graph->print();
                break;
            case 'x':
                cout << "Exiting...";
                break;
            default:
                cout << "Invalid operation";
        }
    } while (op != 'x');

    return 0;
}