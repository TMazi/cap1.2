package zad_2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import zad_2.Participian;
import zad_2.Person;

public class LoadSavePeople {

	private final String defaultLoadFilePath = "C:/users/tmazurek/desktop/konferencja.csv";
	private final String defaultSaveFilePath = "C:/users/tmazurek/desktop/grupy/uczestnicy_";
	private String loadFilePath;

	public LoadSavePeople() {
		loadFilePath = defaultLoadFilePath;
	}

	public LoadSavePeople(String loadFilePath) {
		this.loadFilePath = loadFilePath;
	}

	public List<Person> loadData() {

		List<Person> participians = new ArrayList<Person>();

		try {
			BufferedReader br = new BufferedReader(new FileReader(loadFilePath));
			String nLine;
			String[] personData;
			while ((nLine = br.readLine()) != null) {
				personData = nLine.split(",");
				Person person = new Participian(personData[0], personData[1], personData[2]);
				participians.add(person);
			}
			br.close();

		} catch (FileNotFoundException e) {
			System.out.println("Blad! Nie ma takiego pliku");

		} catch (IOException e) {
			System.out.println("Blad przy wczytywaniu pliku!");
		}

		return participians;
	}


	public void saveData(ArrayList<Person> people, Object postfix) {

		save(people, defaultSaveFilePath + postfix.toString() + ".csv");
		display(people);
	}

	private void save(ArrayList<Person> people, String path) {
		try {
			File destination = new File(path);
			FileWriter fw = new FileWriter(destination);
			BufferedWriter bf = new BufferedWriter(fw);
			for (Person p : people) {
				bf.write(p.toString());
				bf.newLine();
			}
			bf.close();
		} catch (IOException e) {
			System.out.println("Nie udalo sie zapisac do pliku!");
			e.printStackTrace();
		}
	}
	
	private void display(ArrayList<Person> people) {
		for (Person p : people) {
			System.out.println(p.toString());
		}
	}

}
