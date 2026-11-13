package com.codeworld.BookMyShowBackend.dto;

import com.codeworld.BookMyShowBackend.entity.Booking;
import com.codeworld.BookMyShowBackend.entity.BookingStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record BookingResponse(Long Id , Long ShowId, Long ProfileId, String MovieTitle, String theaterName,
                              String customerName, String customerEmail, String customerPhone, List<String>seatLables,
                              BigDecimal bookingPrice , BookingStatus status, LocalDateTime bookAt) {



    public static BookingResponse from(Booking booking)
    {
        return new BookingResponse(booking.getId(),booking.getShow().getId(),
                booking.getCustomer()==null? null: booking.getCustomer().getId(),booking.getShow().getMovie().getTitle(),
                booking.getShow().getTheater().getName(),booking.getCustomerName(),booking.getCustomerEmail(),
                booking.getCustomerPhone(),booking.getSeatLabel(),booking.getBookingPrice(),booking.getStatus(),booking.getBookAt());
    }



}

