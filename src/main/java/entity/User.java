package entity;

public class User extends BaseEntity {
    private String username;
    private String password;
    private String role;

    public User(int id, String username, String password, String role) {
        super(id);
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public User(String username, String password, String role) {
        this(0, username, password, role);
    }

    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public String getRole() { return role; }
}
