package md.utm.fi131;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import com.google.gson.Gson;

public class FileReader {
	//citim din fisier si serializam in obiecte
	public  ArrayList<Worker>  getObjFromFile(String nameFile) throws FileNotFoundException {

		Scanner sk = new Scanner(new File(nameFile));

		ArrayList<Worker> listWorkers = new ArrayList<Worker>();
		while (sk.hasNextLine()) {

			String json = sk.nextLine();
		
			Gson gson = new Gson();
			Worker obj = gson.fromJson(json, Worker.class);
			System.out.println(json);
			listWorkers.add(obj);
		}sk.close();
	
		return listWorkers;
	}
	
}
