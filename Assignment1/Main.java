import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		String projectPath = new File("").getAbsolutePath();
		String filePath = projectPath.concat("\\Input.txt");
		List<Integer> input = new ArrayList<Integer>();
		
		try {
			Scanner scanner = new Scanner(new File(filePath));
			while(scanner.hasNext()) {
				input.add(Integer.parseInt(scanner.next()));
			}
			scanner.close();
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		ThreadedMergeSort m = new ThreadedMergeSort(input);
		Thread t = new Thread(m);
		t.start();
		
		try {
			t.join();
			Thread.sleep(1000);
			List<String> output = m.getOutputList();
			FileWriter writer = new FileWriter("Output.txt");
			for(int i = 0; i < output.size(); i++) {
				writer.write(output.get(i) + "\n");
			}
			writer.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
