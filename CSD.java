package PE2;


import java.util.*;

/* PLEASE DO NOT MODIFY A SINGLE STATEMENT IN THE TEXT BELOW.
READ THE FOLLOWING CAREFULLY AND FILL IN THE GAPS

I hereby declare that all the work that was required to 
solve the following problem including designing the algorithms
and writing the code below, is solely my own and that I received
no help in creating this solution and I have not discussed my solution 
with anybody. I affirm that I have read and understood
the Senate Policy on Academic honesty at 
https://secretariat-policies.info.yorku.ca/policies/academic-honesty-senate-policy-on/
and I am well aware of the seriousness of the matter and the penalties that I will face as a 
result of committing plagiarism in this assignment.

BY FILLING THE GAPS,YOU ARE SIGNING THE ABOVE STATEMENTS.

Full Name: Andrew Tissi
Student Number: 218724179
Course Section: E
*/

/**
 * The Computer Science Department contains various functions which can be done in relation to the students/ employees of the CSD.
 * 
 */
public class CSD {
	
	static int EmployeeID = 100; //employee IDs start at 100
	static int StudentID = 1000; //Student IDs start at 1000
	private ChairPerson ChairPerson; //the chairperson of the CSD
	List<Faculty> Faculty = new ArrayList<Faculty>(); //the list of faculty in the CSD
	List<UGrad> UndergradStudents = new ArrayList<UGrad>(); //the list of the undergrad students in the CSD
	List<Grad> GradStudents = new ArrayList<Grad>(); //the list of the grad students in the CSD
	List<ProgramDirector> ProgramDirectors = new ArrayList<ProgramDirector>(); // the list of the program directors in the CSD
	
	/**
	 * This method creates the CSD with the chairperson
	 * @param chair is the chairperson of the CSD
	 */
	public CSD(ChairPerson chair) {
		this.ChairPerson = chair; //sets the chairperson to the given one
	}
	
	/**
	 * This method Hires faculty and throws an error if there is no space for them
	 * @param f is the faculty member which is going to be hired
	 * @throws NoSpaceException when there is no space for this faculty member
	 */
	public void HireFaculty(Faculty f) throws NoSpaceException { 
		if (this.Faculty.size()>=70) throw new NoSpaceException("too many faculty");
		if (this.Faculty.contains(f)) return; //duplicate faculty hired
		this.Faculty.add(f); //adds the faculty to the list of faculty in CSD
		if (this.ProgramDirectors.size()==0) return; //Faculty hired without a program director
		boolean assigned = false;//stores a variable to keep track of if the faculty is assigned to a program director or not
		for (int i=0;i<this.ProgramDirectors.size();i++) {//iterates through all program directors
			ProgramDirector director = this.ProgramDirectors.get(i); //gets the current director
			if (director.getProgram() == f.getProgramString()) { //checks if the director oversees the faculty's program
				if (director.FacultyAssigned.size()>=25) throw new NoSpaceException("maximum amount of faculty assigned to this program director"); //throws a NoSpaceException error when the program director's faculty list is full and no more faculty can be hired 
				f.setProgramDirector(director);//sets the faculty's program director to the director
				director.FacultyAssigned.add(f); //assigns the faculty into the director's list of faculty assigned
				assigned = true; //the faculty gets assigned to this program director
			}
		}
		if (!assigned) return; //a program director exists, however not the program director which covers the faculty's specialization and therefore the faculty is not assigned to any program director
		
	}
	
	/**
	 * This method returns the chairperson of the CSD
	 * @return returns the chairperson object
	 */
	public Object getChairPerson() {
		return this.ChairPerson; //returns the chairperson
	}
	
	/**
	 * This method returns the Faculty of the CSD in a list
	 * @return returns the faculty list
	 */
	public List<Faculty> getFaculty() {
		return this.Faculty; //returns the faculty list
	}
	
	/**
	 * This method returns the amount of Faculty in the CSD
	 * @return returns the amount of faculty
	 */
	public Integer getNumOfFaculty() {
		return this.Faculty.size(); //returns the amount of faculty
	}
	
	/**
	 * This method admits a student and throws an error if there is no space for them
	 * @param s is the student which is going to be admitted
	 * @throws NoSpaceException when there is no space for this student
	 */
	public void AdmitStudent(UGrad s) throws NoSpaceException {
		if (this.UndergradStudents.size()>=500) throw new NoSpaceException("No space for this student");//no more room for students
		if (this.UndergradStudents.contains(s)) return; // duplicate student
		
		boolean assigned = false;//stores a variable to keep track of if the student is assigned to a faculty or not
		int facultyNumber = 0; //stores a variable to keep track of which faculty we are looking at
		
		while (!assigned) { //loops through until a faculty is found which we can assign this student to
			if (facultyNumber ==this.Faculty.size()) throw new NoSpaceException("no available faculty for this student"); //throws an error when there is no faculty available for this student
			Faculty f = this.Faculty.get(facultyNumber); //gets the current faculty and stores it
			
			if (f.StudentsAssigned.size()>=8) assigned = false; //checks if this current faculty is full of students, and if it is, it will skip to the next faculty
			else { //current faculty is not full of students
				f.StudentsAssigned.add(s);//adds the current student to the faculty's list
				s.setAdvisor(f); //sets the student's advisor to the faculty
				assigned = true; //student is assigned and so the loop will be exited
			}
			facultyNumber+=1;//move to the next faculty
			
		}
		this.UndergradStudents.add(s);//adds the student to the list of students in CSD
	}

	/**
	 * This method returns the amount of undergrad students in the CSD
	 * @return returns the amount of undergrad students
	 */
	public Integer getNumOfUGradStudents() {
		return this.UndergradStudents.size(); //returns the amount of undergrad students
	}

	/**
	 * This method hires a TA and throws an error if there is no space for them
	 * @param s is the TA which is going to be hired
	 * @throws NoSpaceException when there is no space for this TA 
	 */
	public void HireTA(Grad s) throws NoSpaceException{
		if (this.GradStudents.size()>=150) throw new NoSpaceException("too many grad students");//no more room this TA
		if (this.GradStudents.contains(s)) return; //duplicate TA
		
		boolean assigned = false;//stores a variable to keep track of if the TA is assigned to a faculty or not
		int facultyNumber = 0;//stores a variable to keep track of which faculty we are looking at
		
		while (!assigned) {//loops through until a faculty is found which we can assign this TA to
			if (facultyNumber==this.Faculty.size()) throw new NoSpaceException("no avilable faculty for this TA");//throws an error when there is no faculty available for this TA
			Faculty f = this.Faculty.get(facultyNumber);//gets the current faculty and stores it
			
			if (f.TAsAssigned.size()>=5) assigned = false; //checks if this current faculty is full of TAs, and if it is, it will skip to the next faculty
			else {//current faculty is not full of TAs
				f.TAsAssigned.add(s);//adds the current TA to the faculty's list
				s.setAdvisor(f);//sets the TA's advisor to the faculty
				assigned = true; //TA is assigned and so the loop will be exited
			}
			facultyNumber+=1; //move to the next faculty
			
		}
		this.GradStudents.add(s);//adds the TA to the list of TAs in CSD	
	}
	
	/**
	 * This method returns the amount of grad students in the CSD
	 * @return returns the amount of grad students
	 */
	public Integer getNumOfGradStudents() {
		return this.GradStudents.size();//returns the amount of grad students
	}

	/**
	 * This method makes the given undergrad student (s) graduate and removes them from the record
	 * @param s is the graduating undergrad student
	 */
	public void AlumnusUGrad(UGrad s) {
		this.UndergradStudents.remove(s); //removes the undergrad student from the undergrad student list in CSD
		s.getAdvisorFaculty().StudentsAssigned.remove(s);//removes the undergrad student from their advisor's list
	}

	/**
	 * This method makes the given undergrad student (s) graduate and removes them from the record
	 * @param s is the graduating grad student
	 * @throws NoTAException when this TA is the last TA in the faculty and removing them would result in no TA for the faculty
	 */
	public void AlumnusGrad(Grad s) throws NoTAException{
		this.GradStudents.remove(s);//removes the grad student from the grad student list in CSD
		s.getAdvisorFaculty().TAsAssigned.remove(s);//removes the grad student from their advisor's list
		if (s.getAdvisorFaculty().TAsAssigned.size() == 0) throw new NoTAException("removing this TA results in no TA for this faculty"); //throws an error when this TA is the last TA for this faculty
	}

	/**
	 * This method sorts the list of grads students from lowest to highest student ID
	 * @return a sorted list of grad students from lowest to highest student ID
	 */
	public Object ExtractAllGradDetails() {
		Collections.sort(this.GradStudents, (a, b) -> { //sorts the gradstudents list
			return b.getStudentID() - a.getStudentID(); //low student ID to high student ID
		});
		
		return this.GradStudents;//returns the sorted gradstudents list
	}
	
	/**
	 * This method sorts the list of undergrad students in alphabetical order
	 * @return a sorted list of undergrad students in alphabetical order
	 */
	public Object ExtractAllUGradDetails() {
		Collections.sort(this.UndergradStudents, (a, b) -> { //sorts the undergradstudents list
			return (a.getFirstName()+", "+a.getLastName()).compareTo(b.getFirstName()+", "+b.getLastName()); //sorts in alphabetical order
		});
		
		return this.UndergradStudents;//returns the sorted undergradstudents list
	}
	
	/**
	 * This method sorts the list of faculty in alphabetical order
	 * @return a sorted list of faculty in alphabetical order
	 */
	public Object ExtractAllFacultyDetails() {
		Collections.sort(this.Faculty, (a, b) -> { //sorts the faculty list
			return (a.getFirstName()+", "+a.getLastName()).compareTo(b.getFirstName()+", "+b.getLastName());//sorts in alphabetical order
		});
		
		return this.Faculty;//returns the sorted faculty list
	}

	/**
	 * This method sorts the list of faculty specific to the given program in alphabetical order
	 * @param program which is the given program which we will collect the faculty from and then sort them
	 * @return a sorted list of faculty specific to the given program in alphabetical order
	 */
	public Object ExtractFacultyDetails(String program) {
		List<Faculty> SpecificFaculty = new ArrayList<Faculty>();//creates a new list where we will store the faculty in 
		for (int i=0;i<this.Faculty.size();i++) {//loops through all faculty
			if (this.Faculty.get(i).getProgramString() == program) SpecificFaculty.add(this.Faculty.get(i)); //adds the faculty to the specificfaculty list if the current faculty has the specified program
		}
		
		Collections.sort(SpecificFaculty, (a, b) -> {//sorts the specific list
			return (a.getFirstName()+", "+a.getLastName()).compareTo(b.getFirstName()+", "+b.getLastName());//sorts in alphabetical order
		});
		
		return SpecificFaculty;//returns the sorted specific faculty list
	}

	/**
	 * This method sorts the list of students assigned to the given faculty in alphabetical order
	 * @param f which is the faculty which we will be getting students from
	 * @return a sorted list of students in alphabetical order
	 */
	public Object ExtractAdviseesDetails(Faculty f) {
		Collections.sort(f.StudentsAssigned, (a, b) -> {//sorts the students assigned to the faculty
			return (a.getFirstName()+", "+a.getLastName()).compareTo(b.getFirstName()+", "+b.getLastName());//sorts in alphabetical order
		});
		
		return f.StudentsAssigned;//returns the sorted list
	}
	
	/**
	 * This method sorts the list of TAs assigned to the given faculty in alphabetical order
	 * @param f which is the faculty which we will be getting TAs from
	 * @return a sorted list of TAs in alphabetical order
	 */
	public Object ExtractTAsDetails(Faculty f) {
		Collections.sort(f.TAsAssigned, (a, b) -> {//sorts the TAs assigned to the faculty
			return (a.getFirstName()+", "+a.getLastName()).compareTo(b.getFirstName()+", "+b.getLastName());//sorts in alphabetical order
		});
		return f.TAsAssigned;//returns the sorted list
	}

	/**
	 * This method adds a program director and throws an error if there is no space for them
	 * @param p is the program director which is going to be added
	 * @throws NoSpaceException when there is no space for this program director
	 */
	public void addProgramDirector(ProgramDirector p) throws NoSpaceException {
		if (this.ProgramDirectors.size()>=3) throw new NoSpaceException("too many program directors"); //max amount of program directors already added
		if (this.ProgramDirectors.contains(p)) return; //duplicate program director
		
		if (this.ProgramDirectors.size()==0) { //currently no program directors so the first program director to be added will always be added
			this.ProgramDirectors.add(p);//adds the program director
			return;//exits the function
		}
		
		for (int i=0;i<this.ProgramDirectors.size();i++) {//loops through all program directors
			if (p.getProgram() == this.ProgramDirectors.get(i).getProgram()) return; //program director which we are trying to add has the same program as an already existing program director
		}
		
		this.ProgramDirectors.add(p);//adds new program director if no other complications arise
	}

	/**
	 * This method retires a faculty member and removes them from the record of the CSD
	 * @param f is the faculty which is retiring
	 * @throws NoSpecialtyException when there is no other faculty member with the same specialty as the retiring faculty
	 * @throws NoSpaceException when there is no available faculty to reassign the TA's or the Students to
	 */
	public void RetireFaculty(Faculty f) throws NoSpecialtyException, NoSpaceException{ 
		int SameSpecialty = 0; //keeps track of how many faculty share the same program
		for (int i=0; i<this.Faculty.size();i++) {//loops through all faculty to count how many faculty have the same program
			if (this.Faculty.get(i).getProgramString() == f.getProgramString()) SameSpecialty +=1; //increments this variable whenever there is a faculty of the same program
		}
		
		if (SameSpecialty<=1) throw new NoSpecialtyException("There are no other faculty members with the specialty of the retiring faculty memeber");//throws a noSpecialty exception when there are no other faculty members with the same specailty as the retiring faculty member
		
		for (int i=0;i<f.StudentsAssigned.size();i++) {//loops through all the students assigned to the given faculty
			UGrad CurrentStudent = f.StudentsAssigned.get(i);//gets the current student
			boolean assigned = false;//stores a variable to keep track of if the Student is assigned to a faculty or not
			int facultyNumber = 0;//stores a variable to keep track of which faculty we are looking at
			
			while (!assigned) {//loops through until a faculty is found which we can assign this Student to
				if (facultyNumber== Faculty.size()) throw new NoSpaceException("no avilable faculty for this student"); //throws an error when there is no faculty available for this student
				Faculty faculty = Faculty.get(facultyNumber);//gets the current faculty and stores it
				if (faculty.StudentsAssigned.size()>=8 || faculty.getEmployeeID() == f.getEmployeeID()) assigned = false; //checks if this current faculty is full of Students or if this is the faculty which we are retiring, and if it is, it will skip to the next faculty
				else {//current faculty is not full of students, or this is the faculty which we will be retiring
					faculty.StudentsAssigned.add(CurrentStudent);//adds the student to the new faculty's list
					CurrentStudent.setAdvisor(faculty);//sets the student's new advisor to this faculty
					assigned = true;//student is assigned and so the loop will be exited
				}
				facultyNumber+=1; //move to the next faculty
			}
		}
		
		for (int i=0;i<f.TAsAssigned.size();i++) {//loops through all the TAs assigned to the given faculty
			Grad CurrentTA = f.TAsAssigned.get(i);//gets the current TA
			boolean assigned = false;//stores a variable to keep track of if the TA is assigned to a faculty or not
			int facultyNumber = 0;//stores a variable to keep track of which faculty we are looking at
			
			while (!assigned) {//loops through until a faculty is found which we can assign this TA to
				if (facultyNumber== Faculty.size()) throw new NoSpaceException("no avilable faculty for this TA");//throws an error when there is no faculty available for this TA
				Faculty faculty = Faculty.get(facultyNumber);//gets the current faculty and stores it
				if (faculty.TAsAssigned.size()>=5 || faculty.getEmployeeID() == f.getEmployeeID()) assigned = false; //checks if this current faculty is full of TAs or if this is the faculty which we are retiring, and if it is, it will skip to the next faculty
				else {//current faculty is not full of students, or this is the faculty which we will be retiring
					faculty.TAsAssigned.add(CurrentTA);//adds the TA to the new faculty's list
					CurrentTA.setAdvisor(faculty);//sets the TAs new advisor to this faculty
					assigned = true;//TA is assigned and so the loop will be exited
				}
				facultyNumber+=1;//move to the next faculty
				
			}
		}
		Faculty.remove(f);//removes the faculty from CSD
		f.getProgramDirector().FacultyAssigned.remove(f);//removes the faculty from their program director's list
	}
}

/**
 * This class encompasses all people in the CSD
 * 
 */
class Person{
	private String FirstName; //person's first name
	private String LastName; //person's last name
	private int Age; //person's age
	private String Gender; //person's gender
	private String Address; //person's address
	
	@Override
	/**
	 * This method overrides the java toString method and returns a string representation of the person
	 * @returns	a string depicting the person
	 */
	public String toString() {
		return "["+getFirstName()+", "+getLastName()+", "+getAge()+", "+getGender()+", "+getAddress()+"]";
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return this.FirstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.FirstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return this.LastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.LastName = lastName;
	}

	/**
	 * @return the age
	 */
	public int getAge() {
		return this.Age;
	}

	/**
	 * @param age the age to set
	 */
	public void setAge(int age) {
		this.Age = age;
	}

	/**
	 * @return the gender
	 */
	public String getGender() {
		return this.Gender;
	}

	/**
	 * @param gender the gender to set
	 */
	public void setGender(String gender) {
		this.Gender = gender;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return this.Address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.Address = address;
	}
}

/**
 * This class extends the person, all the people in this class have a salary as well as an employee ID
 * 
 */
class Academics extends Person{
	private int EmployeeID; //employee's ID
	private double Salary; //salary

	/**
	 * @param d the salary to set
	 */
	public void setSalary(double d) {
		this.Salary = d;
	}
	
	@Override
	/**
	 * This method overrides the java toString method and returns a string representation of the person
	 * @returns	a string depicting the person
	 */
	public String toString() {
		return "["+getEmployeeID()+", "+getSalary()+super.toString()+"]";
	}

	/**
	 * @return the employeeID
	 */
	public int getEmployeeID() {
		return this.EmployeeID;
	}

	/**
	 * @param employeeID the employeeID to set
	 */
	public void setEmployeeID(int employeeID) {
		this.EmployeeID = employeeID;
	}

	/**
	 * @return the salary
	 */
	public double getSalary() {
		return this.Salary;
	}	
}
/**
 * This class extends academics and encompasses all the administrators of the CSD
 * 
 */
class Administrator extends Academics{
	@Override
	/**
	 * This method overrides the java toString method and returns a string representation of the person
	 * @returns	a string depicting the person
	 */
	public String toString() {
		return "["+super.toString()+"]";
	}
}

/**
 * This class extends administrator and encompasses the program directors
 * the program directors have a program as well as a list of faculty which they are assigned to
 * 
 */
class ProgramDirector extends Administrator{
	private String program;//program director's program
	protected List<Faculty> FacultyAssigned = new ArrayList<Faculty>();//the list of the faculty assigned to the program director
	
	/**
	 * This method sets up the program director with all the appropriate attributes
	 * @param FirstName the first name of the program director
	 * @param LastName the last name of the program director
	 * @param Age the age of the program director
	 * @param Gender the gender of the program director
	 * @param Address the address of the program director
	 */
	public ProgramDirector(String FirstName, String LastName, int Age, String Gender, String Address) {
		//set the instance variables to the given parameters
		this.setFirstName(FirstName);
		this.setLastName(LastName);
		this.setAge(Age);
		this.setGender(Gender);
		this.setAddress(Address);
		this.setEmployeeID(CSD.EmployeeID);
		CSD.EmployeeID+=1; //increments CSD's EmployeeID
	}

	/**
	 * @param program the program to set
	 */
	public void setProgram(String program) {
		this.program = program;
	}
	
	
	@Override
	/**
	 * This method overrides the java toString method and returns a string representation of the person
	 * @returns	a string depicting the person
	 */
	public String toString() {
		return "Program Director ["+super.toString()+"]";
	}

	/**
	 * This method compares this program director to a given program director
	 * @param programDirector the program director to which we are comparing this program director to
	 * @returns 0 if the program directors are identical, and 1 if they are different
	 */
	public int compareTo(ProgramDirector programDirector) {
		if (this.getAddress().compareTo(programDirector.getAddress()) == 0
				&& this.getAge() == programDirector.getAge()
				&& this.getEmployeeID() == programDirector.getEmployeeID()
				&& this.getFirstName().compareTo(programDirector.getFirstName()) == 0
				&& this.getGender().compareTo(programDirector.getGender()) == 0
				&& this.getLastName().compareTo(programDirector.getLastName()) == 0
				&& this.getProgram().compareTo(programDirector.getProgram()) == 0
				&& this.getSalary() == programDirector.getSalary()
				&& this.FacultyAssigned.equals(programDirector.FacultyAssigned)) return 0; //program directors are the same
		return 1; //program directors are different
	}

	/**
	 * @return the program
	 */
	public String getProgram() {
		return this.program;
	}
}

/**
 * This class extends the administrator class and encompasses the chairperson 
 * 
 */
class ChairPerson extends Administrator{
	
	/**
	 * This method sets up the chairperson with all the appropriate attributes
	 * @param FirstName the first name of the chairperson
	 * @param LastName the last name of the chairperson
	 * @param Age the age of the chairperson
	 * @param Gender the gender of the chairperson
	 * @param Address the address of the chairperson
	 */
	public ChairPerson(String FirstName, String LastName, int Age, String Gender, String Address) {
		//set the instance variables to the given parameters
		this.setFirstName(FirstName);
		this.setLastName(LastName);
		this.setAge(Age);
		this.setGender(Gender);
		this.setAddress(Address);
		this.setEmployeeID(CSD.EmployeeID);
		CSD.EmployeeID+=1; //increments CSD's EmployeeID
	}
	
	/**
	 * This method sets up the chairperson with all the appropriate attributes when given a ChairPerson object
	 * @param person the chairperson
	 */
	public ChairPerson(ChairPerson person) {
		//set the instance variables to the given chairperson's instance variables
		this.setFirstName(person.getFirstName());
		this.setLastName(person.getLastName());
		this.setAge(person.getAge());
		this.setGender(person.getGender());
		this.setAddress(person.getAddress());
		this.setEmployeeID(person.getEmployeeID());
	}
	
	@Override
	/**
	 * This method overrides the java toString method and returns a string representation of the person
	 * @returns	a string depicting the person
	 */
	public String toString() {
		return "Chair Person ["+super.toString()+"]";
	}
}
/**
 * This class extends academics and encompasses the faculty
 * all faculty have a program, a program director, a list of students assigned to them and a list of TA's assigned to them
 * This class also implements the comparable interface and is therefore comparable
 * 
 */
class Faculty extends Academics implements Comparable<Faculty>{
	private ProgramDirector ProgramDirector; //the program director which this faculty is assigned to
	private String program; //the program of this faculty
	protected List<UGrad> StudentsAssigned = new ArrayList<UGrad>(); //the list of students which this faculty is responsible for 
	protected List<Grad> TAsAssigned = new ArrayList<Grad>(); //the list of TA which this faculty is responsible for 
	
	/**
	 * This method sets up the faculty with all the appropriate attributes
	 * @param FirstName the first name of the faculty
	 * @param LastName the last name of the faculty
	 * @param Age the age of the faculty
	 * @param Gender the gender of the faculty
	 * @param Address the address of the faculty
	 */
	public Faculty(String FirstName, String LastName, int Age, String Gender, String Address) {
		//set the instance variables to the given parameters
		this.setFirstName(FirstName);
		this.setLastName(LastName);
		this.setAge(Age);
		this.setGender(Gender);
		this.setAddress(Address);
		this.setEmployeeID(CSD.EmployeeID);
		CSD.EmployeeID+=1;//increments CSD's EmployeeID
	}
	
	@Override
	/**
	 * This method overrides the java toString method and returns a string representation of the person
	 * @returns	a string depicting the person
	 */
	public String toString() {
		return "Faculty "+getProgramString()+"["+super.toString()+"]";
	}
	
	@Override
	/**
	 * This method compares this faculty to a given faculty
	 * @param f the faculty to which we are comparing this program director to
	 * @returns 0 if the program directors are identical, and 1 if they are different
	 */
	public int compareTo(Faculty f) {
		if (this.getAddress().compareTo(f.getAddress()) == 0
				&& this.getAge() == f.getAge()
				&& this.getEmployeeID() == f.getEmployeeID()
				&& this.getFirstName().compareTo(f.getFirstName()) == 0
				&& this.getGender().compareTo(f.getGender()) == 0
				&& this.getLastName().compareTo(f.getLastName()) == 0
				&& this.getProgramString().compareTo(f.getProgramString()) == 0
				&& this.getProgramDirector().compareTo(f.getProgramDirector()) == 0
				&& this.getSalary() == f.getSalary()
				&& this.StudentsAssigned.equals(f.StudentsAssigned)
				&& this.TAsAssigned.equals(f.TAsAssigned)) return 0; //faculties are the same
		return 1;//faculties are different
	}
	
	/**
	 * @param program the program to set
	 */
	public void setProgram(String program) {
		this.program = program;
	}
	
	/**
	 * @return the Program
	 */
	public Object getProgram() {
		return this.program;
	}

	/**
	 * @return the list of students assigned to this faculty
	 */
	public List<UGrad> getAdvisingUgrads() {
		return this.StudentsAssigned;
	}
	
	/**
	 * @return the number of students assigned to this faculty
	 */
	public Integer getNumOfAdvisingUGrads() {
		return this.StudentsAssigned.size();
	}

	/**
	 * @return the number of TAs assigned to this faculty
	 */
	public Integer getNumOfTAs() {
		return this.TAsAssigned.size();
	}

	/**
	 * @return the list of TAs assigned to this faculty
	 */
	public List<Grad> getTAs() {
		return this.TAsAssigned;
	}

	/**
	 * @return the programDirector
	 */
	public ProgramDirector getProgramDirector() {
		return this.ProgramDirector;
	}

	/**
	 * @param programDirector the programDirector to set
	 */
	public void setProgramDirector(ProgramDirector programDirector) {
		this.ProgramDirector = programDirector;
	}

	/**
	 * @return the program
	 */
	public String getProgramString() {
		return this.program;
	}
	
}
/**
 * This class extends person and encompasses the students
 * all students have a studentID and an advisor
 * This class also implements the comparable interface and is therefore comparable
 * 
 */
class Student extends Person implements Comparable<Student>{
	private int StudentID;//the student's ID
	private Faculty Advisor; //the student's advisor

	/**
	 * @return the Advisor
	 */
	public Object getAdvisor() {
		return this.Advisor;
	}
	
	@Override
	/**
	 * This method overrides the java toString method and returns a string representation of the person
	 * @returns	a string depicting the person
	 */
	public String toString() {
		return getStudentID()+"["+super.toString()+"]";
	}
	
	@Override
	/**
	 * This method compares this student to a given student
	 * @param s the student to which we are comparing this student to
	 * @returns 0 if the students are identical, and 1 if they are different
	 */
	public int compareTo(Student s) {
		if (this.getAddress().compareTo(s.getAddress()) == 0
				&& this.getAge() == s.getAge()
				&& this.getAdvisorFaculty().compareTo(s.getAdvisorFaculty()) == 0
				&& this.getFirstName().compareTo(s.getFirstName()) == 0
				&& this.getGender().compareTo(s.getGender()) == 0
				&& this.getLastName().compareTo(s.getLastName()) == 0
				&& this.getStudentID() == s.getStudentID()) return 0;//students are equal
		return 1;//students are different
	}

	/**
	 * @return the advisor
	 */
	public Faculty getAdvisorFaculty() {
		return this.Advisor;
	}

	/**
	 * @param advisor the advisor to set
	 */
	public void setAdvisor(Faculty advisor) {
		this.Advisor = advisor;
	}

	/**
	 * @return the studentID
	 */
	public int getStudentID() {
		return this.StudentID;
	}

	/**
	 * @param studentID the studentID to set
	 */
	public void setStudentID(int studentID) {
		this.StudentID = studentID;
	}
}
/**
 * This class extends student and encompasses the undergrad students
 * 
 */
class UGrad extends Student{
	
	/**
	 * This method sets up the undergrad students with all the appropriate attributes
	 * @param FirstName the first name of the undergrad student
	 * @param LastName the last name of the undergrad student
	 * @param Age the age of the undergrad student
	 * @param Gender the gender of the undergrad student
	 * @param Address the address of the undergrad student
	 */
	public UGrad(String FirstName, String LastName, int Age, String Gender, String Address) {
		//set the instance variables to the given parameters
		this.setFirstName(FirstName);
		this.setLastName(LastName);
		this.setAge(Age);
		this.setGender(Gender);
		this.setAddress(Address);
		this.setStudentID(CSD.StudentID);
		CSD.StudentID+=1;//increments CSD's student ID
	}

	@Override
	/**
	 * This method overrides the java toString method and returns a string representation of the person
	 * @returns	a string depicting the person
	 */
	public String toString() {
		return "Undergraduate ["+super.toString()+"]";
	}	
}
/**
 * This class extends student and encompasses the TAs
 * 
 */
class Grad extends Student{
	
	/**
	 * This method sets up the TAs with all the appropriate attributes
	 * @param FirstName the first name of the TA
	 * @param LastName the last name of the TA
	 * @param Age the age of the TA
	 * @param Gender the gender of the TA
	 * @param Address the address of the TA
	 */
	public Grad(String FirstName, String LastName, int Age, String Gender, String Address) {
		/**
		 * This method sets up the TA with all the appropriate attributes
		 * @param FirstName the first name of the TA
		 * @param LastName the last name of the TA
		 * @param Age the age of the TA
		 * @param Gender the gender of the TA
		 * @param Address the address of the TA
		 */
		this.setFirstName(FirstName);
		this.setLastName(LastName);
		this.setAge(Age);
		this.setGender(Gender);
		this.setAddress(Address);
		this.setStudentID(CSD.StudentID);
		CSD.StudentID+=1;//increments CSD's student ID
	}
	@Override
	/**
	 * This method overrides the java toString method and returns a string representation of the person
	 * @returns	a string depicting the person
	 */
	public String toString() {
		return "Graduate ["+super.toString()+"]";
	}
	
}

/**
 * This class is the NoSpaceException and it extends the java exception class
 * this exception is called when there is no space for students,TAs,Faculties, and program directors
 * 
 */
class NoSpaceException extends Exception{
	
	/**
	 * Default constructor when no extra parameters are given
	 * 
	 */
	public NoSpaceException() {
		super();//calls the super class
	}
	
	/**
	 * Overload constructor
	 * @param msg the message which accompanies the error
	 */
	public NoSpaceException(String msg) {
		super(msg);//calls the super class with the given error message
	}
	
}

/**
 * This class is the NoSpecialtyException and it extends the java exception class
 * this exception is called when a faculty is retiring and there is no other faculty with the same program to take over
 * 
 */
class NoSpecialtyException extends Exception{
	
	/**
	 * Default constructor when no extra parameters are given
	 * 
	 */
	public NoSpecialtyException() {
		super();//calls the super class
	}
	
	/**
	 * Overload constructor
	 * @param msg the message which accompanies the error
	 */
	public NoSpecialtyException(String msg) {
		super(msg);//calls the super class with the given error message
	}
}

/**
 * This class is the NoSpecialtyException and it extends the java exception class
 * this exception is called when this TA is the last TA in the faculty and removing them would result in no TA for the faculty
 * 
 */
class NoTAException extends Exception{
	
	/**
	 * Default constructor when no extra parameters are given
	 * 
	 */
	public NoTAException() {
		super();//calls the super class
	}
	
	/**
	 * Overload constructor
	 * @param msg the message which accompanies the error
	 */
	public NoTAException(String msg) {
		super(msg);//calls the super class with the given error message
	}
}




