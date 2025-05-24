import java.io.*;
import java.util.*;
import java.util.Arrays;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int repeat = Integer.parseInt(br.readLine());
        Heap<Integer> heap = new Heap<>(repeat, (o1, o2) -> o2.compareTo(o1));
        while(repeat-- > 0) {
            int op = Integer.parseInt(br.readLine());
            if(op == 0) {
                Integer result = heap.poll();
                if(result == null) result = 0;
                sb.append(result).append("\n");
            } else {
                heap.offer(op);
            }
        }

        System.out.println(sb);
    }
}

class Heap<T> {
    private static final int DEFAULT_CAPACITY = 100;

    private int capacity;
    private int size = 0;
    private Comparator<T> comparator;
    Object[] elements;

    public Heap(int capacity) {
        this(capacity, null);
    }

    public Heap() {
        this(DEFAULT_CAPACITY);
    }

    public Heap(Comparator<T> comparator) {
        this(DEFAULT_CAPACITY, comparator);
    }

    public Heap(int capacity, Comparator<T> comparator) {
        this.capacity = capacity;
        this.comparator = comparator;
        elements = new Object[capacity + 1];
    }

    public void offer(T element) {
        checkAndResize();
        elements[++size] = element;

        int currentIdx = size;
        Integer parentIdx = null;

        while ((parentIdx = getParentIndex(currentIdx)) != null) {
            T parent = (T) elements[parentIdx];
            if (compare(parent, element) > 0) {
                swap(parentIdx, currentIdx);
                currentIdx = parentIdx;
            } else {
                break;
            }
        }
    }

    public T peek() {
        if (size == 0) return null;
        return (T) elements[1];
    }

    public T poll() {
        if (size == 0) return null;
        T result = (T) elements[1];

        elements[1] = elements[size];
        elements[size--] = null;

        Integer currentIdx = 1;
        while (true) {
            T cur = (T) elements[currentIdx];

            Integer leftChildIdx = getLeftChild(currentIdx);
            Integer rightChildIdx = getRightChild(currentIdx);
            int minChildIdx;
            if(leftChildIdx == null && rightChildIdx == null) break;
            else if(leftChildIdx == null) minChildIdx = rightChildIdx;
            else if(rightChildIdx == null) minChildIdx = leftChildIdx;
            else if(compare((T) elements[leftChildIdx], (T) elements[rightChildIdx]) <= 0) minChildIdx = leftChildIdx;
            else minChildIdx = rightChildIdx;


            T minChild = (T) elements[minChildIdx];
            if (compare(cur, minChild) > 0) {
                swap(currentIdx, minChildIdx);
                currentIdx = minChildIdx;
                continue;
            }

            break;
        }

        return result;
    }

    private void swap(int idx1, int idx2) {
        Object tmp = elements[idx1];
        elements[idx1] = elements[idx2];
        elements[idx2] = tmp;
    }

    private Integer getParentIndex(int index) {
        if (index == 1) return null;
        return index / 2;
    }

    private Integer getLeftChild(int index) {
        int result = index * 2;
        if (result > size) return null;
        return result;
    }

    private Integer getRightChild(int index) {
        int result = index * 2 + 1;
        if (result > size) return null;
        return result;
    }

    private void checkAndResize() {
        if (size < capacity) return;
        capacity += (capacity / 2);
        elements = Arrays.copyOf(elements, capacity);
    }

    private int compare(T t1, T t2) {
        if (comparator != null) {
            return comparator.compare(t1, t2);
        } else {
            return ((Comparable<? super T>) t1).compareTo(t2);
        }
    }
}
