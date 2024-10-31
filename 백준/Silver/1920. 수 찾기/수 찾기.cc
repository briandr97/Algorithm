#include <iostream>
#include <unordered_map>
using namespace std;

int main() {
    int N, M;
    unordered_map<int, bool> n;

    scanf("%d", &N);
    for (int i = 0; i < N; i++) {
        int num;
        scanf("%d", &num);
        n[num] = true;
    }

    scanf("%d", &M);
    int m[M];
    for (int i = 0; i < M; i++) {
        scanf("%d", &m[i]);
    }

    for (int & num: m) {
        if (n[num]) printf("%d\n", 1);
        else printf("%d\n", 0);
    }

    return 0;
}
