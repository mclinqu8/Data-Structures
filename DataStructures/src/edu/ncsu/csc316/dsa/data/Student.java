package edu.ncsu.csc316.dsa.data;

/**
 * A student is comparable and identifiable. Students have a first name, last
 * name, id number, number of credit hours, gpa, and unityID.
 * 
 * @author Dr. King
 * @author Maggie Lin
 *
 */
public class Student implements Comparable<Student>, Identifiable {

	/** The first name of the student */
	private String first;

	/** The last name of the student */
	private String last;

	/** The id of the student */
	private int id;

	/** The amount of credit hours the student has */
	private int creditHours;

	/** The student's GPA */
	private double gpa;

	/** The unityID of the student */
	private String unityID;

	/**
	 * Construct a Student object
	 * 
	 * @param first       Student's first name
	 * @param last        Student's last name
	 * @param id          Student's id
	 * @param creditHours Student's credit hours
	 * @param gpa         Student's GPA
	 * @param unityID     Student's unityID
	 */
	public Student(String first, String last, int id, int creditHours, double gpa, String unityID) {
		this.first = first;
		this.last = last;
		this.id = id;
		this.creditHours = creditHours;
		this.gpa = gpa;
		this.unityID = unityID;
	}

	/**
	 * Return the Student's first name.
	 * 
	 * @return First name of the Student.
	 */
	public String getFirst() {
		return first;
	}

	/**
	 * Return the Student's last name.
	 * 
	 * @return Last name of the Student.
	 */
	public String getLast() {
		return last;
	}

	/**
	 * Return the Student's id.
	 * 
	 * @return ID number of the Student.
	 */
	public int getId() {
		return id;
	}

	/**
	 * Return the Student's credit hours.
	 * 
	 * @return Credit hours of the Student.
	 */
	public int getCreditHours() {
		return creditHours;
	}

	/**
	 * Return the Student's GPA.
	 * 
	 * @return GPA of the Student.
	 */
	public double getGpa() {
		return gpa;
	}

	/**
	 * Return the Student's Unity ID.
	 * 
	 * @return Unity ID of the Student.
	 */
	public String getUnityID() {
		return unityID;
	}

	/**
	 * Set Student's first name to the given first name.
	 * 
	 * @param first Student's first name.
	 */
	public void setFirst(String first) {
		this.first = first;
	}

	/**
	 * Set Student's last name to the given last name.
	 * 
	 * @param last Student's last name.
	 */
	public void setLast(String last) {
		this.last = last;
	}

	/**
	 * Set Student's id number to the given id number.
	 * 
	 * @param id Student's id number.
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Set Student's credit hours to the given credit hours.
	 * 
	 * @param creditHours Student's credit hours.
	 */
	public void setCreditHours(int creditHours) {
		this.creditHours = creditHours;
	}

	/**
	 * Set Student's GPA to the given GPA.
	 * 
	 * @param gpa Student's GPA.
	 */
	public void setGpa(double gpa) {
		this.gpa = gpa;
	}

	/**
	 * Set Student's Unity ID to the given Unity ID.
	 * 
	 * @param unityID Student's Unity ID.
	 */
	public void setUnityID(String unityID) {
		this.unityID = unityID;
	}

	/**
	 * Generates a hashCode for Student using the first, last, and id field.
	 * 
	 * @return hashCode for Student
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((first == null) ? 0 : first.hashCode());
		result = prime * result + id;
		result = prime * result + ((last == null) ? 0 : last.hashCode());
		return result;
	}

	/**
	 * Compares a given object to this Student for whether the two object have the
	 * same first name, last name, and student ID.
	 * 
	 * @param obj The object being compared.
	 * @return true if the two Students have the same first name, last name, and
	 *         Student ID.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (obj == null) {
			return false;
		}

		if (getClass() != obj.getClass()) {
			return false;
		}
		Student s = (Student) obj;

		return getLast() == s.getLast() && getFirst() == s.getFirst() && getId() == s.getId();

	}

	/**
	 * Compares if one student object is less than, more than, or equal to another
	 * object
	 * 
	 * @param s Student object that current Student is being compared to.
	 * @return positive or negative integer based on the order of the two objects
	 *         being compared.
	 */
	public int compareTo(Student s) {
		if (this.getLast().compareToIgnoreCase(s.getLast()) == 0) {
			if (this.getFirst().compareToIgnoreCase(s.getFirst()) == 0) {
				if (this.getId() == s.getId()) {
					return this.getId() - s.getId();
				}
			} else {
				return this.getFirst().compareToIgnoreCase(s.getFirst());
			}
		} else {
			return this.getLast().compareTo(s.getLast());
		}
		return 0;
	}

	/**
	 * Returns the string containing all the Student's information.
	 * 
	 * @return A line containing all the Student's information.
	 */
	@Override
	public String toString() {
		return "Student's first name: " + first + ", last name: " + last + ", id: " + id + ", creditHours: "
				+ creditHours + ", gpa: " + gpa + ", unityID: " + unityID;
	}

}
