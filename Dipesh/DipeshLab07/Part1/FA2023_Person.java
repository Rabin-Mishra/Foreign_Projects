// File Name: FA2023_Person.java

public class FA2023_Person {
    protected String SSNumber;
    protected String lastName;
    protected String firstName;
    protected String phone;
    protected String address;

    public FA2023_Person() {
        SSNumber = "";
        lastName = "";
        firstName = "";
        phone = "";
        address = "";
    }

    public FA2023_Person(String num, String last, String first, String phoneN, String addr) {
        SSNumber = num;
        lastName = last;
        firstName = first;
        phone = phoneN;
        address = addr;
    }

    // Mutator methods
    public void setLastName(String lname) {
        lastName = lname;
    }

    public void setFirstName(String fname) {
        firstName = fname;
    }

    public void setPhone(String newPhone) {
        phone = newPhone;
    }

    public void setAddress(String addr) {
        address = addr;
    }

    // Assessor methods
    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    // Method toString()
    public String toString() {
        return String.format("%-15s%25s\n", "SS Number: ", SSNumber) +
                String.format("%-15s%25s\n", "Name: ", lastName + ", " + firstName) +
                String.format("%-15s%25s\n", "Phone: ", phone) +
                String.format("%-15s%25s\n", "Address: ", address);
    }
}
