#include <cstdlib>
#include <math.h>
#include <cstring>
#include <iostream>
using namespace std;

class HashTable {
    string* table;
    int N;
    int count;

    //  Polynomial Hash Code using a=7
    int hash_code(string key) {
        int code = 0, size = key.size();

        for (int i = 0; i < size; i++)
            code += (int) (key[i] - 'a' + 1) * pow(7, size - i - 1);

        return code;
    }

    // This hash table uses a MAD compression function
    // where a = 11, b = 461, p = 919
    int compress(int code) {
        return (11 * code + 461) % 919 % N;
    }

    // Using the knowledge that a hash function is composed of two portions
    int hashfn(string key) {
        return compress(hash_code(key));
    }

public:
    HashTable(int N) : N{N}, table{new string [N]}, count{0} {}

    int insert(string key) {
        if(count == N)
            return -1;

        int numCollisions = 0, hash = hashfn(key);

        while (!table[(hash + (int) pow(numCollisions, 2)) % N].empty()) {
            ++numCollisions;
        }

        table[(hash + (int)pow(numCollisions, 2)) % N] = key;

        return numCollisions;
    }

    // Tip: str1.compare(str2) will return 0 if both strings are the same
    int search(string key) {
        int numberOfCollisions = 0, hash = hashfn(key);

        while(
                numberOfCollisions < N &&
                table[(hash + (int)pow(numberOfCollisions, 2)) % N] != key)
            ++numberOfCollisions;

        return table[(hash + (int)pow(numberOfCollisions, 2)) % N].empty() || numberOfCollisions == N ? -1 :
                numberOfCollisions;
    }

    int remove(string key) {
        int numberOfCollisions = search(key), hash = hashfn(key);

        if(numberOfCollisions != -1)
            table[(hash + (int)pow(numberOfCollisions, 2)) % N] = "";

        return numberOfCollisions;
    }
    
    void print() {
        for(int i = 0; i < N; i++)
            cout << i << '\t';
        cout << '\n';
        for(int i = 0; i < N; i++)
            cout << table[i] << '\t';
        cout << '\n';
    }
};