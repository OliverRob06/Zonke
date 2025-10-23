import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Set;
import java.util.LinkedHashSet;

public class fileHanding {

	profile P = new profile();
	
	FileWriter fileWriter = null;
	BufferedWriter bufferedWriter = null;

	String fileName = "zonke-profiles.txt";

    // Reads the file puts the inforamtion in to the sets
	public void readLineFromAFile() {
		FileReader fileReader = null;
		BufferedReader bufferedReader = null;
	
		String nextLine;
		
		try {
			fileReader = new FileReader(fileName);
			bufferedReader = new BufferedReader(fileReader);
			nextLine = bufferedReader.readLine();

			while (nextLine != null) {
                // Splits the line and puts to an array which is then put into the Hashset
				String[] profileLine = nextLine.split(",");
				Set<String> newProfileSet = new LinkedHashSet<>();
				
				for (String element : profileLine) {
					newProfileSet.add(element);
				}

				profile.IDSet.add(newProfileSet);

				nextLine = bufferedReader.readLine();
			}

        //Error handling
		} catch (IOException e) {
			System.out.println("Error reading file " + e.getMessage());
		} finally {
			if (bufferedReader != null) {
				try {
					bufferedReader.close();
				}
				catch (IOException e) {
					System.out.println("Sorry, there has been a problem closing the file");
				}
			}
		}
	}

    //Gets the information and adds writes it to the file.
	public void writeToFile() {
		try {
			fileWriter = new FileWriter(fileName);
			bufferedWriter = new BufferedWriter(fileWriter);

            // Writes what is in IDSet to the file.
            for (Set<String> element : profile.IDSet) {
				for (String string : element) {
					bufferedWriter.write(string + ",");
				}
				bufferedWriter.newLine();
			}

		// Error handling
		} catch (IOException e) {
			System.out.println("Error writing to the file" + e);
		} finally {
			if (bufferedWriter != null){
				try {
					bufferedWriter.close();
				} catch (IOException e) {
					System.out.println("Error closing file" + e);;
				}
			}
		}
	}
		
}