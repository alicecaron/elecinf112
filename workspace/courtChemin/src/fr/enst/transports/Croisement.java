package fr.enst.transports;

import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class Croisement {
	
	
	public static void main(String[] args){
		
		if(args.length!=1){
			System.err.println("Need a filename");
		}
		
		//Get file and buffer
		try{
			Reader reader = new FileReader(args[0]);
			BufferedReader buffer = new BufferedReader(reader);
			//put each train line in a final list of lines
			final List<List<String>> trainLines = CommonFonctions.makeTrainLines(buffer);
			//compare each trainLine to get double key points (create a map)
			//search key points in the map
			CommonFonctions.printKeyPoints(CommonFonctions.createMap(trainLines));
			
		}catch(IOException e){
			System.err.println("Unable to get file \""+args[0]+"\"");
			System.exit(0);
		}
	}
	
	
}
