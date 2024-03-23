class List {
public:
    virtual void add(char) = 0;
    virtual char get(int pos) = 0;
    virtual int remove(char num) = 0;
    virtual void print() = 0;
};