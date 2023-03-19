package com.company.logic;

import com.company.dto.Word;
import com.company.generate.Generete;

import java.util.Random;
import java.util.Scanner;

public class LogicMain {
    public void start (){
        Word[] words = new Word[10];
        words[0] = new Word(0, "Olma", "Apple");
        words[1] = new Word(1, "Ruchka", "Pen");
        words[2] = new Word(2, "Qalam", "Pensil");
        words[3] = new Word(3, "Pul", "Money");
        words[4] = new Word(4, "Imtihon", "Exam");
        boolean isBack = false;
        while (true) {
            if (isBack){
                break;
            }
            menu();
            int action = Generete.getAction();
            switch (action){
                case 1: {
                    if (checkWordsArray(words)){
                        Word word = addWord(words);
                        words[getEmptyIndex(words)] = word;
                    } else {
                        System.out.println("Please, clean history");
                    }
                    break;
                }
                case 2 : {
                    allWords(words);
                    break;
                }
                case 3 : {
                    RandomLogic randomLogic = new RandomLogic();
                    randomLogic.start(words);
                    break;
                }
                case 4 : {
                    QuizLogic quizLogic = new QuizLogic();
                    quizLogic.start(words);
                    break;
                }
                case 5 : {
                    allWordsForUpdating(words);
                    System.out.print("Enter word id ");
                    int id = Generete.getAction();
                    boolean b = deleteWord(id, words);
                    if (b){
                        System.out.println("Success!");
                    } else {
                        System.out.println("Failed");
                    }
                    break;
                }
                case 6 : {
                    allWordsForUpdating(words);
                    System.out.println("Enter word id: ");
                    int id = Generete.getAction();
                    boolean b = editWord(id, words);
                    if (b){
                        System.out.println("Success");
                    }
                    break;
                }
                case 7 : {
                    boolean isTrachBack = false;
                    while (true){
                        if (isTrachBack){
                            break;
                        }
                        trashMenu();
                        int tAction = Generete.getAction();
                        switch (tAction) {
                            case 1 : {
                                allDeletedWords(words);
                                break;
                            }
                            case 2 : {
                                deleteAllFromTrash(words);
                                break;
                            }
                            case 0 : {
                                isTrachBack = true;
                                break;
                            }
                        }
                    }
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
        System.out.println("1.Add Word");
        System.out.println("2.All words");
        System.out.println("3.Random word");
        System.out.println("4.Test");
        System.out.println("5.Delete word");
        System.out.println("6.Update word");
        System.out.println("7.Trash");
        System.out.println("0.Exit");
    }

    public void trashMenu(){
        System.out.println("1.All history");
        System.out.println("2.Clear");
        System.out.println("0.Exit");
    }

    public Word addWord(Word[] words){
        Scanner scanner = Generete.getScanner();
        while (true) {
            System.out.print("Enter title uz: ");
            String titleUz = scanner.nextLine();

            System.out.print("Enter title en: ");
            String titleEn = scanner.nextLine();

            boolean b = findByName(titleUz, titleEn, words);
            if (!b){
                Word word = new Word();
                word.id = Generete.getId();
                word.status = true;
                word.titleUz = titleUz;
                word.titleEn = titleEn;
                return word;
            } else {
                System.out.println("This is word already exist");
            }
        }
    }

    public boolean editWord(int id, Word[] words){
        Word word = getWordById(id, words);
        if (word == null){
            System.out.println("This id not found");
            return false;
        }
        Scanner scanner = Generete.getScanner();
        System.out.print("Enter title uz: ");
        String titleUz = scanner.nextLine();
        System.out.print("Enter title en: ");
        String titleEn = scanner.nextLine();

        boolean b = findByName(titleUz, titleEn, words);
        if (b) {
            System.out.println("This is word already exist");
            return false;
        }

        word.titleUz = titleUz;
        word.titleEn = titleEn;
        return true;
    }

    public boolean deleteWord(int id, Word[] words){
        Word word = getWordById(id, words);
        if (word != null){
            word.status = false;
            return true;
        }
        return false;
    }

    public void allWords(Word [] words){
        for (int i = 0; i < words.length; i++) {
            Word word = words[i];
            if (word != null && word.status){
                word.detail();
            }
        }
    }

    public void allWordsForUpdating(Word [] words){
        for (int i = 0; i < words.length; i++) {
            Word word = words[i];
            if (word != null && word.status){
                word.detailForGet();
            }
        }
    }

    public void deleteAllFromTrash(Word [] words){
        for (int i = 0; i < words.length; i++) {
            Word word = words[i];
            if (word != null && !word.status){
                words[i] = null;
            }
        }
    }

    public boolean checkWordsArray(Word [] words) {
        int count = 0;
        for (int i = 0; i < words.length; i++) {
            Word word = words[i];
            if (word == null){
                count++;
            }
        }
        if (count == 0) {
            return false;
        }
        return true;
    }

    public int getEmptyIndex(Word[] words){
        for (int i = 0; i < words.length; i++) {
            if (words[i] == null){
                return i;
            }
        }
        return -1;
    }

    public Word getWordById(int id, Word[] words){
        for (int i = 0; i < words.length; i++) {
            Word word = words[i];
            if (word.status && word.id == id){
                return word;
            }
        }
        return null;
    }

    public boolean findByName (String nameUz,String nameEn, Word[]words) {
        for (int i = 0; i < words.length; i++) {
            Word word = words[i];
            if (word != null && word.titleUz.equalsIgnoreCase(nameUz) &&
                word.titleEn.equalsIgnoreCase(nameEn)){
                return true;
            }
        }
        return false;
    }

    public void allDeletedWords(Word[ ] words) {
        for (int i = 0; i < words.length; i++) {
            Word word = words[i];
            if (word != null && !word.status){
                word.detailForGet();
            }
        }
    }
    // TODO test logic in the class











}
