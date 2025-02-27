package tr.com.nemesis.dal;

import tr.com.nemesis.core.ObjectHelper;
import tr.com.nemesis.interfaces.IDataAccesLayer;
import tr.com.nemesis.type.EnergyConsumption;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class energyDal extends ObjectHelper implements IDataAccesLayer<EnergyConsumption> {
    @Override
    public void insert(EnergyConsumption variable) {

        Connection connection = getConnection();
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO EnergyConsumption (TimePeriod,Quantity,EnergyType,DeviceID) VALUES ('"+variable.getTimePeriod()+"','"+variable.getQuantity()+"','"+variable.getEnergyType()+"','"+variable.getDeviceID()+"')");

            statement.close();
            connection.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void update(EnergyConsumption variable) {

    }

    @Override
    public void delete(EnergyConsumption variable) {

    }

    @Override
    public List getList() {
        return null;
    }

    @Override
    public EnergyConsumption getById(int id) {
        return null;
    }
}
