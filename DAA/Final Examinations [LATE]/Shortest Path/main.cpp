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
            case 'p':
                graph->print();
                break;
            case 's':
                cin >> orig;
                cin >> dest;
                cout << graph->distance(orig, dest) << endl;
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