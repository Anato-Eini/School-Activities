class List {
public:
    virtual void add(string) = 0;
    virtual int get(int pos) = 0;
    virtual string remove(string num) = 0;
    virtual void print() = 0;
};