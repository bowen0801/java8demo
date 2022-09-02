
import java.io.*;

public class Test01 {

		public static void main(String[] args) throws IOException {
			// TODO Auto-generated method stub
			//File f1=new File("f:");
			 //节点类
	            //InputStreamReader isr=null; //转换类
	            //BufferedReader br=null; 
			 FileInputStream fis= new FileInputStream("e:/all_aml_train.res");  //填写读取文件所需要的路径
		        //isr= new InputStreamReader(fis,"UTF-8"); 
			 //传入fis 并且设置字符编码 ，需要与写入文件时候的编码相同
			
			 int len=0;
			 while((len=fis.read())!=-1)
				System.out.print((char)len);
				
			fis.close();
		}

	}