package gui;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class Levels {
	private static final String PATH_LEVELS="./level/";
	private HashMap<String,File> levels ;
	private ArrayList<String> levelsName; 
	
	public Levels() {
		levels = new HashMap<>();
		levelsName = new ArrayList<>();
		File directory = new File(PATH_LEVELS);
		String[] files=directory.list();
		
		if(files!=null){
			for (String string : files) {
				System.out.println(string);
				levels.put(string,new File(PATH_LEVELS+string));
				levelsName.add(string);
			}
		}
	}

	public ArrayList<String> getLevelsName(){
		return levelsName;
	}
	
	public File getLevel(String string) {
		System.out.println("GET LEVEL "+string);
		return levels.get(string);
	}
	
	public String getLevel(int i){
		return levelsName.get(i);
	}
	
}
