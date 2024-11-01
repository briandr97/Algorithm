#include <iostream>
#include <vector>
#include <string>
#include <unordered_set>
#include <algorithm>
using namespace std;

int main() {
    int n, m;
    scanf("%d %d", &n, &m);

    unordered_set<string> unheard;
    vector<string> result;
    char input[21];
    
    for(int i=0; i<n; i++) {
        scanf("%s", input);
        unheard.insert(string(input));
    }
    for(int i=0; i<m; i++) {
        scanf("%s", input);
        string unsaw = string(input);
        if(unheard.find(unsaw) != unheard.end()) {
            result.push_back(unsaw);
        }
    }
    sort(result.begin(), result.end());
    printf("%d\n", (int)result.size());
    for(string & s: result) {
        printf("%s\n", s.c_str());
    }
    return 0;
}