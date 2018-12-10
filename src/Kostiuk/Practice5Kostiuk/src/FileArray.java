

	import java.io.BufferedReader;
	import java.io.FileReader;
	import java.io.IOException;
	import java.util.ArrayList;
	import java.util.List;

	public class FileArray {

	    public String[] readLines(String filename) throws IOException {
	        FileReader fileReader = new FileReader(filename);
	        BufferedReader bufferedReader = new BufferedReader(fileReader);
	        List<String> lines = new ArrayList<String>();
	        String line = null;
	        while ((line = bufferedReader.readLine()) != null) {
	            lines.add(line);
	        }
	        bufferedReader.close();
	        return lines.toArray(new String[lines.size()]);
	    }
	

//testFileArrayProvider  throws IOException 
		    public void main() throws IOException{
	        FileArray fap = new FileArray();
	        String[] lines = fap.readLines("sortArray10K.txt");
	        for (String line : lines) {
	            System.out.println(line);
	        }
	    }
	}
