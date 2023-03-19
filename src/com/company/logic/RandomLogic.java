package com.company.logic;

import com.company.dto.Word;
import com.company.generate.Generete;

import java.util.Random;
import java.util.Scanner;

public class RandomLogic {
    public boolean start (Word[] words){
        Word [] words1 = new Word[words.length];
        for (int i = 0; i < words.length; i++) {
            words1[i] = words[i];
        }
        boolean isBack = false;
        while (true) {
            if (isBack){
                return isBack;
            }
            menu();
            int action = Generete.getAction();
            switch (action) {
                case 1 : {
                    startTestUz(words1);
                    break;
                }
                case 2 : {
                    startTestEn(words1);
                    break;
                }
                case 0 : {
                    isBack = true;
                    break;
                }
            }
        }
    }

    public void menu(){
        System.out.println("1.Uz - En");
        System.out.println("2.En - Uz");
        System.out.println("0.Exit");

    }

    public void startTestUz(Word [ ] words){
        Word  [] currentArray = new Word[words.length];
        for (int i = 0; i < words.length; i++) {
            currentArray[i] = words[i];
        }
        int trueAnswerCount = 0;
        int wrongAnswerCount = 0;
        String currentTitle = "";

        for (int i = 0; i < currentArray.length; i++) {
            Word word = getRandomWord(currentArray);
            if (word != null && word.status){
                String str = getTitleFromConsol(word.titleUz);
                if (str.equalsIgnoreCase(word.titleEn)){
                    trueAnswerCount++;
                } else {
                    wrongAnswerCount++;
                }
            } else {
                break;
            }
        }

        System.out.println("True answers: " + trueAnswerCount);
        System.out.println("Wrong answers: " + wrongAnswerCount);
    }

    public void startTestEn(Word [ ] words){
        Word [] currentArray = new Word[words.length];
        for (int i = 0; i < words.length; i++) {
            currentArray[i] = words[i];
        }
        int trueAnswerCount = 0;
        int wrongAnswerCount = 0;
        String currentTitle = "";

        for (int i = 0; i < currentArray.length; i++) {
            Word word = getRandomWord( currentArray);
            if (word != null && word.status){
                String str = getTitleFromConsol(word.titleEn);
                if (str.equalsIgnoreCase(word.titleUz)){
                    trueAnswerCount++;
                } else {
                    wrongAnswerCount++;
                }
            } else {
                break;
            }
        }

        System.out.println("True answers: " + trueAnswerCount);
        System.out.println("Wrong answers: " + wrongAnswerCount);
    }

    public Word getRandomWord(Word[]words) {
        Random random = Generete.getRandom();
        while (true) {
            int index = random.nextInt(0, words.length);
            for (int i = 0; i < words.length; i++) {
                if (words[index] != null){
                    Word word = words[index];
                    words[index] = null;
                    return word;
                } else {
                    int arrayCount = getArrayCount(words);
                    if (arrayCount == 0){
                        return null;
                    }
                }
            }
        }
    }

    public int getArrayCount(Word[] words) {
        int count = 0;
        for (int i = 0; i < words.length; i++) {
            if (words[i] != null){
                count++;
            }
        }

        return count;
    }

    public String getTitleFromConsol(String title){
        Scanner scanner = Generete.getScanner();
        System.out.print(title + " -> ");
        return scanner.nextLine();
    }
}
