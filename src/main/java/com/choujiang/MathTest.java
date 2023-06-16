package com.choujiang;

public class MathTest {
    public static void main(String[] args) {
        int a1 = 0;
        int a2 = 0;
        int a3 = 0;
        int a4 = 0;
        int a5 = 0;
        int a6 = 0;
        int a7 = 0;
        int a8 = 0;
        int a9 = 0;
        int a10 = 0;
        long total = 10000000;

        for (int i=0;i<=total;i++) {
            int test = test();
            if (test == 0) {
                a1++;
            }else if (test == 1) {
                a2++;
            }else if (test == 2) {
                a3++;
            }else if (test == 3) {
                a4++;
            }else if (test == 4) {
                a5++;
            }else if (test == 5) {
                a6++;
            }else if (test == 6) {
                a7++;
            }else if (test == 7) {
                a8++;
            }else if (test == 8) {
                a9++;
            }else if (test == 9) {
                a10++;
            }else {
                System.out.println("chaochu"+test);
            }
        }
        System.out.println("a1:"+a1 +" " + a1*1.0/total);
        System.out.println("a2:"+a2 +" " + a2*1.0/total);
        System.out.println("a3:"+a3 +" " + a3*1.0/total);
        System.out.println("a4:"+a4 +" " + a4*1.0/total);
        System.out.println("a5:"+a5 +" " + a5*1.0/total);
        System.out.println("a6:"+a6 +" " + a6*1.0/total);
        System.out.println("a7:"+a7 +" " + a7*1.0/total);
        System.out.println("a8:"+a8 +" " + a8*1.0/total);
        System.out.println("a9:"+a9 +" " + a9*1.0/total);
        System.out.println("a10:"+a10 +" " + a10*1.0/total);
    }

    public static int test(){
        double random = Math.random();
        //int random1 = (int) Math.floor(random* 10);
        int random1 = (int) (random* 10);
        System.out.println("random:"+random + "result:"+random1);
        return random1;
    }

    /*public static int randomNum(int max,int min){
        return Math.floor(Math.random()*(max-min+1)+min);
    }*/

}
