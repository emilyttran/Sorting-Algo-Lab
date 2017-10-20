import java.util.Arrays;
import java.util.Random;

public class Sort {
    public static void main(String args[]){
        Random r = new Random();
        int[] arr = new int[100];
        for(int l = 0; l < 100; l++){
            arr[l] = r.nextInt(100);
        }
        System.out.println(Arrays.toString(insertionSort(arr)));
    }
    public static int[] insertionSort(int[] a){
        for(int i = 0; i < a.length-1; i++){
            int key = a[i + 1];

            while(i >= 0 && a[i] > key){
                a[i+1] = a[i];
                i--;
            }
            a[i+1] = key;
        }
        return a;
    }

    public static int[] bubbleSort(int[] a){
        boolean sorted = false;
        int count = 1;

        while(!sorted) {
            sorted = true;
            for (int i = 0; i < a.length-count; i++) {
                if (a[i] > a[i + 1]) {
                    int temp = a[i + 1];
                    a[i + 1] = a[i];
                    a[i] = temp;
                    sorted = false;
                }
            }
            count++;
        }
        return a;
    }
}
