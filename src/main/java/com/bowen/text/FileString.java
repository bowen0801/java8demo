package com.bowen.text;
import  java.io.*;
public class FileString {
    String fileName;//the name of the file to which we try to access
    String fileString;
    public FileString (String fileName){
        this.fileName = fileName;
        this.fileString = "";
    }
    public FileString (String fileName,String fileString){
        this.fileName = fileName;
        this.fileString =fileString;
    }

    public String getFileString() {
        return fileString;
    }
    
    public void setFileString(String fileString) {
        this.fileString = fileString;
    }
    public void loadFileString(){
    	try {
			RandomAccessFile stream =new RandomAccessFile(this.fileName,"r");
			long length=stream.length();
			byte[] byArray=new byte[(int)length];
			stream.readFully(byArray);
			this.fileString=new String(byArray);
			stream.close();
		} catch (IOException e) {
			// TODO: handle exception
			System.out.println("There is the error in file processing!"+e);
		}
    	
    }
    public void saveFileString(){
    	try {
			RandomAccessFile stream =new RandomAccessFile(this.fileName,"rw");
			stream.writeBytes(this.fileString);
			stream.close();
		} catch (IOException e) {
			// TODO: handle exception
			System.out.println("There is the error in file processing!"+e);
		}
    	
    }
}

