package co.uk.cartaxcheck.utilities;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import co.uk.cartaxcheck.dataobjects.CarDetails;

public class TextFileUtility {

	/**
	 * Gives data of a text file as String
	 * 
	 * @param fileName
	 * @return
	 * @throws IOException
	 */
	public static String getDataFromInputFile(String fileName) throws IOException {

		File file = new File(System.getProperty("user.dir") + "\\testData\\inputFiles\\" + fileName);
		@SuppressWarnings("resource")
		BufferedReader br = new BufferedReader(new FileReader(file));
		String fileData = "";
		String st;
		while ((st = br.readLine()) != null) {
			fileData = fileData + st;
		}
		return fileData;
	}

	/**
	 * Read data from output file
	 * 
	 * @param fileName
	 * @return Map of string and CarDetails object
	 * @throws IOException
	 */
	public static Map<String, CarDetails> getOutPutFileData(String fileName) throws IOException {
		Map<String, CarDetails> outputData = new HashMap<>();
		File file = new File(System.getProperty("user.dir") + "\\testData\\outputFiles\\" + fileName);
		@SuppressWarnings("resource")
		BufferedReader br = new BufferedReader(new FileReader(file));
		String st;
		while ((st = br.readLine()) != null) {
			String carDetails[] = st.split(",");
			outputData.put(carDetails[0].trim().toUpperCase(),
					new CarDetails(carDetails[0].trim().toUpperCase(), carDetails[1].trim().toUpperCase(),
							carDetails[2].trim().toUpperCase(), carDetails[3].trim().toUpperCase(),
							carDetails[4].trim().toUpperCase()));
		}
		return outputData;
	}

	/**
	 * Extract registration numbers from the given string
	 * @param inputFileDate
	 * @return
	 */
	public static List<String> getRegistrationNumbers(String inputFileData) {
		List<String> allMatches = new ArrayList<>();
		Matcher m = Pattern.compile("([A-HK-PRSVWY][A-HJ-PR-Y])\\s?([0][2-9]|[1-9][0-9])\\s?[A-HJ-PR-Z]{3}")
				.matcher(inputFileData);
		while (m.find()) {
			allMatches.add(m.group());
		}
		return allMatches;
	}

}
