package tr.com.nemesis.dal;

import tr.com.nemesis.core.ObjectHelper;
import tr.com.nemesis.interfaces.IDataAccesLayer;
import tr.com.nemesis.type.Device;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DeviceDal extends ObjectHelper implements IDataAccesLayer<Device> {

    @Override
    public void insert(Device variable) {
        Connection connection = getConnection();
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO Device (DeviceID,Type,InstallationDate,Status) VALUES ('"+variable.getDeviceID()+"','"+variable.getType()+"','"+variable.getInstallationsDate()+"','"+variable.getStatus()+"')");

            statement.close();
            connection.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Device variable) {

    }

    @Override
    public void delete(Device variable) {

    }

    @Override
    public List<Device> getList() {
        return null;
    }

    @Override
    public Device getById(int id) {
        return null;
    }
}
