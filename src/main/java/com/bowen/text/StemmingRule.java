package com.bowen.text;
import java.util.Vector;

public class StemmingRule{
    String rootForm;
    Vector variedFormList;
   

    public StemmingRule(String line){
        String [] tokenList = line.split(" ");
        this.rootForm = tokenList[0];
        this.variedFormList = new Vector();
        for(int i=1;i<tokenList.length;i++)
              this.variedFormList.addElement(tokenList[i]);
    }
    public boolean isRegistered(Word indivWord){
    	boolean isRegistered = false;
        String wordName = indivWord.getWordName();
        int size = this.variedFormList.size();
        for (int i=0;i<size;i++){
            String variedForm = (String)this.variedFormList.elementAt(i);
            if (wordName.equals(variedForm)) {
            	isRegistered = true;
            	break;
            }
        }
        return isRegistered;
    }
    
    private Vector loadStemmingRuleList(String stemmingRuleFileName) {
    	Vector stemmingRuleList = new Vector();
    	FileString fs = new FileString(stemmingRuleFileName);
    	fs.loadFileString();
    	String stemmingRuleStream = fs.getFileString();
    	String[] lineList = stemmingRuleStream.split("\n");
    	for (int i = 0; i < lineList.length; i++) {
			StemmingRule rule = new StemmingRule(lineList[i]);
			stemmingRuleList.addElement(rule);
		}
    	return stemmingRuleList;
    }
 

    /*private String changeRootForm(Word indivWord, Vector stemmingRuleList) {
    	//String wordName = indivWord.getWordName();
    	int size = stemmingRuleList.size();
    	for (int i = 0; i < size; i++) {
			StemmingRule rule = (StemmingRule)stemmingRuleList.elementAt(i);
			if (rule.isRegistered(indivWord)) {
				String rootForm = rule.getRootForm();
				return rootForm;
			}
		}
    	return indivWord.getWordName();
    }*/
	public String getRootForm() {
		return null;
	}
}

//    private Object changeRootForm;(study06.Word Object indivWord;
//        indivWord, Vector stemmingRuleList) {
//        return null;
//    }

//    String getRootForm() {
//        return null;
//    }
//
//
//}
//
//    private String getRootForm() {
//        return null;
//    }
