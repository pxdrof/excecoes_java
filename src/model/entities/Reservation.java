package model.entities;

import model.exceptions.DomainException;

import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.zip.DataFormatException;

public class Reservation {

    private Integer roomNumber;
    private Date checkIn;
    private Date checkOut;

    private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    public Reservation (Integer roomNumber, Date checkIn, Date checkOut) throws DomainException{
        if (!checkOut.after(checkIn)){
            throw new DomainException("Check-out date must be after check-i date");
        }
        this.roomNumber = roomNumber;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }

    public Integer getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(Integer roomNumber) {
        this.roomNumber = roomNumber;
    }

    public Date getCheckIn() {
        return checkIn;
    }

    public Date getCheck0ut() {
        return checkOut;
    }

    public long duration(){
        long diff = checkOut.getTime() - checkIn.getTime(); /* Calcula a diferença da data em milisegundos */
        return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS); /* converte de milisegundos para dia */
    }

    public void updateDates(Date checkIn, Date checkOut) throws DomainException{
        Date now = new Date();
        if (checkIn.before(now) || checkOut.before(now)){
            throw new DomainException("Reservation dates for update must be future dates");
        }
        if (!checkOut.after(checkIn)){
            throw new DomainException("Check-out date must be after check-i date");
        }
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }

    @Override
    public String toString(){
        return  "Reservation: Room "
                + roomNumber
                + ", check-in: "
                + sdf.format(checkIn)
                + ", check-out: "
                + sdf.format(checkOut)
                + ", "
                + duration()
                + " nights";
    }
}
