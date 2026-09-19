#include <stdlib.h>
typedef struct {
    int time, process, index;
} Task;
int cmp(const void *a, const void *b) {
    return ((Task *)a)->time - ((Task *)b)->time;
}
int cmpHeap(Task *a, Task *b) {
    if (a->process != b->process)
        return a->process - b->process;
    return a->index - b->index;
}
void swap(Task **a, Task **b) {
    Task *t = *a;
    *a = *b;
    *b = t;
}
void push(Task **heap, int *size, Task *x) {
    int i = (*size)++;
    heap[i] = x;

    while (i > 0) {
        int p = (i - 1) / 2;
        if (cmpHeap(heap[p], heap[i]) <= 0)
            break;
        swap(&heap[p], &heap[i]);
        i = p;
    }
}
Task* pop(Task **heap, int *size) {
    Task *ans = heap[0];
    heap[0] = heap[--(*size)];
    int i = 0;
    while (1) {
        int l = 2 * i + 1, r = 2 * i + 2;
        int small = i;
        if (l < *size && cmpHeap(heap[l], heap[small]) < 0)
            small = l;
        if (r < *size && cmpHeap(heap[r], heap[small]) < 0)
            small = r;
        if (small == i)
            break;
        swap(&heap[i], &heap[small]);
        i = small;
    }
    return ans;
}
int* getOrder(int** tasks, int tasksSize, int* tasksColSize,
              int* returnSize) {
    Task *t = malloc(tasksSize * sizeof(Task));
    for (int i = 0; i < tasksSize; i++) {
        t[i].time = tasks[i][0];
        t[i].process = tasks[i][1];
        t[i].index = i;
    }
    qsort(t, tasksSize, sizeof(Task), cmp);
    Task **heap = malloc(tasksSize * sizeof(Task *));
    int *ans = malloc(tasksSize * sizeof(int));
    int size = 0, i = 0, k = 0;
    long long time = 0;
    while (k < tasksSize) {
        if (size == 0 && time < t[i].time)
            time = t[i].time;
        while (i < tasksSize && t[i].time <= time)
            push(heap, &size, &t[i++]);
        Task *cur = pop(heap, &size);
        ans[k++] = cur->index;
        time += cur->process;
    }
    free(t);
    free(heap);
    *returnSize = tasksSize;
    return ans;
}