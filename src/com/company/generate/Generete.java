package com.company.generate;

import java.util.Random;
import java.util.Scanner;

public class Generete {
    static int id;

    public static int getId(){
        id = id + 1;
        return id;
    }

    public static int getAction() {
        Scanner scanner = getScanner();
        System.out.print("-> ");
        return scanner.nextInt();
    }

    public static Scanner getScanner() {
        return new Scanner(System.in);
    }

    public static Random getRandom() {
        return new Random();
    }
}
