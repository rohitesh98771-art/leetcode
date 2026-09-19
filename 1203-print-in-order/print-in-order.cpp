#include <mutex>
#include <condition_variable>
using namespace std;
class Foo {
    mutex m;
    condition_variable cv;
    int x = 0;
public:
    void first(function<void()> printFirst) {
        printFirst();
        x = 1;
        cv.notify_all();
    }
    void second(function<void()> printSecond) {
        unique_lock<mutex> lock(m);
        cv.wait(lock, [&]{ return x == 1; });
        printSecond();
        x = 2;
        cv.notify_all();
    }
    void third(function<void()> printThird) {
        unique_lock<mutex> lock(m);
        cv.wait(lock, [&]{ return x == 2; });
        printThird();
    }
};