package Models;

import javafx.scene.image.Image;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.SecureRandom;
import java.time.LocalDate;

public class Contact {
    private int ID;
    private String firstName, lastName, address, phone;
    private LocalDate birthday;
    private File profileImage;

    public Contact(String firstName, String lastName, LocalDate birthday, String address, String phone) {
        setProfileImage(new File("./src/images/default-image.jpg"));
        setFirstName(firstName);
        setLastName(lastName);
        setBirthday(birthday);
        setAddress(address);
        setPhone(phone);
    }

    public Contact(File userImage, String firstName, String lastName, LocalDate birthday, String address, String phone) throws IOException {
        this(firstName, lastName, birthday, address, phone);
        setProfileImage(userImage);
        copyImageFile();
    }

    public int getID() { return ID; }

    public void setID(int ID) {
        if (ID > 0)
            this.ID = ID;
        else
            throw new IllegalArgumentException("ID error. Make sure you are calling the correct table in the DB");
    }

    public File getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(File profileImage) {
        this.profileImage = profileImage;
    }

    /**
     * This method will copy the file specified to the images
     * directory on this server,
     * then give it a unique name
     * @return
     */
    public void copyImageFile() throws IOException {
        // create a new Path to copy the image into a local directory
        Path sourcePath = profileImage.toPath();

        String uniqueFileName = getUniqueFileName(profileImage.getName());

        Path targetPath = Paths.get(".\\src\\images\\"+uniqueFileName);

        // copy the file to the new directory
        Files.copy(sourcePath, targetPath, StandardCopyOption.REPLACE_EXISTING);

        // update the imageFile to point to the new File
        profileImage = new File(targetPath.toString());
    }

    /**
     * This method will receive a String that represents a file name
     * and return a String with a random, unique set
     * of letters prefixed to it
     * @return
     */
    private String getUniqueFileName(String oldFileName)
    {
        String newName;

        // create a Random Number Generator
        SecureRandom rng = new SecureRandom();

        // loop until we have a unique file name
        do
        {
            newName = "";

            // generate 32 random characters
            for (int count = 1; count <= 32; count++)
            {
                int nextChar;

                do
                {
                    nextChar = rng.nextInt(123);
                } while(!validCharacterValue(nextChar));

                newName = String.format("%s%c", newName, nextChar);
            }
            newName += oldFileName;

        } while (!uniqueFileInDirectory(newName));

        return newName;
    }

    /**
     * This method will search the images directory and ensure that
     * the file name is unique
     */
    public boolean uniqueFileInDirectory(String fileName)
    {
        File directory = new File("./src/images/");

        File[] dir_contents = directory.listFiles();

        for (File file: dir_contents)
        {
            if (file.getName().equals(fileName))
                return false;
        }
        return true;
    }

    /**
     * This method will validate if the integer given
     * corresponds with a valid ASCII character that could be
     * used in a file name
     * @return
     */
    public boolean validCharacterValue(int asciiValue)
    {
        // 0-9 = ASCII range 48 to 57
        if (asciiValue >= 48 && asciiValue <= 57)
            return true;

        // A-Z ASCII range 65 to 90
        if (asciiValue >= 65 && asciiValue <= 90)
            return true;

        // a-z ASCII range 97 to 122
        if (asciiValue >= 97 && asciiValue <= 122)
            return true;

        return false;
    }

    public String getFirstName() { return firstName; }

    public void setFirstName(String firstName) {
        if (!firstName.isEmpty())
            this.firstName = firstName;
        else
            throw new IllegalArgumentException("Please enter a first name");
    }

    public String getLastName() { return lastName; }

    public void setLastName(String lastName) {
        if (!lastName.isEmpty())
            this.lastName = lastName;
        else
            throw new IllegalArgumentException("Please enter a last name");
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
            throw new IllegalArgumentException("Please enter an address");
    }

    public String getPhone() { return phone; }

    public void setPhone(String phone) {
        if (phone.matches("[1-9]\\d{2}-[1-9]\\d{2}-\\d{4}"))
            this.phone = phone;
        //        if (phone.length() > 0 && phone.length() <= 12)
//            this.phone = phone;
        else
            throw new IllegalArgumentException("Please enter a valid phone number in the format 555-555-5555");
    }
}
