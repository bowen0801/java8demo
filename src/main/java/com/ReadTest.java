
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class ReadTest {
    public static void main(String[] args) throws IOException {
            FileInputStream fis=null;  
            InputStreamReader isr=null; 
            BufferedReader br=null; 
        fis= new FileInputStream("E:/练习一基因IO应用/all_aml_train.res");  
        isr= new InputStreamReader(fis,"UTF-8"); 
        br=new BufferedReader(isr); 
        String text;
        int k = 0;
        try {
            while ((text=br.readLine())!=null)
            {
            	k++;
                System.out.println(k+"行："+text); 
                String[] split = text.split("\t");
                
                System.out.println(Arrays.toString(split));
            }
            } catch (IOException e) {
                e.printStackTrace();
            }
        finally {
            br.close();
        }
        }
    }

