#include <iostream>
#include <string>

using namespace std;

class Window {
    char dna[4] = {'A', 'C', 'G', 'T'};
    int count[4];
    int size = 0;
    int k;

    int getCountIdx(char c) {
        for (int i = 0; i < 4; i++) {
            if (dna[i] == c) return i;
        }
        return -1;
    }

public:
    Window(int input_k) {
        for (int &c: count) {
            c = 0;
        }
        k = input_k;
    }

    void append(const char c) {
        int idx = getCountIdx(c);
        if (idx == -1) return;
        count[idx]++;
        size++;
    }

    void remove(const char c) {
        int idx = getCountIdx(c);
        if (idx == -1) return;
        count[idx]--;
        size--;
    }

    bool isAvailable(const int arr[]) {
        if (size < k) return false;
        for (int j = 0; j < 4; j++) {
            if (count[j] < arr[j]) return false;
        }
        return true;
    }
};

int n, k;
string str;
int arr[4];

int main() {
    cin >> n >> k;
    cin >> str;
    for (int &element: arr) {
        cin >> element;
    }

    Window window = Window(k);

    int count = 0;
    for (int i = 0; i < n; i++) {
        window.append(str.at(i));
        if (i >= k) window.remove(str.at(i - k));
        if (window.isAvailable(arr)) count++;
    }
    cout << count << "\n";
    return 0;
}
