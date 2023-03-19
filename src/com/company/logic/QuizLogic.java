package com.company.logic;

import com.company.dto.Option;
import com.company.dto.Word;
import com.company.generate.Generete;

import java.util.Random;
import java.util.Scanner;

public class QuizLogic {
    public boolean start(Word[] words){
        if (words.length < 4){
            System.out.println("Quiz not found");
            return false;
        }
        boolean isBack = false;
        while (true){
            if (isBack){
                break;
            }
            menu();
            int action = Generete.getAction();

            switch (action){
                case 1: {
                    generateQuizUz(words);
                    break;
                }
                case 2 : {
                    generateQuizEn(words);
                    break;
                }
                case 0 : {
                    isBack = true;
                    break;
                }
            }
        }

      return true;
    }

    public void menu(){
        System.out.println("1.Uz -> En");
        System.out.println("2.En -> Uz");
        System.out.println("0.Exit");
    }

    public void generateQuizUz(Word [] words){
        int trueAnswerCount = 0;
        int wrongAnswerCount = 0;
        for (int i = 0; i < words.length; i++) {
            Word word = words[i];
            if (word != null){
                System.out.println(word.titleUz);
                Option option = new Option(word.titleEn, true);
                Option[] options = getOptionUz(option, words);
                quiz(options);
                boolean b = checkUserAnswer(option);
                if (b) {
                    trueAnswerCount++;
                } else  {
                    wrongAnswerCount++;
                }
            }

        }

        System.out.println(trueAnswerCount);
        System.out.println(wrongAnswerCount);
    }

    public void generateQuizEn(Word [] words){
        int trueAnswerCount = 0;
        int wrongAnswerCount = 0;
        for (int i = 0; i < words.length; i++) {
            Word word = words[i];
            if (word != null){
                System.out.println(word.titleEn);
                Option option = new Option(word.titleUz, true);
                Option[] options = getOptionEn(option, words);
                quiz(options);
                boolean b = checkUserAnswer(option);
                if (b) {
                    trueAnswerCount++;
                } else  {
                    wrongAnswerCount++;
                }
            }

        }

        System.out.println(trueAnswerCount);
        System.out.println(wrongAnswerCount);
    }

    public Option [] getOptionUz(Option option, Word[]words){
        Option[] options = new Option[4];
        options[0] = option;
        int optionIndex = 1;
        Word [] wordsArray = new Word[words.length];
        for (int i = 0; i < words.length; i++) {
            wordsArray[i] = words[i];
        }
        Random random = Generete.getRandom();
        while (true){
            int index = random.nextInt(0, wordsArray.length);
                Word word = wordsArray[index];
                if (word != null && word.status && !word.titleEn.equals(option.name)){
                    if (optionIndex != 4){
                        options[optionIndex++] = new Option(word.titleEn, false);
                        wordsArray[index] = null;
                    } else {
                        break;
                    }

                } else {
                    int arrayCount = getArrayCount(wordsArray);
                    if (arrayCount == 0){
                        break;
                    }
                }

        }

        return options;
    }

    public Option [] getOptionEn(Option option, Word[]words){
        Option[] options = new Option[4];
        options[0] = option;
        int optionIndex = 1;
        Word [] wordsArray = new Word[words.length];
        for (int i = 0; i < words.length; i++) {
            wordsArray[i] = words[i];
        }
        Random random = Generete.getRandom();
        while (true){
            int index = random.nextInt(0, wordsArray.length);
                Word word = wordsArray[index];
                if (word != null && word.status && !word.titleUz.equals(option.name)){
                    if (optionIndex != 4){
                        options[optionIndex++] = new Option(word.titleUz, false);
                        wordsArray[index] = null;
                    } else {
                        break;
                    }

                } else {
                    int arrayCount = getArrayCount(wordsArray);
                    if (arrayCount == 0){
                        break;
                    }
                }

        }

        return options;
    }

    public void quiz(Option [] options) {
        Option [] optionArray = new Option[options.length];
        for (int i = 0; i < options.length; i++) {
            optionArray[i] = options[i];
        }
        char [] chars = {'a', 'b', 'c', 'd'};
        int charIndex = 0;
        Random random = Generete.getRandom();
        while (true){
            int index = random.nextInt(0, optionArray.length);
                Option option = optionArray[index];
                if (option != null){
                    System.out.println(chars[charIndex] + ": " + option.name);
                    option.opt = chars[charIndex++];
                    optionArray[index] = null;
                } else {
                    int arrayCount = getArrayCount(optionArray);
                    if (arrayCount == 0){
                        break;
                    }
                }



        }
    }

    public int getArrayCount(Option[] options){
        int count = 0;
        for (int i = 0; i < options.length; i++) {
            if (options[i] != null){
                count++;
            }
        }
        return count;
    }

    public int getArrayCount(Word[] words){
        int count = 0;
        for (int i = 0; i < words.length; i++) {
            if (words[i] != null){
                count++;
            }
        }
        return count;
    }

    public boolean checkUserAnswer(Option option){
        Scanner scanner = Generete.getScanner();
        System.out.print("-> ");
        String action = scanner.next();

        switch (action) {
            case "a" : {
                if (option.opt == 'a'){
                    return true;
                } else {
                    return false;
                }
            }
            case "b" : {
                if (option.opt == 'b'){
                    return true;
                } else {
                    return false;
                }
            }
            case "c" : {
                if (option.opt == 'c'){
                    return true;
                } else {
                    return false;
                }
            }
            case "d" : {
                if (option.opt == 'd'){
                    return true;
                } else {
                    return false;
                }
            }
            default:{
                return false;
            }
        }
    }

}
