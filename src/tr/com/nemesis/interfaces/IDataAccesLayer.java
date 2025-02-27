package tr.com.nemesis.interfaces;

import java.util.List;

public interface IDataAccesLayer<T> {

    public void insert(T variable);
    public void update(T variable);
    public void delete(T variable);
    public List<T> getList();
    public  T getById(int id);
}
