import java.sql.*;
import java.util.Scanner;


public class HotelReservationSystem {

    public static final String url = "jdbc:mysql://localhost:3306/hotel_db";
    public static final String username ="root";
    public static final String password ="root";

    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }

        try {
            Connection connection = DriverManager.getConnection(url,username,password);
            while(true){
                System.out.println();
                System.out.println("HOTEL RESERVATION SYSTEM");
                Scanner sc = new Scanner(System.in);
                System.out.println("1. Reserve a Room");
                System.out.println("2. View Reservation");
                System.out.println("3. Get Room Number");
                System.out.println("4. Update Reservation");
                System.out.println("5. Delete Reservation");
                System.out.println("6. Exit");
                System.out.println("Choose an option");
                int choice = sc.nextInt();
                switch(choice){
                    case 1-> reserveRoom(connection,sc);
                    case 2-> viewReservation(connection);
                    case 3-> getRoomNumber();
                    case 4-> updateReservation();
                    case 5-> deleteReservation();
                    case 0 -> {
                        exit();
                        sc.close();
                    }
                    default -> System.out.println("Invalid choice. Try again.");
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void reserveRoom(Connection connection, Scanner scanner) {
        try{
            System.out.println("Enter guest name");
            String guestName = scanner.next();
            scanner.nextLine();
            System.out.println("Enter room number");
            int roomNumber = scanner.nextInt();
            System.out.println("Enter contact number");
            String contactNumber = scanner.next();

            String query = "Insert into reservations (guest_name, room_number, contact_number) values (?,?,?)";

            try(PreparedStatement ps = connection.prepareStatement(query)){
                ps.setString(1,guestName);
                ps.setInt(2,roomNumber);
                ps.setString(3,contactNumber);

                int rowsAffected = ps.executeUpdate();
                if(rowsAffected>0){
                    System.out.println("Reservation Successful");
                }else{
                    System.out.println("Reservation Failed");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void viewReservation(Connection connection) throws SQLException {
        String query = "Select reservation_id, guest_name, room_number, contact_number, reservation_date from reservations";
        //used 'try-with-resources' statement below
        try(Statement statement = connection.prepareStatement(query);
            ResultSet rs = statement.executeQuery(query)){
            System.out.println("Current Reservations:");
            System.out.println("+----------------+-----------------+---------------+----------------------+-------------------------+");
            System.out.println("| Reservation ID | Guest           | Room Number   | Contact Number       | Reservation Date        |");
            System.out.println("+----------------+-----------------+---------------+----------------------+-------------------------+");

            while(rs.next()){
                int reservationId = rs.getInt("reservation_id");
                String guestName = rs.getString("guest_name");
                int roomNumber = rs.getInt("room_number");
                String contactNumber =rs.getString("contact_number");
                String reservationDate = rs.getString("reservation_date");

                //Table data
                System.out.printf("| %-14d | %-15s |  %-13d | %-20s | %-19s |\n",
                        reservationId,guestName,roomNumber,contactNumber,reservationDate);
            }
                System.out.println("+----------------+-----------------+---------------+----------------------+-------------------------+");
        }
    }

    private static void getRoomNumber() {
    }

    private static void updateReservation() {
    }

    private static void deleteReservation() {
    }

    private static void exit() {
    }


}
