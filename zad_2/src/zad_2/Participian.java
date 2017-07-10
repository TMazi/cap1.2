package zad_2;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class Participian implements Person {

	private String firstName, lastName;
	private LocalDate birthDate;

	public Participian(String firstName, String lastName, String birthDate) {
		this.firstName = firstName;
		this.lastName = lastName;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		this.birthDate = LocalDate.parse(birthDate, formatter);
	}

	@Override
	public int countAge() {
		LocalDate today = LocalDate.now();
		return Period.between(birthDate, today).getYears();
	}

	@Override
	public String getLastName() {
		return lastName;
	}

	@Override
	public String getFirstName() {
		return firstName;
	}

	@Override
	public LocalDate getBirthDate() {
		return birthDate;
	}

	@Override
	public String toString() {
		return firstName + "," + lastName + "," + countAge();
	}

}
