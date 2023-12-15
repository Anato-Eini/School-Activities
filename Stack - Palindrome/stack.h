class Stack {
public:
    virtual void push(char) = 0;
    virtual char pop() = 0;
    virtual char top() = 0;
    virtual int size() = 0;
    virtual bool isEmpty() = 0;
    virtual void print() = 0;
};