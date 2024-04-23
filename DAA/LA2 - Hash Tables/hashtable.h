#include <cstdlib>
#include <iostream>
using namespace std;

class HashTable {
    char* table;
    int N;
    int count;

    // Use the ASCII code of the character
    int hash_code(char key) {
        return (int) key;
    }

    // This hash table uses a MAD compression function
    // where a = 59, b = 17, p = 509
    int compress(int code) {
        return ((59 * code + 17) % 509) % N;
    }

    // Using the knowledge that a hash function is composed of two portions
    int hashfn(char key) {
        return compress(hash_code(key));
    }
public:
    explicit HashTable(int N) : N(N), count(0) {
        if(N < 0)
            N = 0;
        table = new char[N];
        for(int i = 0; i < N; i++)
            table[i] = '\0';
    }

    int insert(char key) {
        if(count == N || search(key) > 0)
            return -1;
        int index = hashfn(key), i = 0, probe = (index + i++) % N;
        while(table[probe] != '\0')
            probe = (index + i++) % N;
        table[probe] = key;
        count++;
        return i - 1;
    }

    int search(char key) {
        int index = hashfn(key), i = 0, probe = (index + i++) % N, checkedNum = 0;
        while(checkedNum < count){
            if(table[probe] == key)
                return i;
            else if(table[probe] != '\0')
                checkedNum++;
            probe = (index + i++) % N;
        }
        return -i;
    }

    int remove(char key) {
        int keyIndex = search(key) - 1;
        if(keyIndex < 0)
            return -1;
        table[(hashfn(key) + keyIndex) % N] = '\0';
        count--;
        return keyIndex;
    }

    void print() {
        for (int i = 0; i < N; i++) {
            cout << i << "\t";
        }
        cout << "\n";
        for (int i = 0; i < N; i++) {
            cout << table[i] << "\t";
        }
        cout << "\n";
    }
};