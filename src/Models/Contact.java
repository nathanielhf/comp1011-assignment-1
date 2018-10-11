package Models;

import javafx.scene.image.Image;

import java.time.LocalDate;

public class Contact {
    private int ID;
    private String firstName, lastName, address, phone;
    private LocalDate birthday;
    private Image profileImage;

    public Contact( /*int ID,*/ Image userImage, String firstName, String lastName, LocalDate birthday, String address, String phone) {
        //this.ID = ID;
        this.profileImage = userImage;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.address = address;
        this.phone = phone;
    }

    public int getID() { return ID; }

    public void setID(int ID) {
        if (ID > 0 && ID < 4)
            this.ID = ID;
        else
            throw new IllegalArgumentException("Please enter a 3 digit ID number");
    }

    public String getFirstName() { return firstName; }

    public void setFirstName(String firstName) {
        if (!firstName.isEmpty())
            this.firstName = firstName;
        else
            throw new IllegalArgumentException("First Name cannot be empty");
    }

    public String getLastName() { return lastName; }

    public void setLastName(String lastName) {
        if (!lastName.isEmpty())
            this.lastName = lastName;
        else
            throw new IllegalArgumentException("Last Name cannot be empty");
    }

    public String getAddress() { return address; }

    public void setAddress(String address) {
        if (!address.isEmpty())
            this.address = address;
        else
            throw new IllegalArgumentException("Address cannot be empty");
    }

    public String getPhone() { return phone; }

    public void setPhone(String phone) {
        if (phone.length() > 0 && phone.length() <= 12)
            this.phone = phone;
        else
            throw new IllegalArgumentException("Please enter a phone number in the format 555-555-5555");
    }
}
