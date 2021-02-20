import java.sql.*;
import java.util.ArrayList;

public class KaroraDB {

    Connection conn;

    public KaroraDB() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/karorak";
        String user = "root";
        String passw = "";

        conn = DriverManager.getConnection(url, user, passw);
    }

    public ArrayList<Karora> getKarorak() throws SQLException {
        ArrayList<Karora> karorak = new ArrayList<>();
        Statement st = conn.createStatement();

        ResultSet result = st.executeQuery("SELECT * FROM karorak");
        while (result.next()) {
            int id = result.getInt("id");
            String marka = result.getString("marka");
            String modell = result.getString("modell");
            String szin = result.getString("szin");
            int ar = result.getInt("ar");

            Karora k = new Karora(id, marka, modell, szin, ar);

            karorak.add(k);


        }

        return karorak;
    }

    public int deleteKarora(int id) throws SQLException {
        PreparedStatement st = conn.prepareStatement("DELETE FROM karorak WHERE id = ?");
        st.setInt(1, id);
        return st.executeUpdate();
    }

    public int insertKarora(String marka, String modell, String szin, int ar) throws SQLException {
        String sql = "INSERT INTO karorak(marka,modell,szin,ar) VALUES(?,?,?,?)";
        PreparedStatement st = conn.prepareStatement(sql);
        st.setString(1, marka);
        st.setString(2, modell);
        st.setString(3, szin);
        st.setInt(4, ar);
        return st.executeUpdate();
    }

    public int updateKarora(int id, String marka, String modell, String szin, int ar) throws SQLException {
        String sql = "UPDATE karorak SET marka = ?, modell = ?, szin = ?, ar = ? WHERE id = ?";
        PreparedStatement st = conn.prepareStatement(sql);
        st.setString(1, marka);
        st.setString(2, modell);
        st.setString(3, szin);
        st.setInt(4, ar);
        st.setInt(5, id);
        return st.executeUpdate();
    }
}
