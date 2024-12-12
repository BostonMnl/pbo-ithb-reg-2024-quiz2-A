package model.classes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Users {
    private int id;
    private String name;
    private String email;
    private String password;

    public Users() {

    }

    public Users(int id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    //0 Gagal, 1 Berhasil, 2 Email benar password salah
    public static int login(String emailInput, String pass) {
        String query = "SELECT * FROM users WHERE email = ? AND password = ?";

        try (Connection con = ConnectionManager.getConnection();
                PreparedStatement st = con.prepareStatement(query)) {

            st.setString(1, emailInput);
            st.setString(2, pass);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    System.out.println("hehe");
                    return 1;
                } else {
                    System.out.println("not hehe");
                    return 0;
                }
            }
        } catch (Exception ex) {
            System.out.println("Terjadi kesalahan: " + ex.getMessage());
            return 0;
        }
    }

    public static Users getData(String emails, String pass) {
        Users user = null;
        String query = "SELECT * FROM users WHERE email = ? AND password = ?";

        try (Connection con = ConnectionManager.getConnection();
                PreparedStatement st = con.prepareStatement(query)) {

            st.setString(1, emails);
            st.setString(2, pass);

            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    user = new Users();
                    user.setId(rs.getInt("id"));
                    user.setName(rs.getString("name"));
                    user.setEmail(rs.getString("email"));
                    user.setPassword(null);
                }
            }
        } catch (Exception ex) {
            System.out.println("Terjadi kesalahan saat mengambil data: " + ex.getMessage());
        }

        return user;
    }

}
