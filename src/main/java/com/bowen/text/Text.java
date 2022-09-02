package com.bowen.text;

import java.io.File;
import java.util.Vector;

import javax.sound.sampled.LineListener;

public class Text {
    String fileName;
    String textContent;
    Vector wordList;

    public Text(String fileName) {
        this.fileName = fileName;
        this.textContent = textContent;
        this.wordList = wordList;
    }

    public String getFileName() {
        return fileName;
    }

    public String getTextContent() {
        return textContent;
    }

    public Vector getWordList() {
        return wordList;
    }
    
    public void loadTextContent(){
    	FileString fs=new FileString(this.fileName);
    	fs.loadFileString();
    	this.textContent=fs.getFileString();
    }
   
    private void stemWordList(String stemmingRuleFileName){
    	Vector stemmingRuleList =this.loadStemmingRuleList(stemmingRuleFileName);
    	int size=this.wordList.size();
    	for (int i = 0; i < size; i++) {
			Word indivWord= (Word) this.wordList.elementAt(i);
			indivWord.setWordName(this.changeRootForm(indivWord,stemmingRuleList));
			this.wordList.setElementAt(indivWord, i);
    	}
    }
    
    private String changeRootForm(Word indivWord, Vector stemmingRuleList) {
	
    	String wordName=indivWord.getWordName();
    	int size=stemmingRuleList.size();
    	for (int i = 0; i < size; i++) {
			StemmingRule rule =(StemmingRule) stemmingRuleList.elementAt(i);
			if (rule.isRegistered(indivWord)) {
				String rootForm=rule.getRootForm();
				return rootForm;
			}
    	}
    	return indivWord.getWordName();
	}

	private Vector loadStemmingRuleList(String stemmingRuleFileName) {
		// TODO Auto-generated method stub
		Vector stemmingRuleList=new Vector ();
		FileString fs=new FileString(stemmingRuleFileName);
		fs.loadFileString();
		String stemmingRuleStream =fs.getFileString();
		String[] lineList= stemmingRuleStream.split("\n");
		for (int i = 0; i < lineList.length; i++) {
			StemmingRule rule =new StemmingRule(lineList[i]);
			stemmingRuleList.addElement(rule);
		}
		return stemmingRuleList;
	}

	
    private Vector loadStopWordList(String stopWordFileName) {
		// TODO Auto-generated method stub
    	Vector stopWordList=new Vector();
    	FileString fs=new FileString(stopWordFileName);
    	fs.loadFileString();
    	String stopWordStream=fs.getFileString();
    	String[] lineList=stopWordStream.split("\n");
    		for (int j = 0; j < lineList.length; j++) {
				
				stopWordList.addElement(lineList[j]);
			}
    	
		return stopWordList;
	}
    
    private void tokenizeTextContent(){
    	String[] tokenList=this.textContent.split(".?!");
    	int size =tokenList.length;
    	for (int i = 0; i < tokenList.length; i++) {
			Word indivWord =new Word(tokenList[i].toLowerCase());
		}
    }
    
    
    private void removeStopWords(String stopWordFileName){
    	Vector stopWordList =this.loadStopWordList(stopWordFileName);
    	int size =this.wordList.size();
    	for (int i = 0; i < size; i++) {
			Word indivWord=(Word)this.wordList.elementAt(i);
			if (this.isRegistered(indivWord,stopWordList)) {
				this.wordList.remove(i);
			}
    	}
    }
	private boolean isRegistered(Word indivWord, Vector stopWordList) {
		int size=stopWordList.size();
		String wordName= indivWord.getWordName();
		for (int i = 0; i < size; i++) {
			String stopWord=(String)stopWordList.elementAt(i);
			if(wordName==stopWord)
				return true;
		}
		
		return false;
	}

	private void indexTextContent(){};
    public void indexTextContent (String stemmingRuleFileName,String stopWordFileName) {
        this.loadTextContent();	
        this.tokenizeTextContent();
        this.stemWordList(stemmingRuleFileName);
        this.removeStopWords(stopWordFileName);
    }
}
