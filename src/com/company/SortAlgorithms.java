package com.company;

public class SortAlgorithms {
    public static <T extends Comparable<T>> void bubbleSort(T[] data) {
        int position, scan;

        for(position = data.length - 1; position > 0; position--) {
            for(scan = 0; scan < position; scan++) {
                if(data[scan].compareTo(data[scan + 1]) > 0) {
                    swap(data, scan, scan + 1);
                }
            }
        }
    }

    private static <T extends Comparable<T>> void swap(T[] data, int el1, int el2) {
        T temp = data[el2];
        data[el2] = data[el1];
        data[el1] = temp;
    }
}
