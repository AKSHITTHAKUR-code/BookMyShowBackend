package com.codeworld.BookMyShowBackend.controller;


import com.codeworld.BookMyShowBackend.dto.BookingResponse;
import com.codeworld.BookMyShowBackend.dto.CreateBookingRequest;
import com.codeworld.BookMyShowBackend.service.BookingService;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/booking")
public class BookingController {
    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }



    @PostMapping("/shows/{showId}")
    public BookingResponse book(@PathVariable Long showId, @RequestBody CreateBookingRequest request)
    {
        return bookingService.book(showId, request);

    }




    @PostMapping("{bookingId}/cancel")
    public BookingResponse cancel(@PathVariable Long bookingId, @RequestBody Long profileId )
    {
        return bookingService.cancel(bookingId, profileId);

    }

    @PostMapping("/{bookingId}")
    public BookingResponse find(@PathVariable Long bookingId )
    {
        return bookingService.find(bookingId);

    }


}
