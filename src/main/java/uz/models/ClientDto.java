package uz.models;

/**
 * Created by Jack on 22.03.2019.
 */
public class ClientDto {
    private Long id;
    private String nameCompany;
    private String director;
    private String address;
    private String phone;
    private int payed;
    private Long clientId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameCompany() {
        return nameCompany;
    }

    public void setNameCompany(String nameCompany) {
        this.nameCompany = nameCompany;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
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

    @Override
    public String toString() {
        return nameCompany+"  "+director ;
    }

    public int isPayed() {
        return payed;
    }

    public void setPayed(int payed) {
        this.payed = payed;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }
}
