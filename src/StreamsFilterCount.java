import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class StreamsFilterCount {
	public static void main(String[] args) {
		List<Student> listStudents = getStudentList();
		List<DateIdCheck> dateIdChecks = getDateIdCheckList();
		listStudents.stream().forEach(System.out::println);
		dateIdChecks.stream().forEach(System.out::println);
		Instant start = Instant.now();
		Long count = listStudents.stream()
				.filter(student -> dateIdChecks.stream()
						.anyMatch(dateIdCheck -> student.getAdmissionDate().equals(dateIdCheck.getFlightDate())
								&& student.getId().equals(dateIdCheck.getId())))
				.count();
		List<Student> finalListStudents= listStudents.stream()
				.filter(student -> dateIdChecks.stream()
						.anyMatch(dateIdCheck -> student.getAdmissionDate().equals(dateIdCheck.getFlightDate())
								&& student.getId().equals(dateIdCheck.getId())))
				.toList();
		finalListStudents.stream().forEach(System.out::println);
		
		// your code
		Instant end = Instant.now();
		Duration timeElapsed = Duration.between(start, end);
		System.out.println("Time taken: " + timeElapsed.toMillis() + " milliseconds");
		System.out.println(count);
	}

	public static List<Student> getStudentList() {
		List<Student> students = new ArrayList<>(10);
		for (int i = 0; i < 10; i++) {
			String stName = "student" + i;
			LocalDate adDate = LocalDate.now().plusDays(i);
			String stId = "" + i;
			Student student = new Student(stName, adDate, stId);
			students.add(student);
		}
		return students;

	}

	public static List<DateIdCheck> getDateIdCheckList() {
		List<DateIdCheck> dateIdChecks = new ArrayList<>(5);
		for (int i = 0; i < 10; i++) {
			LocalDate adDate = LocalDate.now().plusDays(i);
			String stId = "" + (2*i);
			DateIdCheck dateIdCheck = new DateIdCheck(adDate, stId);
			dateIdChecks.add(dateIdCheck);
		}
		return dateIdChecks;

	}

}

class Student {
	String name;
	LocalDate admissionDate;
	String id;

	public Student(String name, LocalDate admissionDate, String id) {
		super();
		this.name = name;
		this.admissionDate = admissionDate;
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getAdmissionDate() {
		return admissionDate;
	}

	public void setAdmissionDate(LocalDate admissionDate) {
		this.admissionDate = admissionDate;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Student [name=" + name + ", admissionDate=" + admissionDate + ", id=" + id + "]";
	}

}

class DateIdCheck {
	LocalDate flightDate;
	String id;

	public LocalDate getFlightDate() {
		return flightDate;
	}

	public void setFlightDate(LocalDate flightDate) {
		this.flightDate = flightDate;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public DateIdCheck(LocalDate flightDate, String id) {
		super();
		this.flightDate = flightDate;
		this.id = id;
	}

	@Override
	public String toString() {
		return "DateIdCheck [flightDate=" + flightDate + ", id=" + id + "]";
	}

}
