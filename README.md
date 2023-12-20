# Hotel Reservation System 🏨

The Hotel Reservation System is a Java-based application designed to manage hotel reservations efficiently. 
This system provides a set of features to handle reservations, view existing bookings, retrieve room numbers, update guest information, and delete reservations.

## Key Features 🌟

- **Reservation Management**: Reserve hotel rooms by entering guest details, room numbers, and contact numbers.

- **View Reservations**: Access a list of current reservations, including reservation ID, guest name, room number, contact number, and reservation date.

- **Retrieve Room Numbers**: Get room numbers based on reservation ID and guest names.

- **Update Reservations**: Modify existing reservations with new guest details, room numbers, and contact numbers.

- **Delete Reservations**: Remove reservations by specifying the reservation ID.

## Usage 🛠️

The Hotel Reservation System is designed to be user-friendly and straightforward. Follow these steps to utilize the system efficiently:

1. **Run the Application**: Launch the application by running the `HotelReservationSystem.java` file in your preferred Java IDE.

2. **Main Menu Options**:
    - **Reserve a Room (Option 1)**: To make a new reservation, provide the guest's name, preferred room number, and contact number.
    - **View Reservations (Option 2)**: Access a detailed list of existing reservations, including reservation ID, guest name, room number, contact number, and reservation date.
    - **Get Room Number (Option 3)**: Retrieve the room number by entering the reservation ID and guest name.
    - **Update Reservation (Option 4)**: Modify an existing reservation by entering the reservation ID and providing updated guest details, room number, and contact number.
    - **Delete Reservation (Option 5)**: Remove a reservation by specifying the reservation ID.
    - **Exit (Option 0)**: Close the application.

3. **Follow Prompts**: Once you choose an option from the menu, the system will guide you through the necessary steps. Pay attention to the prompts and provide the requested information.

4. **Enjoy the Features**: Explore the various features of the system to manage hotel reservations efficiently.

Feel free to navigate through the options and interact with the system to make, view, update, or delete reservations based on your requirements.

## Database Configuration ⚙️

The application uses MySQL as the database. Configure the database connection in the `HotelReservationSystem.java` file by updating the `url`, `username`, and `password` variables.

```java
public static final String url = "jdbc:mysql://localhost:3306/hotel_db";
public static final String username = "your-username";
public static final String password = "your-password";
```

## Getting Started 🚀

1. **Clone the Repository**:

    ```bash
    git clone https://github.com/your-username/hotel-reservation-system.git
    ```

2. **Set Up the MySQL Database**:

    - Import the `hotel_db.sql` file located in the `database` directory.

3. **Open the Project in your Java IDE**:

    - Navigate to the project directory and open it in your preferred Java Integrated Development Environment (IDE).

4. **Run the Application**:

    - Locate the `HotelReservationSystem.java` file.
    - Run the file to launch the Hotel Reservation System application.

Now you're all set! You can start exploring and utilizing the features of the Hotel Reservation System.

Enjoy managing hotel reservations with our system!

## *👤 Author: Vaibhav Kubal*
