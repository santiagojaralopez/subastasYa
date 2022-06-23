package co.edu.cue.subastasYa;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

@SpringBootTest
class CrudApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void tryourAge(){

		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate fechaNac = LocalDate.parse("23/07/2004", fmt);
		LocalDate ahora = LocalDate.now();

		Period periodo = Period.between(fechaNac, ahora);
		System.out.printf("Tu edad es: %s años, %s meses y %s días",
				periodo.getYears(),periodo.getMonths(),periodo.getDays());

	}
	@Test
	void tryDates(){
		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate fechaNac = LocalDate.parse("23/07/2004", fmt);
		System.out.println(fechaNac);
	}



}
