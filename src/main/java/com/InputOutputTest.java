import java.io.BufferedReader;
import java.io.FileReader;


public class InputOutputTest {
	public static void main(String[] args) throws Exception {
		readFile("E:/练习一基因IO应用/all_aml_test.res");
		 
		
		
	}
	
	public static void readFile(String filePath) throws Exception {
		FileReader fs = null;
		BufferedReader br = null;
			fs = new FileReader(filePath);
			br = new BufferedReader(fs);
			String record = null;
			int k = 0;
			while ((record = br.readLine()) != null) {
				k++;
				System.out.println(k+"行："+record);
				
			}
			br.close();
			fs.close();
		
	}

}
