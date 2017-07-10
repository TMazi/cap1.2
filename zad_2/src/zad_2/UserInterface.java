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

		System.out.println(
				"Podaj sciezke do zapisu plikow lub nacisnij enter, aby uzyc domyslnej: " + defaultSaveFilePath);
		String path = scan.nextLine();

		try {

			if (path.length() < 1)
				lsp = new LoadSavePeople(defaultSaveFilePath);
			else
				lsp = new LoadSavePeople(path);
			participians = lsp.loadData();

			while (!finished) {
				System.out.println("Aby wybrac osoby o nazwiskach na dana litere, wpisz ja i potwierdz enterem");
				System.out.println("Aby podzielic ludzi na grupy o danej wielkosci, wpisz liczbe i potwierdz enterem");
				input = scan.nextLine();

				Checker check = new Checker(input, participians, participians.size());
				if (check.processPeople()) {
					System.out.println("Pomyslnie zapisano do pliku");
					finished = true;
				}
			}
		}

		catch (RuntimeException e) {
			System.out.println(e.getMessage());
			finished = false;
		}
	}

}
