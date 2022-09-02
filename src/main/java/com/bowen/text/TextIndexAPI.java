package com.bowen.text;
import java.util.Vector;
public class TextIndexAPI {
	Text indivText;
	String corpusPath;
	String fileName;
	 public TextIndexAPI(String filename,String corpusPath) {
		
		// TODO Auto-generated constructor stub
		 this.indivText=new Text(fileName);
		 
		 this.corpusPath=corpusPath;
	}
	 public Vector indexText() {
		 /*indivText.indexTextContent(stemmingRuleFileName, stopWordFileName);*/
		 
		return null;
	}
	
	
}
