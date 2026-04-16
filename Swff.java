
package com.mycompany.swff;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


// 📦 Model: Schedule
class Schedule {
    String train, route;
    int seats, booked;

    Schedule(String t, String r, int s) {
        train = t;
        route = r;
        seats = s;
        booked = 0;
    }
}

// 📦 Model: Reservation
class Reservation {
    String name, train;

    Reservation(String n, String t) {
        name = n;
        train = t;
    }
}



public class Swff {
static ArrayList<Schedule> schedules = new ArrayList<>();
    static ArrayList<Reservation> reservations = new ArrayList<>();
    public static void main(String[] args) {
        loginScreen();
    
    }
    
    // 🔐 LOGIN SCREEN
    static void loginScreen() {
        JFrame frame = new JFrame("Login");

        JTextField user = new JTextField();
        JPasswordField pass = new JPasswordField();
        JButton btn = new JButton("Login");

        frame.setLayout(new GridLayout(3, 2, 5, 5));

        frame.add(new JLabel("Username"));
        frame.add(user);
        frame.add(new JLabel("Password"));
        frame.add(pass);
        frame.add(new JLabel(""));
        frame.add(btn);

        btn.addActionListener(e -> {
            if (user.getText().equals("admin") &&
                String.valueOf(pass.getPassword()).equals("1234")) {

                frame.dispose();
                dashboard();
                
     } else {
                JOptionPane.showMessageDialog(frame, "❌ Wrong login");
            }
        });

        frame.setSize(300, 180);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    
    
    // 📊 DASHBOARD
    static void dashboard() {
        JFrame frame = new JFrame("Dashboard");

        JButton add = new JButton("➕ Add Schedule");
        JButton view = new JButton("📋 View Schedules");
        JButton reserve = new JButton("🎫 Reservation");
        JButton stats = new JButton("📊 View Stats");

        frame.setLayout(new GridLayout(4, 1, 10, 10));

        frame.add(add);
        frame.add(view);
        frame.add(reserve);
        frame.add(stats);

        add.addActionListener(e -> addSchedule());
        view.addActionListener(e -> viewSchedules());
        reserve.addActionListener(e -> makeReservation());
        stats.addActionListener(e -> viewStats());

        frame.setSize(350, 250);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    // ➕ ADD SCHEDULE
    static void addSchedule() {
        JTextField train = new JTextField();
        JTextField route = new JTextField();
        JTextField seats = new JTextField();

        Object[] fields = {
            "Train:", train,
            "Route:", route,
            "Seats:", seats
        };
    
    int option = JOptionPane.showConfirmDialog(null, fields,
                "Add Schedule", JOptionPane.OK_CANCEL_OPTION);

        if (option == JOptionPane.OK_OPTION) {
            try {
                int seatNum = Integer.parseInt(seats.getText());

                schedules.add(new Schedule(
                        train.getText(),
                        route.getText(),
                        seatNum
                ));

                JOptionPane.showMessageDialog(null, "✅ Schedule Added");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "❌ Invalid input");
            }
        }
    }
    
    // 📋 VIEW SCHEDULES
    static void viewSchedules() {
        if (schedules.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No schedules available");
            return;
        }

        String msg = "";

        for (int i = 0; i < schedules.size(); i++) {
            Schedule s = schedules.get(i);
            msg += i + ". " + s.train + " | " + s.route +
                   " | Seats: " + s.booked + "/" + s.seats + "\n";
        }

        JOptionPane.showMessageDialog(null, msg);
    }
    
    // 🎫 MAKE RESERVATION
    static void makeReservation() {
        if (schedules.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No schedules available");
            return;
        }

        String list = "";
        for (int i = 0; i < schedules.size(); i++) {
            Schedule s = schedules.get(i);
            list += i + ". " + s.train +
                    " (" + s.booked + "/" + s.seats + ")\n";
        }

        try {
            String indexStr = JOptionPane.showInputDialog(
                    list + "\nEnter schedule number:");
            
 int index = Integer.parseInt(indexStr);
            Schedule s = schedules.get(index);

            if (s.booked < s.seats) {
                String name = JOptionPane.showInputDialog("Passenger name:");

                s.booked++;
                reservations.add(new Reservation(name, s.train));

                JOptionPane.showMessageDialog(null, "✅ Booking successful");
            } else {
                JOptionPane.showMessageDialog(null, "❌ No seats available");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "❌ Invalid input");
        }
    }
    
    // 📊 STATS
    static void viewStats() {
        String msg = "Total Schedules: " + schedules.size() +
                     "\nTotal Reservations: " + reservations.size();

        JOptionPane.showMessageDialog(null, msg);
    }

}
