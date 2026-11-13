package com.codeworld.BookMyShowBackend.entity;


import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="bookings")
public class Booking {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    private Show show;

    @ManyToOne(fetch = FetchType.LAZY)

    private Customer customer;

    private String customerName;

    private String  customerEmail;
    private String  customerPhone;
    private BigDecimal bookingPrice;

    private LocalDateTime bookAt;




    @Enumerated(EnumType.STRING)
    private BookingStatus Status;
    @ElementCollection
    @CollectionTable(
            name="booking_seats",
            joinColumns = @JoinColumn(name="booking_id")
    )



    @Column(name="seat_label",nullable=false)
    public List<String> seatLabel=new ArrayList<>();


    public Booking()
    {

     }

    public Booking(Show show, String coustomerName, String customerEmail,String customerPhone, BigDecimal bookingPrice, List<String> seatLabel) {
        this.show = show;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.customerPhone = customerPhone;
        this.bookingPrice = bookingPrice;
        this.bookAt=LocalDateTime.now();
        this.seatLabel = new ArrayList<>(seatLabel);
       this. Status = BookingStatus.CONFIRMED;
    }

    public Booking(Show show, Customer customer,BigDecimal bookingPrice,List<String> seatLabel)
    {
        this(show,customer.getName(),customer.getEmail(),customer.getPhoneNumber(),bookingPrice,seatLabel);
 this.customer=customer;
    }



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Show getShow() {
        return show;
    }

    public void setShow(Show show) {
        this.show = show;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }


    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public BigDecimal getBookingPrice() {
        return bookingPrice;
    }

    public void setBookingPrice(BigDecimal bookingPrice) {
        this.bookingPrice = bookingPrice;
    }

    public LocalDateTime getBookAt() {
        return bookAt;
    }

    public void setBookAt(LocalDateTime bookAt) {
        this.bookAt = bookAt;
    }

    public BookingStatus getStatus() {
        return Status;
    }

    public void setStatus(BookingStatus status) {
        Status = status;
    }

    public List<String> getSeatLabel() {
        return seatLabel;
    }

    public void setSeatLabel(List<String> seatLabel) {
        this.seatLabel = seatLabel;
    }

    public void cancel() {
        BookingStatus status = BookingStatus.CANCELLED;
    }
}

