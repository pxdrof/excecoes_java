package application;

import model.entities.Reservation;
import model.exceptions.DomainException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Program {
    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        try{
            System.out.print("Room number: ");
            int roomNumber = sc.nextInt();
            sc.nextLine(); // break line
            System.out.print("Check-in date (dd/MM/yyyy): ");
            Date checkIn = sdf.parse(sc.nextLine());
            System.out.print("Check-out date (dd/MM/yyyy): ");
            Date checkOut = sdf.parse(sc.nextLine());
            Reservation reservation = new Reservation(roomNumber, checkIn, checkOut);
            System.out.print(reservation);

            System.out.println("\n");
            System.out.print("Enter data to update the reservation:\n");
            System.out.print("Check-in date (dd/MM/yyyy): ");
            checkIn = sdf.parse(sc.nextLine());
            System.out.print("Check-out date (dd/MM/yyyy):  ");
            checkOut = sdf.parse(sc.nextLine());
            reservation.updateDates(checkIn, checkOut);
            System.out.print("Reservation: " + reservation);

        } catch (ParseException e){
            System.out.print("Invalid Date Format");

        } catch (DomainException d){
            System.out.print("Error in reservation: " + d.getMessage());

        } catch (RuntimeException e){
            System.out.print("Unexpected error");
        }
        sc.close();
    }
}
