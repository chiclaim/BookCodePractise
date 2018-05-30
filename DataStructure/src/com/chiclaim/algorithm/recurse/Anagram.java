package com.chiclaim.algorithm.recurse;

/**
 * Created by Chiclaim on 2018/5/30.
 */
public class Anagram {

    private int count;
    private int size;
    private char[] arr;

    public void anagram(String word) {
        size = word.length();
        arr = word.toCharArray();
        doAnagram(size);
    }


    private void doAnagram(int newSize) {
        if (newSize == 1) {
            return;
        }

        for (int j = 0; j < newSize; j++) {
            doAnagram(newSize - 1);
            if (newSize == 2) {
                displayWord();
            }
            rotate(newSize);
        }
    }

    private void rotate(int newSize) {
        int j;
        int position = size - newSize;
        char tmp = arr[position];
        for (j = position + 1; j < size; j++) {
            arr[j - 1] = arr[j];
        }
        arr[j - 1] = tmp;
    }

    private void displayWord() {
        if (count > 99) {
            System.out.print(" ");
        }
        if (count < 9) {
            System.out.print(" ");
        }
        System.out.print(++count + " ");
        for (int j = 0; j < size; j++) {
            System.out.print(arr[j]);
        }
        System.out.print("  ");
        if (count % 6 == 0) {
            System.out.println("");
        }
    }


    public static void main(String[] args) {
        Anagram anagram = new Anagram();
        anagram.anagram("anagram");
    }


}
