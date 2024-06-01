// WARNING! Do not modify this file!
// Doing so will nullify your score for this activity.
class List {
public:
    /**
     * The add operation will add the specified num at the last part of the list.
     * Will return the position of the newly added element.
     */
    virtual int add(int num) = 0;

    /**
     * The get operation will return the element in the specified position
     * of the list. If invalid, return -1.
     */
    virtual int get(int pos) = 0;

    /**
     * The size operation will return the size of the list.
     */
    virtual int size() = 0;

    /**
     * The swap operation will swap the elements at said position
     * of the list.
     */
    virtual void swap(int pos1, int pos2) = 0;

    /**
     * The print operation will print the list.
     * This has already been implemented by the instructor.
     */
    virtual void print() = 0;
};