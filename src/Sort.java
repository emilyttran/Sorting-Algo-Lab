import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.WorkbookUtil;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

import static java.lang.Math.toIntExact;


public class Sort {
    public static void main(String args[]){
        int [] arr;
        int NUM_TRIALS = 25;
        int NUM_ELEMENTS = 10; //number of elements to sort
        int NUM_BOUND = 100; //highest value for each element
        long t1, t2, totalTime;
        long[][] timeArr = new long[7][10];
        int[] numEleArr = new int[10];
        int sortFunc;

        //get timing data
        for(int n = 0; n < 10; n++) {
            NUM_ELEMENTS += NUM_ELEMENTS;
            numEleArr[n] = NUM_ELEMENTS;
            //BUBBLE SORT
            totalTime = 0;
            for (int i = 0; i < NUM_TRIALS; i++) {
                arr = createRandArr(NUM_ELEMENTS, NUM_BOUND);

                t1 = System.nanoTime();
                bubbleSort(arr);
                t2 = System.nanoTime();
                totalTime += (t2 - t1);
            }
            sortFunc = 0;
            timeArr[sortFunc][n] = totalTime / NUM_TRIALS;

            //INSERTION SORT
            totalTime = 0;
            for (int i = 0; i < NUM_TRIALS; i++) {
                arr = createRandArr(NUM_ELEMENTS, NUM_BOUND);

                t1 = System.nanoTime();
                insertionSort(arr);
                t2 = System.nanoTime();
                totalTime += (t2 - t1);
            }
            sortFunc = 1;
            timeArr[sortFunc][n] = totalTime / NUM_TRIALS;

            //SELECTION SORT
            totalTime = 0;
            for (int i = 0; i < NUM_TRIALS; i++) {
                arr = createRandArr(NUM_ELEMENTS, NUM_BOUND);

                t1 = System.nanoTime();
                selectionSort(arr);
                t2 = System.nanoTime();
                totalTime += (t2 - t1);
            }
            sortFunc = 2;
            timeArr[sortFunc][n] = totalTime / NUM_TRIALS;

            totalTime = 0;
            for (int i = 0; i < NUM_TRIALS; i++) {
                arr = createRandArr(NUM_ELEMENTS, NUM_BOUND);

                t1 = System.nanoTime();
                mergeSort(arr);
                t2 = System.nanoTime();
                totalTime += (t2 - t1);
            }
            sortFunc = 3;
            timeArr[sortFunc][n] = totalTime / NUM_TRIALS;

            //QUICK SORT
            totalTime = 0;
            for (int i = 0; i < NUM_TRIALS; i++) {
                arr = createRandArr(NUM_ELEMENTS, NUM_BOUND);

                t1 = System.nanoTime();
                quickSort(arr, 0, NUM_ELEMENTS - 1);
                t2 = System.nanoTime();
                totalTime += (t2 - t1);
            }
            sortFunc = 4;
            timeArr[sortFunc][n] = totalTime / NUM_TRIALS;

            //SHELL SORT
            totalTime = 0;
            for (int i = 0; i < NUM_TRIALS; i++) {
                arr = createRandArr(NUM_ELEMENTS, NUM_BOUND);

                t1 = System.nanoTime();
                shellSort(arr);
                t2 = System.nanoTime();
                totalTime += (t2 - t1);
            }
            sortFunc = 5;
            timeArr[sortFunc][n] = totalTime / NUM_TRIALS;

            //JAVA SORT
            totalTime = 0;
            for (int i = 0; i < NUM_TRIALS; i++) {
                arr = createRandArr(NUM_ELEMENTS, NUM_BOUND);

                t1 = System.nanoTime();
                Arrays.sort(arr);
                t2 = System.nanoTime();
                totalTime += (t2 - t1);
            }
            sortFunc = 6;
            timeArr[sortFunc][n] = totalTime / NUM_TRIALS;
        }


        try {

            //initialize Excel files
            XSSFWorkbook workbook = new XSSFWorkbook();
            String safename = WorkbookUtil.createSafeSheetName("Data");
            Sheet sheet = workbook.createSheet(safename);

            //add data to sheet
            Row row = sheet.createRow(0);
            for(int i = 1; i < 11; i++){
                row.createCell(i).setCellValue(numEleArr[i-1]);
            }

            for(int i = 1; i < 8; i++){
                row = sheet.createRow((short)i);
                for(int j = 1; j < 11; j++) {
                    row.createCell(j).setCellValue(timeArr[i-1][j-1]);
                }
            }
            FileOutputStream fileOut = new FileOutputStream("HW3Data.xlsx");
            workbook.write(fileOut);
            fileOut.close();
            System.out.println(Arrays.toString(numEleArr));
            for(int i = 0; i < timeArr.length; i++){
                System.out.println(Arrays.toString(timeArr[i]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public static int[] createRandArr(int numElements, int bound){
        Random r = new Random();
        int[] arr = new int[numElements];
        for(int i = 0; i < numElements; i++){
            arr[i] = r.nextInt(bound);
        }
        return arr;
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
