package com.company.dto;

public class Word {
    public int id;
    public String titleUz;
    public String titleEn;
    public boolean status;

    public Word() {
    }

    public Word(int id, String titleUz, String titleEn) {
        this.id = id;
        this.titleUz = titleUz;
        this.titleEn = titleEn;
        this.status = true;
    }

    public void detail(){
        System.out.println("Uzbek: " + this.titleUz + ", English: " + this.titleEn);
    }

    public void detailForGet(){
        System.out.println("Id: " + this.id + ", Uzbek: " + this.titleUz + ", English: " + this.titleEn);
    }
}
