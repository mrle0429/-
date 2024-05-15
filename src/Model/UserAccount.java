package Model;

public class UserAccount {
    private Integer cid;
    private String password;

    public UserAccount() {
    }

    public UserAccount(Integer cid,  String password) {
        this.cid = cid;
        this.password = password;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
