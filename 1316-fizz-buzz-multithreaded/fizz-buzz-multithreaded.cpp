class FizzBuzz {
    int n, x = 1;
    mutex m;
    condition_variable cv;
public:
    FizzBuzz(int n) : n(n) {}
    void fizz(function<void()> f) {
        while (1) {
            unique_lock<mutex> l(m);
            cv.wait(l, [&]{ return x > n || (x%3==0 && x%5!=0); });
            if (x > n) return;
            f(); x++; cv.notify_all();
        }
    }
    void buzz(function<void()> f) {
        while (1) {
            unique_lock<mutex> l(m);
            cv.wait(l, [&]{ return x > n || (x%5==0 && x%3!=0); });
            if (x > n) return;
            f(); x++; cv.notify_all();
        }
    }
    void fizzbuzz(function<void()> f) {
        while (1) {
            unique_lock<mutex> l(m);
            cv.wait(l, [&]{ return x > n || x%15==0; });
            if (x > n) return;
            f(); x++; cv.notify_all();
        }
    }
    void number(function<void(int)> f) {
        while (1) {
            unique_lock<mutex> l(m);
            cv.wait(l, [&]{ return x > n || (x%3 && x%5); });
            if (x > n) return;
            f(x++); cv.notify_all();
        }
    }
};