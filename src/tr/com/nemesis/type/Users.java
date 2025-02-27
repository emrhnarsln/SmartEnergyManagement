package tr.com.nemesis.type;

public class Users extends Device {
    private  int id;
    private String name;
    private String contInfo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContInfo() {
        return contInfo;
    }

    public void setContInfo(String contInfo) {
        this.contInfo = contInfo;
    }

    public String toString() {

        return id + "           " + name;
    }
}
