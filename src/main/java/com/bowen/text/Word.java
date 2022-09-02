package com.bowen.text;

public class Word {
    String wordName;
    double worldWeight;

    public Word(String wordName) {
        this.wordName = wordName;
        this.worldWeight = 0.0;
    }

    public String getWordName() {
        return wordName;
    }

    public void setWorldWeight(double worldWeight) {
        this.worldWeight = worldWeight;
    }
    public void computeWeight(String corpusPath,String fullText){}

    public void setWordName(Object changeRootForm) {
    }

    public void computeWordFrequency(Object fullText) {
    }
}

