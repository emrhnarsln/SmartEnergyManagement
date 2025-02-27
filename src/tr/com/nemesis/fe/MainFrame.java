package tr.com.nemesis.fe;

import tr.com.nemesis.abs.AFrame;
import tr.com.nemesis.dal.DeviceDal;
import tr.com.nemesis.dal.UserDal;
import tr.com.nemesis.dal.billingDal;
import tr.com.nemesis.dal.energyDal;
import tr.com.nemesis.type.Billing;
import tr.com.nemesis.type.Device;
import tr.com.nemesis.type.EnergyConsumption;
import tr.com.nemesis.type.Users;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Date;
import java.util.List;

public class MainFrame extends AFrame {

    private JTextField nameField;
    private JTextField idField;
    private JTextField contactInfoField;
    private JList<Users> listUsers;

    public MainFrame() {
        initFrame("Add User", initPanel());
    }

    @Override
    public JPanel initPanel() {
        JPanel mainPanel = new JPanel(new BorderLayout());

        JPanel leftPanel = initLeftPanel();
        JPanel rightPanel = initRightPanel();

        mainPanel.add(leftPanel, BorderLayout.WEST);
        mainPanel.add(rightPanel, BorderLayout.CENTER);

        return mainPanel;
    }

    private JPanel initLeftPanel() {
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setPreferredSize(new Dimension(300, 600)); // Fixed height for consistency

        JPanel userFormPanel = createUserFormPanel();
        leftPanel.add(userFormPanel);

        listUsers = new JList<>(new DefaultListModel<>());
        updateUsersList();

        JScrollPane paneUsers = new JScrollPane(listUsers);
        leftPanel.add(paneUsers);

        setupUserListSelectionListener();

        return leftPanel;
    }

    private JPanel createUserFormPanel() {
        JPanel userFormPanel = new JPanel(new GridLayout(6, 2));
        JLabel searchJLabel = new JLabel("Search ID:",JLabel.RIGHT);
        userFormPanel.add(searchJLabel);
        JTextField searchField = new JTextField(10);
        userFormPanel.add(searchField);
        JLabel nameJLabel = new JLabel("Name & Surname:", JLabel.RIGHT);
        nameField = new JTextField(30);
        JLabel idJLabel = new JLabel("ID:", JLabel.RIGHT);
        idField = new JTextField(30);
        JLabel contactInfoLabel = new JLabel("Email:", JLabel.RIGHT);
        contactInfoField = new JTextField(30);
        JButton saveButton = new JButton("Save");
        JButton updateButton = new JButton("Update");
        JButton deleteButton = new JButton("Delete");

        userFormPanel.add(nameJLabel);
        userFormPanel.add(nameField);
        userFormPanel.add(idJLabel);
        userFormPanel.add(idField);
        userFormPanel.add(contactInfoLabel);
        userFormPanel.add(contactInfoField);
        userFormPanel.add(saveButton);
        userFormPanel.add(updateButton);
        userFormPanel.add(deleteButton);

        saveButton.addActionListener(this::saveUser);
        searchField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                updateUsersList(searchField.getText());
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Users cast = listUsers.getSelectedValue();
                Users user = new Users();

                user.setId(cast.getId());
                user.setName(nameField.getText());
                user.setContInfo(contactInfoField.getText());

                new UserDal().update(user);
                JOptionPane.showMessageDialog(idField, "The person with the "+user.getId()+" ID has been edited!");
                listUsers.setListData((new UserDal().getList().toArray(new Users[0])));
                listUsers.setSelectedIndex(0);
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Users user = listUsers.getSelectedValue();

                new UserDal().delete(user);
                JOptionPane.showMessageDialog(idField, "The person with the " + user.getId() + " ID has been deleted!");
                listUsers.setListData(new UserDal().getList().toArray(new Users[0]));

            }
        });

        return userFormPanel;
    }
    private void updateUsersList() {
        updateUsersList("");
    }

    private void updateUsersList(String search) {
        DefaultListModel<Users> model = (DefaultListModel<Users>) listUsers.getModel();
        model.clear();
        List<Users> users;
        if (search.isEmpty()) {
            users = new UserDal().getList();
        } else {
            try {
                int userId = Integer.parseInt(search);
                users = new UserDal().getSearch(Integer.parseInt(String.valueOf(userId)));
            } catch (NumberFormatException e) {
                users = new UserDal().getSearch(Integer.parseInt(search));
            }
        }

        for (Users user : users) {
            model.addElement(user);
        }
    }

    private void saveUser(ActionEvent e) {
        try {
            Users user = new Users();
            user.setName(nameField.getText());
            user.setContInfo(contactInfoField.getText());
            user.setId(Integer.parseInt(idField.getText()));
            new UserDal().insert(user);
            JOptionPane.showMessageDialog(this, "User " + user.getName() + " has been registered!", "Success", JOptionPane.INFORMATION_MESSAGE);
            updateUsersList();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid integer ID.", "Invalid ID!", JOptionPane.ERROR_MESSAGE);
        }
    }


    private void setupUserListSelectionListener() {
        listUsers.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                Users selectedUser = listUsers.getSelectedValue();
                if (selectedUser != null) {
                    Users user = new UserDal().getById(selectedUser.getId());
                    nameField.setText(user.getName());
                    contactInfoField.setText(user.getContInfo());
                    idField.setText(String.valueOf(user.getId()));
                }
                else {
                    listUsers.setSelectedIndex(0);
                }
            }
        });
    }
    private JPanel initRightPanel() {
        JPanel rightPanel = new JPanel(new GridLayout(3, 1));

        // İlk satır
        JPanel rightPanel1 = createDeviceFormPanel();
        JScrollPane scrollPane1 = new JScrollPane(rightPanel1);
        rightPanel.add(scrollPane1);

        // İkinci satır
        JPanel rightPanel2 = createEnergyConsumptionPanel();
        JScrollPane scrollPane2 = new JScrollPane(rightPanel2);
        rightPanel.add(scrollPane2);

        // Üçüncü satır
        JPanel rightPanel3 = createBillingPanel();
        JScrollPane scrollPane3 = new JScrollPane(rightPanel3);
        rightPanel.add(scrollPane3);

        return rightPanel;
    }


    private JPanel createDeviceFormPanel() {
        JPanel devicePanel = new JPanel(new GridLayout(7, 2));

        JLabel searchJLabel = new JLabel("Search Device ID:",JLabel.RIGHT);
        devicePanel.add(searchJLabel);
        JTextField searchField = new JTextField(10);
        devicePanel.add(searchField);

        JLabel deviceIDJLabel = new JLabel("DeviceID:", JLabel.RIGHT);
        JTextField deviceIDField = new JTextField(30);
        JLabel typeJLabel = new JLabel("Type:", JLabel.RIGHT);
        JTextField typeField = new JTextField(30);
        JLabel installationDateJLabel = new JLabel("Installation Date:", JLabel.RIGHT);
        JTextField installationDateField = new JTextField(30);
        JLabel statusJLabel = new JLabel("Status:", JLabel.RIGHT);
        JTextField statusField = new JTextField(30);
        JButton saveButton = new JButton("Save");
        JButton updateButton = new JButton("Update");
        JButton deleteButton = new JButton("Delete");

        devicePanel.add(deviceIDJLabel);
        devicePanel.add(deviceIDField);
        devicePanel.add(typeJLabel);
        devicePanel.add(typeField);
        devicePanel.add(installationDateJLabel);
        devicePanel.add(installationDateField);
        devicePanel.add(statusJLabel);
        devicePanel.add(statusField);
        devicePanel.add(saveButton);
        devicePanel.add(updateButton);
        devicePanel.add(deleteButton);

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Device device =new Device();
                device.setDeviceID(Integer.parseInt(deviceIDField.getText()));
                device.setType(typeField.getText());
                device.setInstallationsDate(Date.valueOf(installationDateField.getText()));
                device.setStatus(statusField.getText());
                new DeviceDal().insert(device);
                JOptionPane.showMessageDialog(typeField, "The device "+device.getDeviceID()+" ID has been registered!");
            }
        });
        return devicePanel;
    }

    private  JPanel createEnergyConsumptionPanel() {
        JPanel energyPanel = new JPanel(new GridLayout(7,2));

        JLabel searchJLabel = new JLabel("Search Device ID:",JLabel.RIGHT);
        energyPanel.add(searchJLabel);
        JTextField searchField = new JTextField(10);
        energyPanel.add(searchField);

        JLabel deviceIDJLabel = new JLabel("DeviceID:", JLabel.RIGHT);
        JTextField deviceIDField = new JTextField(30);
        JLabel timePeriodJLabel = new JLabel("Time Period:", JLabel.RIGHT);
        JTextField timePeriodField = new JTextField(30);
        JLabel quantityJLabel = new JLabel("Quantity:", JLabel.RIGHT);
        JTextField quantityField = new JTextField(30);
        JLabel energyTypeJLabel = new JLabel("Energy Type:", JLabel.RIGHT);
        JTextField energyTypeField = new JTextField(30);
        JButton saveButton = new JButton("Save");
        JButton updateButton = new JButton("Update");
        JButton deleteButton = new JButton("Delete");

        energyPanel.add(timePeriodJLabel);
        energyPanel.add(timePeriodField);
        energyPanel.add(quantityJLabel);
        energyPanel.add(quantityField);
        energyPanel.add(energyTypeJLabel);
        energyPanel.add(energyTypeField);
        energyPanel.add(deviceIDJLabel);
        energyPanel.add(deviceIDField);
        energyPanel.add(saveButton);
        energyPanel.add(updateButton);
        energyPanel.add(deleteButton);

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EnergyConsumption energyConsumption = new EnergyConsumption();
                energyConsumption.setDeviceID(Integer.parseInt(deviceIDField.getText()));
                energyConsumption.setTimePeriod(Date.valueOf(timePeriodField.getText()));
                energyConsumption.setQuantity(Float.parseFloat(quantityField.getText()));
                energyConsumption.setEnergyType(energyTypeField.getText());
                new energyDal().insert(energyConsumption);
                JOptionPane.showMessageDialog(energyTypeField, "The device "+energyConsumption.getDeviceID()+" ID has been registered!");
            }
        });
        return energyPanel;
    }

    private JPanel  createBillingPanel() {

        JPanel billingPanel = new JPanel(new GridLayout(5,2));
        JLabel amountJLabel = new JLabel("AmountDue:", JLabel.RIGHT);
        JTextField amountField = new JTextField(30);
        JLabel paymentStatusJLabel = new JLabel("Payment Status:", JLabel.RIGHT);
        JTextField paymentStatusField = new JTextField(30);
        JLabel dueDateJLabel = new JLabel("Due Date:", JLabel.RIGHT);
        JTextField dueDateField = new JTextField(30);
        JButton saveButton = new JButton("Save");
        JButton updateButton = new JButton("Update");
        JButton deleteButton = new JButton("Delete");

        billingPanel.add(amountJLabel);
        billingPanel.add(amountField);
        billingPanel.add(paymentStatusJLabel);
        billingPanel.add(paymentStatusField);
        billingPanel.add(dueDateJLabel);
        billingPanel.add(dueDateField);
        billingPanel.add(saveButton);
        billingPanel.add(updateButton);
        billingPanel.add(deleteButton);


        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Billing billing = new Billing();
                billing.setAmountDue(Float.parseFloat(amountField.getText()));
                billing.setPaymentStatus(paymentStatusField.getText());
                billing.setDueDate(Date.valueOf(dueDateField.getText()));
                new billingDal().insert(billing);
                JOptionPane.showMessageDialog(paymentStatusField, "The billing registered, successfully!");
            }
        });

        return billingPanel;
    }

}
