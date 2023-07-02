package Project;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class userInfo {
    private String name;
    private String address;
    private String phone;

    public userInfo(String name, String address, String phone) {
        this.name = name;
        this.address = address;
        this.phone = phone;
    }

    public void saveToFile() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("Information.txt", true));
            writer.write(name + " , " + address + " , " + phone);
            writer.newLine();
            writer.close();

            System.out.println("Successfully Logged In");
        } catch (IOException ex) {
            System.out.println("Failed to write information to file");
            ex.printStackTrace();
        }
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}

