package Models;

import javafx.scene.image.Image;


import java.io.File;
import java.time.LocalDate;

public class Contact {
    private int ID;
    private String firstName, lastName, address, phone;
    private LocalDate birthday;
    private File profileImage;

    public Contact(String firstName, String lastName, LocalDate birthday, String address, String phone) {
        setFirstName(firstName);
        setLastName(lastName);
        setBirthday(birthday);
        setAddress(address);
        setPhone(phone);
    }

    public Contact(File userImage, String firstName, String lastName, LocalDate birthday, String address, String phone) {
        setProfileImage(userImage);
        setFirstName(firstName);
        setLastName(lastName);
        setBirthday(birthday);
        setAddress(address);
        setPhone(phone);
    }

    public int getID() { return ID; }

    public void setID(int ID) {
        if (ID > 0 && ID < 4)
            this.ID = ID;
        else
            throw new IllegalArgumentException("Please enter a 3 digit ID number");
    }

    public File getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(File profileImage) {
        this.profileImage = profileImage;
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

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
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
