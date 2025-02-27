package tr.com.nemesis.dal;

import tr.com.nemesis.core.ObjectHelper;
import tr.com.nemesis.type.Users;
import tr.com.nemesis.interfaces.IDataAccesLayer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDal extends ObjectHelper implements IDataAccesLayer<Users> {
    @Override
    public void insert(Users user) {
        Connection connection = getConnection();
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO Users (UserID,Name,ContInfo) VALUES('" + user
                    .getId() + "','" + user.getName() + "', '"+ user.getContInfo() +"')");

            statement.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public void update(Users user) {
        // Güncelleme işlemleri burada uygulanacak
        Connection connection = getConnection();
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("UPDATE Users SET Name ='"+user.getName()+"', ContInfo='"+user.getContInfo()+"' WHERE UserID =" + user.getId());

            statement.close();
            connection.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void delete(Users user) {
        // Silme işlemleri burada uygulanacak
        Connection connection = getConnection();
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("DELETE FROM Users WHERE UserID =" + user.getId());
            statement.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Users> getList() {
        // Listeleme işlemleri burada uygulanacak
        List<Users> dataUser = new ArrayList<Users>();

        Connection connection = getConnection();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM Users");
            while (rs.next()) {
                Users user = new Users();
                user.setId(rs.getInt("UserID"));
                user.setName(rs.getString("Name"));
                user.setContInfo(rs.getString("ContInfo"));

                dataUser.add(user);
            }

            statement.close();
            connection.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return dataUser;
    }

    @Override
    public Users getById(int id) {
        // Tek bir kullanıcıyı id ile getirme işlemleri burada uygulanacak
        Users user = new Users();

        Connection connection = getConnection();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM Users WHERE UserID =" + id);
            while (rs.next()) {

                user.setId(rs.getInt("UserID"));
                user.setName(rs.getString("Name"));
                user.setContInfo(rs.getString("ContInfo"));
            }

            statement.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }

    public List<Users> getSearch(int id) {
        List<Users> dataUser = new ArrayList<Users>();

        Connection connection = getConnection();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM Users WHERE UserID LIKE '%" + id + "'");


            while (rs.next()) {
                Users user = new Users();

                user.setId(rs.getInt("UserID"));
                user.setName(rs.getString("Name"));
                user.setContInfo(rs.getString("ContInfo"));

                dataUser.add(user);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return dataUser;
    }
}
