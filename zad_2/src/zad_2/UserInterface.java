package zad_2;

import java.util.List;
import java.util.Scanner;

public class UserInterface {

	private static Scanner scan;
	private final static String defaultSaveFilePath = "C:/users/tmazurek/desktop/grupy/uczestnicy_";

	public static void main(String[] args) {
		boolean finished = false;
		String input;
		scan = new Scanner(System.in);
		List<Person> participians;
		LoadSavePeople lsp;

		lsp = new LoadSavePeople(defaultSaveFilePath);
		participians = lsp.loadData();

		while (!finished) {

			try {
				System.out.println("Aby wybrac osoby o nazwiskach na dana litere, wpisz ja i potwierdz enterem");
				System.out.println("Aby podzielic ludzi na grupy o danej wielkosci, wpisz liczbe i potwierdz enterem");
				input = scan.nextLine();

				Checker check = new Checker(input, participians, participians.size());
				if (check.processPeople()) {
					System.out.println("Pomyslnie zapisano do pliku. Sciezka " + defaultSaveFilePath);
					finished = true;
				}
			}

			catch (RuntimeException e) {
				System.out.println(e.getMessage());
				finished = false;
			}
		}
	}

}
