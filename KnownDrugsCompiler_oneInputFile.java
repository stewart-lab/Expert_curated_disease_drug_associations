import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * Program retrieves ID and a list of synonyms from drugs/biologics lexicon
 * 
 * @author Kalpana Raja
 *
 */

public class KnownDrugsCompiler_oneInputFile {
	
	//global attributes
	static ArrayList<String> drugIDs = new ArrayList<String>();
	
	/**
	 * Program execution starts here
	 * 
	 * @param args
	 */
	
	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();

		String line="";

		String arg1 = args[0]; //drugs/biologics lexicon
		String arg2 = args[1]; //input_file
		String arg3 = args[2]; //output_file
		
		HashMap<String, String> drugsAndID = new LinkedHashMap<String, String>();
		ArrayList<String> drugSynonymsAndID = new ArrayList<String>();
		ArrayList<String> allOutput = new ArrayList<String>();
		
		Pair pair = new Pair();
		
		try {
			//lexicon
			FileInputStream fis0 = new FileInputStream(arg1);
			InputStreamReader isr0 = new InputStreamReader(fis0,"UTF-8");
		    BufferedReader br0 = new BufferedReader(isr0);
		    while((line = br0.readLine()) != null) {
			    if(line.startsWith("drug/synonym")) continue;
                drugSynonymsAndID.add(line);

		    	String[] arrLine = line.split("\t");
		    	drugsAndID.put(arrLine[0].toLowerCase(), arrLine[1]);
		    }
		    
		    //input file
		    FileInputStream fis1 = new FileInputStream(arg2);
			InputStreamReader isr1 = new InputStreamReader(fis1,"UTF-8");
		    BufferedReader br1 = new BufferedReader(isr1);
		    while((line = br1.readLine()) != null) {
		    	pair = getDrugInfo(line, drugsAndID, drugSynonymsAndID);
		    	
		    	String output = pair.getOutput();
		    	drugIDs = pair.getdrugIDs();
		    	
		    	if(!output.isEmpty()) allOutput.add(output);
			}
		    
		    //output file
			FileOutputStream fos = new FileOutputStream(arg3);
		    OutputStreamWriter osr = new OutputStreamWriter(fos, "UTF-8");
		    BufferedWriter bw = new BufferedWriter(osr);
		    bw.append("Drug\tDrug_synonyms\tDrug_ID");
			bw.append("\n");
			for(String eachOutput : allOutput) {
		    	bw.append(eachOutput);
		    	bw.append("\n");
		    }
		       
			br0.close();
			br1.close();
			bw.close();
		} catch(IOException e) {
			System.err.println(e);
		}

		long stopTime = System.currentTimeMillis();
		long elapsedTime = stopTime - startTime;
		System.out.println("Execution time in milliseconds: " + elapsedTime);
	}
	
	/**
	 * Method retrieves drug ID and a list of drug synonyms from drugs/biologics lexicon
	 * 
	 * @param drug
	 * @param drugsAndID
	 * @param drugSynonymsAndID
	 * @return
	 */
	
	public static Pair getDrugInfo(String drug,
					HashMap<String, String> drugsAndID, 
					ArrayList<String> drugSynonymsAndID) {
		String output="", drugSynonyms="";
		ArrayList<String> allSynonyms = new ArrayList<String>(); 
		
		//get drugID
    	String drugID =  drugsAndID.get(drug.trim().toLowerCase());
		if(drugID == null) {
			output = drug + "\t-\t-";
		}
		else {
    		if(!drugIDs.contains(drugID)) {
    			//get drug synonyms
    			for(String each : drugSynonymsAndID) {
    				if(!each.endsWith(drugID)) continue;
				
    				String[] arrLine = each.split("\t");
					if(arrLine[0].equals(drug)) continue;
					if(allSynonyms.contains(arrLine[0])) continue;
					allSynonyms.add(arrLine[0]);
    			}

				if(!allSynonyms.isEmpty()) {
					for(String eachSynonym : allSynonyms) {
						if(drugSynonyms.isEmpty()) drugSynonyms = eachSynonym;
                                		else drugSynonyms = drugSynonyms + "|" + eachSynonym;
					}
				}
    	
    			//get output
				output = drug + "\t" + drugSynonyms + "\t" + drugID;
    			drugIDs.add(drugID);
    		}
		}
    	
    	//return values
    	Pair pair = new Pair(output, drugIDs);
    	return pair;
	}
	
}

/**
 * Program encapsulates and returns multiple values
 * 
 * @author Kalpana Raja
 *
 */

class Pair{
	
	public String output;
	public ArrayList<String> drugIDs;
	
	//default constructor
	public Pair() {}
	
	//constructor to initialize variables
	public Pair(String output, ArrayList<String> drugIDs) {
		this.output = output;
		this.drugIDs = drugIDs;
	}
	
	//method to return value 1
	public String getOutput() {
		return output;
	}
	
	//method to return value 2
	public ArrayList<String> getdrugIDs() {
		return drugIDs;
	}
	
}

