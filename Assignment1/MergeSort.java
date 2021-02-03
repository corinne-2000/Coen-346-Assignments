package Assignment1;

public class MergeSort implements Runnable {

    private int[] array;
    private int[] temp;
    private int left;
    private int right;

    public MergeSort(int[] a, int[] t, int l, int r) {
        this.array = a;
        this.temp = t;
        this.left = l;
        this.right = r;
    }

    public MergeSort() {
        mergesort(array, temp, left, right);
    }

    public static void mergesort(int[] A, int[] temp, int l, int r) {

        int mid = (l + r) / 2; // Select midpoint
        if (l == r) {

            return; // List has one element
        }


        mergesort(A, temp, l, mid); // Mergesort first half
        mergesort(A, temp, mid + 1, r); // Mergesort second half

        MergeSort m1 = new MergeSort(A, temp, l, mid);
        Thread thread1 = new Thread(m1);
        MergeSort m2 = new MergeSort(A, temp, mid+1, r);
        Thread thread2 = new Thread(m2);
        thread1.start();
        thread2.start();
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (int i = l; i <= r; i++) // Copy subarray to temp
            temp[i] = A[i];

        // Do the merge operation back to A
        int i1 = l;
        int i2 = mid + 1;
        for (int curr = l; curr <= r; curr++) {

            if ((i1 < mid + 1) && (i2 <= r)) {
                if (temp[i1] < (temp[i2])) // Get smaller
                    A[curr] = temp[i1++];
                else
                    A[curr] = temp[i2++];

            } else if ((i1 == mid + 1) && (i2 <= r)) { // Left sublit exhausted
                A[curr] = temp[i2++];
            } else if (i2 > r) { // Right sublist exhausted
                A[curr] = temp[i1++];
            }
        }

    }

    @Override
    public void run() {
//call mergesort
        mergesort(array, temp, left, right);
    }
}
