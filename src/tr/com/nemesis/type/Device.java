package tr.com.nemesis.type;


import java.sql.Date;

public class Device {

    private int DeviceID;
    private String Type;
    private Date InstallationsDate;
    private String Status;

    public int getDeviceID() {
        return DeviceID;
    }

    public void setDeviceID(int deviceID) {
        DeviceID = deviceID;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public Date getInstallationsDate() {
        return InstallationsDate;
    }

    public void setInstallationsDate(Date installationsDate) {
        InstallationsDate = installationsDate;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }
}

