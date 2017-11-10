import java.util.Arrays;
import java.util.Random;

public class Sort {
    public static void main(String args[]){
        Random r = new Random();
        int[] arr = new int[50];
        for(int l = 0; l < 50; l++){
            arr[l] = r.nextInt(100);
        }
        shellSort(arr);
        System.out.print(Arrays.toString(arr));
    }

    public static void shellSort(int a[])
    {
        int n = a.length;

        for (int gap = n/2; gap > 0; gap /= 2)
        {
            for (int i = gap; i < n; i += 1)
            {
                int temp = a[i];

                int j;
                for (j = i; j >= gap && a[j - gap] > temp; j -= gap)
                    a[j] = a[j - gap];

                a[j] = temp;
            }
        }
    }

    public static void quickSort(int[] a, int low, int high){
        if(low < high) { //if there are elements left
            int pivot = partition(a, low, high);

            quickSort(a, low, pivot - 1); //partition the left of the pivot
            quickSort(a, pivot + 1, high); //partition the right of the pivot
        }
    }

    public static int partition(int[] a, int low, int high){
        int pivot = a[high]; //setting the top element as the pivot
        int i = low - 1;

        for(int j = low; j < high; j++){ //check every element and compare it to the pivot
            if(a[j] <= pivot){  //if the current element is smaller than the pivot
                i++;
                swap(a,i,j); //put the current element to the left of the pivot
            }
        }
        swap(a,i+1,high);
        return i+1; //return the pivot
    }

    public static void swap(int[] a, int b, int c){
        int temp = a[b];
        a[b] = a[c];
        a[c] = temp;
    }

    public static int[] mergeSort(int[] a){
        if(a.length > 1) {
            int numLeftElements = a.length / 2;
            int numRightElements = a.length - numLeftElements;
            int[] arrLeft = new int[numLeftElements];
            int[] arrRight = new int[numRightElements];

            for (int i = 0; i < numLeftElements; i++) {  //split left elements
                arrLeft[i] = a[i];
            }

            for (int j = numLeftElements; j < numLeftElements + numRightElements; j++) { //split right elements
                arrRight[j - numLeftElements] = a[j];
            }

            arrLeft = mergeSort(arrLeft);
            arrRight = mergeSort(arrRight);

            int leftMark = 0;
            int rightMark = 0;
            int i = 0;

            while (arrLeft.length != leftMark && arrRight.length != rightMark) {
                if (arrLeft[leftMark] > arrRight[rightMark]) {
                    a[i++] = arrRight[rightMark++];
                } else {
                    a[i++] = arrLeft[leftMark++];
                }
            }

            while (arrLeft.length != leftMark) {
                a[i++] = arrLeft[leftMark++];
            }

            while (arrRight.length != rightMark) {
                a[i++] = arrRight[rightMark++];
            }
        }
    return a;
    }

    public static int[] selectionSort(int[] a){
        for(int j = 0; j < a.length-1; j++) {
            int min = j;
            for (int i = j+1; i < a.length; i++) {
                if (a[i] < a[min])
                    min = i;
            }
            int temp = a[min];
            a[min] = a[j];
            a[j] = temp;
        }
        return a;
    }

    public static int[] insertionSort(int[] a){ //
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

    public static int[] bubbleSort(int[] a){ //bubble largest element to the end
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
