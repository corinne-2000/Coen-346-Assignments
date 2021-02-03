# Code for first Assignment :

//this is the first class 

public class DemoClass {

    public static void main(String args[]) {



        int[] array = {3304, 8221, 26849, 14038, 1509, 6367, 7856, 21362};
        int[] temp = new int[array.length];
        MergeSort m = new MergeSort(array, temp, 0, array.length - 1);
        Thread thread = new Thread(m);
        thread.start();

        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }








//        test t = new test(0);
//        thread thread = new thread(t);
//        thread.start();
//        try{
//            thread.join();
//        }catch (interruptedexception e) {
//            e.printstacktrace();
//        }





    }

    public static void print(int[] A) {
        for (int i = 0; i < A.length; i++){
            System.out.println(A[i]);
        }
    }
}
