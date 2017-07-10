package zad_2;

import java.time.LocalDate;

public interface Person {
	
	
	String getLastName();
	String getFirstName();
	LocalDate getBirthDate();
	String toString();
	int countAge();

}