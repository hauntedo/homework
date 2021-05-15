#include <iostream>
#include <ctime>
#include <random>
#include <chrono>

using namespace std;

class Timer
{
private:

    using clock_t = std::chrono::high_resolution_clock;
    using second_t = std::chrono::duration<double, std::ratio<1> >;

    std::chrono::time_point<clock_t> m_beg;

public:
    Timer() : m_beg(clock_t::now())
    {
    }

    void reset()
    {
        m_beg = clock_t::now();
    }

    double elapsed() const
    {
        return std::chrono::duration_cast<second_t>(clock_t::now() - m_beg).count();
    }
};

struct FenwickTreeK {

    vector<int> t;

public:

    FenwickTreeK(vector<int> a) {
        t.resize(a.size());
        build(a);
    };

    int sum(int i, int j) {
        return prefix(j) - prefix(i);
    }

    int F(int i) {
        return i&(i+1);
    }

    void modify(int i, int d) {
        while (i < t.size()) {
            t[i] += d;
            i = i | (i + 1);
        }
    }

    void set(int i, int x) {
        int d = x - sum(i,i+1); //срез a[i:i+1] = a[i]
        modify(i, d);
    }

    void build(vector<int> a) {
        for (int i=0; i<t.size(); i++) {
            modify(i, a[i]);
        }
    }

    int prefix(int i) {
        int result = 0;
        while (i >= 0) {
            result += t[i];
            i = F(i) - 1;
        }
        return result;
    }

};

int getSum(int fw[], int i)
{
    int sum = 0;
    // Fenwick's index start from 1
    i++;

    while(i > 0)
    {
        sum += fw[i];
        // i & (-i)  returns the decimal value of last set digit
        // eg: if i = 12 (1100) then  i & (-i) will 4 (100)
        i -= i & (-i);
    }
    return sum;
}

// newVal will be updated to Fenwick and all its ancestor
void updateFW(int fw[], int n, int i, int newVal)
{
    // Fenwick's index start from 1
    i++;
    while (i <= n)
    {
        fw[i] += newVal;
        i += i & (-i);
    }
}

// Build Fenwick's tree
int *constructFenwick(int a[], int n)
{
    int *fw = new int [n+1];
    for (int i = 0; i <= n; i++)
        fw[i] = 0;

    for (int i=0; i<n; i++)
        updateFW(fw, n, i, a[i]);

    return fw;
}

int main()
{
    int n;
    vector<int> ab;
    cin >> n;
    int a[n];
    for (int i = 0; i < n; i++) {
        a[i] = i;
    }
    int *fw = constructFenwick(a, n);
    updateFW(fw, n, 3, 7);
    cout<<getSum(fw, 10);


    for (int i = 0; i < n; i++) {
        ab.at(i) = i;
    }
    FenwickTreeK ft = *new FenwickTreeK(ab);
    cout << ft.prefix(3) << endl;
    cout << ft.sum(-1,4) << endl;

    return 0;
}






