package zad_2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import zad_2.Person;

public class DataTransformer {

	Object input;
	Comparator<Person> comparator;
	ArrayList<Person> people;

	public DataTransformer(Object input, List<Person> people) {

		this.input = input;
		this.people = (ArrayList<Person>) people;
		if (input instanceof Integer)
			comparator = new FirstNameComparator();
		else if(input instanceof Character)
			comparator = new LastNameComparator();

	}

	public boolean transformAndSave() throws RuntimeException {
		boolean result = false;
		LoadSavePeople lsp = new LoadSavePeople();
		ArrayList<Person> temp = new ArrayList<>();
		if( input instanceof Integer) {
			int count = (Integer) input;
			sort();
			for (int i = 0; i < people.size()/count; i++) {
				for (int j = 0; j < count; j++) {
					temp.add(people.get(i*count+j));
				}
				lsp.saveData(temp, i+1);
				temp.clear();
			}
			result = true;
		}
		else if (input instanceof Character) {
			peopleLastNameStartWith((char) input);
			if(people.size() < 1 )
				throw new RuntimeException("Nie ma ludzi o nazwisku na taka litere!");
			sort();
			lsp.saveData(people, input);
			result = true;
		}
		return result;
	}

	public List<Person> sort() {
		sortItQuick(people, 0, people.size() - 1, comparator);
		return people;
	}

	private void sortItQuick(List<Person> nonSorted, int start, int end, Comparator<Person> comparator) {
		int left = start;
		int right = end;
		int pivot = (start + end) / 2;
		Person pivotValue = nonSorted.get(pivot);
		while (left <= right) {
			while (left < end && comparator.compare(pivotValue, nonSorted.get(left)) > 0)
				left++;
			while (right > start && comparator.compare(pivotValue, nonSorted.get(right)) < 0)
				right--;
			if (left <= right) {
				Collections.swap(nonSorted, left, right);
				left++;
				right--;
			}
		}
		if (start < right)
			sortItQuick(nonSorted, start, right, comparator);
		if (left < end)
			sortItQuick(nonSorted, left, end, comparator);
	}
	
	public void peopleLastNameStartWith(char letter) {
		if (Character.isLowerCase(letter))
			letter = Character.toUpperCase(letter);
		ArrayList<Person> result = new ArrayList<Person>();
		for (Person p : people) {
			if (p.getLastName().charAt(0) == letter) {
				result.add(p);
			}
		}
		people = result;
	}
	
}
