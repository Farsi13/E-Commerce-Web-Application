package bd.edu.seu.ecommerce.model;
import jakarta.persistence.*;

@Entity
public class Checkout {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    private String name;
    private String address;
    private String postalCode;
    private String city;
    private String phone;
    private String email;
    private double price;
    @Column(length = 1000)
    private String purchasingProductId;
    @Column(length = 1000)
    private String purchasingProductName;

    public String getPurchasingProductId() {
        return purchasingProductId;
    }

    public void setPurchasingProductId(String purchasingProductId) {
        this.purchasingProductId = purchasingProductId;
    }

    public String getPurchasingProductName() {
        return purchasingProductName;
    }

    public void setPurchasingProductName(String purchasingProductName) {
        this.purchasingProductName = purchasingProductName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
