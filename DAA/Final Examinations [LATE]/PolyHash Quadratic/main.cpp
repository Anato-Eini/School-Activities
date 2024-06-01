#include <iostream>
#include "hashtable.h"
using namespace std;

int main(void) {
  cout << "Enter capacity of hash table: ";
  int N;
  cin >> N;
  
  HashTable *table = new HashTable(N);
  
  char op;
  string key;
  do {
    cout << "Operation: ";
    cin >> op;
    
    switch (op) {
      case 'i': // Insert
        cin >> key;
        cout << table->insert(key) << endl;
        break;
        
      case 's': // Search
        cin >> key;
        cout << table->search(key) << endl;
        break;
        
      case 'd': // Delete
        cin >> key;
        cout << table->remove(key) << endl;
        break;
        
      case 'p':
        table->print();
        break;
      
      case 'x':
        cout << "Exiting";
        break;
    }
  } while (op != 'x');
  
  return 0;
}