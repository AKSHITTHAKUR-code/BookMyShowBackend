package com.codeworld.BookMyShowBackend.entity;


import jakarta.persistence.*;


import java.math.BigDecimal;
import java.time.LocalDateTime;


@Entity
@Table(name="shows")
public class Show {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    private Movie  movie;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    private Theater theater;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private BigDecimal ticketPrice;


    private int totalSeats;

    private int availableSeats;

    private boolean active=true;

    @Version
    private long version;


    public Show(Movie movie, Theater theater, LocalDateTime startTime, LocalDateTime endTime, int totalSeats) {
        this.movie = movie;
        this.theater = theater;
        this.startTime = startTime;
        this.endTime = endTime;
        this.ticketPrice=ticketPrice;

        this.totalSeats = totalSeats;
        this.availableSeats = availableSeats;
    }


    public Show() {

    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Theater getTheater() {
        return theater;
    }

    public void setTheater(Theater theater) {
        this.theater = theater;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public BigDecimal getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(BigDecimal ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public void setTotalSeats(int totalSeats) {
        this.totalSeats = totalSeats;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }



    public  void reserved(int seats)
    {
        if(seats<0||seats>availableSeats)
        {
            throw new IllegalArgumentException("Not enough seats Available");
        }
        availableSeats-=seats;
    }

    public void release(int seats)
    {
        availableSeats=Math.min(totalSeats,availableSeats+seats);
    }
}
