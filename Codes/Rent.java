package Project;
import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Calendar;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.util.HashMap;

public class Rent extends JFrame {

    private JPanel contentPane;
    private JTextField nameTxt;
    private JTextField addressTxt;
    private JTextField phoneTxt;
    private JTextField timeTxt;
    private JComboBox<String> rentTxt;
    private JComboBox<String> quantityTxt;
    private JLabel vieweqBtn;
    private JComboBox<String> monthTxt;
    private JComboBox<String> dayTxt;
    private JComboBox<String> yearTxt;
    private HashMap<String, Integer> equipmentQuantities = new HashMap<>();

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Rent frame = new Rent();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void loadEquipmentData() {
        try (BufferedReader br = new BufferedReader(new FileReader("Equipments.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 2) {
                    String equipment = data[0].trim();
                    int quantity = Integer.parseInt(data[1].trim());
                    String equipmentWithQuantity = equipment + " - " + quantity;
                    rentTxt.addItem(equipmentWithQuantity);

                    // Store initial quantity in the HashMap
                    equipmentQuantities.put(equipmentWithQuantity, quantity);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void writeRentDataToFile(String name, String address, String phone, String equipment, String quantity, Object selectedMonth, Object selectedDay, Object selectedYear, String time) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Rent.txt", true))) {
            String rentData = name + "," + address + "," + phone + "," + equipment + "," + quantity + "," + selectedMonth + "," + selectedDay + "," + selectedYear + "," + time;
            writer.write(rentData);
            writer.newLine();
            // Deduct the rented quantity from the equipment quantity
            int rentedQuantity = Integer.parseInt(quantity);
            int availableQuantity = equipmentQuantities.get(equipment) - rentedQuantity;
            equipmentQuantities.put(equipment, availableQuantity); // Update the quantity in the HashMap
            updateEquipmentDataFile(); // Update the equipment data file with the new quantity
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadQuantityData(String selectedEquipment) {
        try (BufferedReader br = new BufferedReader(new FileReader("Equipments.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 2) {
                    String equipment = data[0].trim();
                    String quantity = data[1].trim();
                    if (equipment.equals(selectedEquipment)) {
                        int maxQuantity = Integer.parseInt(quantity);
                        for (int i = 1; i <= maxQuantity; i++) {
                            quantityTxt.addItem(String.valueOf(i));
                        }
                        break;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void updateEquipmentDataFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Equipments.txt"))) {
            for (String equipment : equipmentQuantities.keySet()) {
                int quantity = equipmentQuantities.get(equipment);
                String[] equipmentData = equipment.split(" - ");
                String equipmentName = equipmentData[0].trim();
                writer.write(equipmentName + "," + quantity);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

   



    private void setInitialDateValues() {
        // Month options
        String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        for (String month : months) {
            monthTxt.addItem(month);
        }

        // Day options
        for (int i = 1; i <= 31; i++) {
            dayTxt.addItem(String.valueOf(i));
        }

        // Year options
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        for (int i = currentYear - 10; i <= currentYear + 10; i++) {
            yearTxt.addItem(String.valueOf(i));
        }

        // Set current date as selected
        Calendar calendar = Calendar.getInstance();
        int currentMonth = calendar.get(Calendar.MONTH);
        int currentDay = calendar.get(Calendar.DAY_OF_MONTH);
        int currentYearIndex = currentYear - (currentYear - 10); // Index of current year in the JComboBox
        monthTxt.setSelectedIndex(currentMonth);
        dayTxt.setSelectedIndex(currentDay - 1); // Subtract 1 to match 0-based index
        yearTxt.setSelectedIndex(currentYearIndex);
    }

    public Rent() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1000, 600);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
     // ...

        JLabel submitBtn = new JLabel("");
        submitBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String name = nameTxt.getText();
                String address = addressTxt.getText();
                String phone = phoneTxt.getText();
                Object selectedEquipment = rentTxt.getSelectedItem();
                Object selectedQuantity = quantityTxt.getSelectedItem();
                Object selectedMonth = monthTxt.getSelectedItem();
                Object selectedDay = dayTxt.getSelectedItem();
                Object selectedYear = yearTxt.getSelectedItem();
                String time = timeTxt.getText();
                
                // Check if phone number is valid
                if (!phone.matches("09\\d{9}")) {
                    JOptionPane.showMessageDialog(Rent.this, "Invalid phone number. Please enter a valid 11-digit number starting with '09'.", "Invalid Phone Number", JOptionPane.WARNING_MESSAGE);
                    return;
                }


                // Check if all fields are filled
                if (name.isEmpty() || address.isEmpty() || phone.isEmpty() || selectedEquipment == null || selectedQuantity == null || selectedMonth == null || selectedDay == null || selectedYear == null || time.isEmpty()) {
                    JOptionPane.showMessageDialog(Rent.this, "Please fill up the form.", "Incomplete Form", JOptionPane.WARNING_MESSAGE);
                } else {
                    String equipment = selectedEquipment.toString();
                    String quantity = selectedQuantity.toString();
                    String date = selectedMonth.toString() + " " + selectedDay.toString() + ", " + selectedYear.toString();

                    // Write rent data to the text file
                    writeRentDataToFile(name, address, phone, equipment, quantity, selectedMonth, selectedDay, selectedYear, time);


                    
                    HomePage home = new HomePage();
                    home.setVisible(true);
                    dispose();
                    // Pass the 'records' object to the next step (e.g., display or process the data in Records.java)

                    // Display success message
                    JOptionPane.showMessageDialog(Rent.this, "Rent successful.", "Success", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        
       

        // ...

        submitBtn.setBounds(506, 467, 81, 25);
        contentPane.add(submitBtn);
        
        monthTxt = new JComboBox<>();
        monthTxt.setBounds(750, 230, 53, 32);
        contentPane.add(monthTxt);
        
        dayTxt = new JComboBox<>();
        dayTxt.setBounds(803, 230, 62, 32);
        contentPane.add(dayTxt);
        
        yearTxt = new JComboBox<>();
        yearTxt.setBounds(865, 230, 67, 32);
        contentPane.add(yearTxt);

        rentTxt = new JComboBox<String>() {
            @Override
            public void setSelectedItem(Object item) {
                super.setSelectedItem(item);
                String selectedEquipment = item.toString().split(" - ")[0];
                quantityTxt.removeAllItems();
                loadQuantityData(selectedEquipment);

                // Get the initial quantity for the selected equipment
                int initialQuantity = equipmentQuantities.get(item.toString());

                // Add quantity options based on the initial quantity
                for (int i = 1; i <= initialQuantity; i++) {
                    quantityTxt.addItem(String.valueOf(i));
                }
            }
        };

        rentTxt.setBounds(305, 403, 198, 32);
        contentPane.add(rentTxt);

        nameTxt = new JTextField();
        nameTxt.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        nameTxt.setBackground(null);
        nameTxt.setBorder(null);
        nameTxt.setOpaque(false);
        nameTxt.setBounds(254, 237, 351, 25);
        contentPane.add(nameTxt);
        nameTxt.setColumns(10);

        addressTxt = new JTextField();
        addressTxt.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        addressTxt.setBackground(null);
        addressTxt.setBorder(null);
        addressTxt.setOpaque(false);
        addressTxt.setBounds(254, 291, 351, 25);
        contentPane.add(addressTxt);
        addressTxt.setColumns(10);

        phoneTxt = new JTextField();
        phoneTxt.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        phoneTxt.setBackground(null);
        phoneTxt.setBorder(null);
        phoneTxt.setOpaque(false);
        phoneTxt.setBounds(254, 352, 351, 25);
        contentPane.add(phoneTxt);
        phoneTxt.setColumns(10);

        timeTxt = new JTextField();
        timeTxt.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        timeTxt.setBackground(null);
        timeTxt.setBorder(null);
        timeTxt.setOpaque(false);
        timeTxt.setBounds(775, 296, 125, 25);
        contentPane.add(timeTxt);
        timeTxt.setColumns(10);

        quantityTxt = new JComboBox<String>();
        quantityTxt.setBounds(571, 403, 151, 32);
        contentPane.add(quantityTxt);

        JLabel RentPic = new JLabel("");
        RentPic.setIcon(new ImageIcon("C:\\Users\\VJRM14\\Downloads\\MicrosoftTeams-image (9).png"));
        RentPic.setBounds(0, 0, 1000, 600);
        contentPane.add(RentPic);

        vieweqBtn = new JLabel("");
        vieweqBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                UserView userView = new UserView();
                userView.setVisible(true);
                dispose();
            }
        });
        vieweqBtn.setBounds(21, 520, 248, 41);
        contentPane.add(vieweqBtn);

        loadEquipmentData(); // Call to load equipment data
        setInitialDateValues(); // Call to set initial date values
    }
}
