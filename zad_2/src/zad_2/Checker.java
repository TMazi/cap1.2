package zad_2;

import java.util.List;

public class Checker {

	DataTransformer dt;

	public Checker(String input, List<Person> people, int size) throws WrongInputException {

		if (input.matches("\\d+")) {
			int count = Integer.parseInt(input);
			if (count == 0)
				throw new WrongInputException("0 nie jest prawidlowa liczba!");
			if ((size % count) != 0)
				throw new WrongInputException(count + " nie jest dzielnikem liczby uczestnikow (" + size + ")");
			dt = new DataTransformer(Integer.parseInt(input), people);
		} 
		else if (input.matches("^[a-zA-Z]$")) {
			dt = new DataTransformer(input.charAt(0), people);
		}
		else
			throw new WrongInputException("Nieprawidlowe dane wejsciowe!");
	}

	public void processPeople() {
		dt.transformAndSave();
	}

}
