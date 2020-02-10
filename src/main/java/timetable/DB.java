package timetable;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class DB {
    public static Connection connection;
    public  static void connect(){
        connection=null;
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return;
        }
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres", "Vlad");
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public static void write(int number, String route, Timestamp time){
        PreparedStatement st=null;
        try {
            String sq="INSERT INTO timetable(number, time,route) "+"VALUES(?, ?, ? )";
            st = connection.prepareStatement(sq);
            st.setInt(1, number);
            st.setTimestamp(2,time);
            st.setString(3, route);
            st.executeUpdate();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public static Set<Train> read(){
        Set<Train> trains = new HashSet<>();
        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM timetable;");

            while(rs.next()){
                int name=rs.getInt("number");
                String route=rs.getString("route");
                Timestamp time=rs.getTimestamp("time");
                Train train =new Train(name,route,time);
                trains.add(train);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return trains;
    }
}
