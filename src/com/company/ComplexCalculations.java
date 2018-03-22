package com.company;

public class ComplexCalculations implements Runnable {
    @Override
    public void run() {
        int j = 0;
        for (int i=0; i<2000000; i++) {
            for (int k = 0; k<20000; k++) {
                for (int n = 0; n<20; n++) {
                    j++;
                }
            }
        }
        System.out.println("result"+j);
    }
}
