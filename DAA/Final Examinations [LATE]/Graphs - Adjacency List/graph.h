class Graph {
   public:
    virtual int numVertices() = 0;
    virtual char* vertices() = 0;
    virtual int numEdges() = 0;
    virtual int* edges() = 0;
    virtual int getEdge(char u, char v) = 0;
    virtual char* endVertices(int e) = 0;
    virtual char opposite(char v, int e) = 0;
    virtual int outDegree(char v) = 0;
    virtual int inDegree(char v) = 0;
    virtual int* outgoingEdges(char v) = 0;
    virtual int* incomingEdges(char v) = 0;
    virtual bool insertVertex(char x) = 0;
    virtual bool insertEdge(char u, char v, int x) = 0;
    virtual int removeVertex(char v) = 0;
    virtual bool removeEdge(int e) = 0;
    virtual void print() = 0;
};