package tr.com.nemesis.dal;

import tr.com.nemesis.core.ObjectHelper;
import tr.com.nemesis.interfaces.IDataAccesLayer;
import tr.com.nemesis.type.Billing;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class billingDal extends ObjectHelper implements IDataAccesLayer<Billing> {
    @Override
    public void insert(Billing variable) {

        Connection connection = getConnection();
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO Billing (BillingID,AmountDue,PaymentStatus,DueDate) VALUES ('"+variable.getBillingID()+"','"+variable.getAmountDue()+"','"+variable.getPaymentStatus()+"','"+variable.getDueDate()+"')");

            statement.close();
            connection.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void update(Billing variable) {

    }

    @Override
    public void delete(Billing variable) {

    }

    @Override
    public List getList() {
        return null;
    }

    @Override
    public Billing getById(int id) {
        return null;
    }
}
