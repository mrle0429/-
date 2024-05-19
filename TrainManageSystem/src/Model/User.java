package Model;

public class User {
    private String userId;
    private String userName;
    private Integer userAge;
    private String  userSex;
    private String phoneNumber;
    private String email;
    private String address;
    private String country;
    private String password;


    public User() {
    }

    public User(String userId, String userName, Integer userAge, String userSex, String phoneNumber, String email, String address, String country) {
        this.userId = userId;
        this.userName = userName;
        this.userAge = userAge;
        this.userSex = userSex;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
        this.country = country;
    }

    public User(String userId, String userName, Integer userAge, String userSex, String phoneNumber, String email, String address, String country, String password) {
        this.userId = userId;
        this.userName = userName;
        this.userAge = userAge;
        this.userSex = userSex;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
        this.country = country;
        this.password = password;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getUserAge() {
        return userAge;
    }

    public void setUserAge(Integer userAge) {
        this.userAge = userAge;
    }

    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
