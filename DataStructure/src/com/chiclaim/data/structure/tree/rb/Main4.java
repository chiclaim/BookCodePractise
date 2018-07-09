package com.chiclaim.data.structure.tree.rb;

import java.util.Random;

public class Main4 {
    public static void main(String[]args){
        Random random = new Random(10);
        for (int i=0;i<10;i++){
            System.out.println(random.nextInt(Integer.MAX_VALUE));
        }
    }
}
