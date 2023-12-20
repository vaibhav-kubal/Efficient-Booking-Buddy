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
                    case 3-> getRoomNumber(connection, sc);
                    case 4-> updateReservation(connection, sc);
                    //case 5-> deleteReservation();
                    case 0 -> {
                        //exit();
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
                System.out.printf("| %-14d | %-15s |  %-12d | %-20s | %-23s |\n",
                        reservationId,guestName,roomNumber,contactNumber,reservationDate);
            }
                System.out.println("+----------------+-----------------+---------------+----------------------+-------------------------+");
        }
    }

    private static void getRoomNumber(Connection connection, Scanner scanner) {
        try{
            System.out.println("Enter Reservation ID");
            int reservationId = scanner.nextInt();
            System.out.println("Enter Guest Name");
            String guestName = scanner.next();

            String query = "SELECT room_number from reservations where reservation_id = "+
                    reservationId + " and guest_name = '" + guestName + "'";

            try(Statement statement = connection.createStatement();
                ResultSet rs = statement.executeQuery(query)){
                    if(rs.next()){
                        int roomNumber = rs.getInt("room_number");
                        System.out.println("Room number for Reservation ID: "+
                                reservationId + " and guest " + guestName + " is " + roomNumber);
                    }else{
                        System.out.println("Reservation not found for given ID and guest name");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private static void updateReservation(Connection connection, Scanner scanner) {
        try{
            System.out.println("Enter reservation ID");
            int reservationID = scanner.nextInt();
            scanner.nextLine();
            if(!reservationExists(connection, reservationID)){
                System.out.println("Reservation not found for given Reservation ID");
                return;
            }

            System.out.println("Enter new guest name");
            String newGuestName = scanner.next();
            System.out.println("Enter new room number");
            int newRoomNumber = scanner.nextInt();
            System.out.println("Enter new contact number");
            String newContactNumber = scanner.next();

            String query = "UPDATE reservations set guest_name = '" + newGuestName +
                    "', room_number = " + newRoomNumber + ", contact_number = '" + newContactNumber +
                    "' where reservation_id = " + reservationID;
            try(Statement statement= connection.createStatement()){
                    int affectedRows = statement.executeUpdate(query);

                if(affectedRows>0){
                    System.out.println("Reservation Updated Successfully");
                }else{
                    System.out.println("Reservation failed, Try Again");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static boolean reservationExists(Connection connection, int reservationID) {
        try{
            String query = "Select reservation_id from reservations where reservation_id = " +
                    reservationID;

            try(Statement statement = connection.createStatement();
                        ResultSet rs = statement.executeQuery(query)){

                return rs.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
//
//    private static void deleteReservation() {
//    }
//
//    private static void exit() {
//    }


}
