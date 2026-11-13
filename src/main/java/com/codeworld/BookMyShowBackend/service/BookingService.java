package com.codeworld.BookMyShowBackend.service;


import com.codeworld.BookMyShowBackend.dto.BookingResponse;
import com.codeworld.BookMyShowBackend.dto.CreateBookingRequest;
import com.codeworld.BookMyShowBackend.entity.Booking;
import com.codeworld.BookMyShowBackend.entity.BookingStatus;
import com.codeworld.BookMyShowBackend.entity.Show;
import com.codeworld.BookMyShowBackend.entity.ShowSeat;
import com.codeworld.BookMyShowBackend.repository.BookingRepository;
import com.codeworld.BookMyShowBackend.repository.CustomerRepository;
import com.codeworld.BookMyShowBackend.repository.ShowRepository;
import com.codeworld.BookMyShowBackend.repository.ShowSeatRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Locale;


@Service
public class BookingService {

    private final ShowRepository  showRepository;

    private  final ShowSeatRepository  showSeatRepository;

    private final BookingRepository bookingRepository;

    private final CustomerRepository customerRepository;

    public BookingService(ShowRepository showRepository, ShowSeatRepository showSeatRepository, BookingRepository bookingRepository, CustomerRepository customerRepository) {
        this.showRepository = showRepository;
        this.showSeatRepository = showSeatRepository;
        this.bookingRepository = bookingRepository;
        this.customerRepository = customerRepository;
    }

    @Transactional
    public BookingResponse book(long showId, CreateBookingRequest request)
    {
     Show show =showRepository.findById(showId).orElseThrow(()->new ResourseNotFoundException("show not found"));
     var customer=customerRepository.findById(request.profileId())
             .orElseThrow(()-> new ResourseNotFoundException("Profile not found"));

        List<String> labels = request.SeatLables().stream()
                .map(lable -> lable.trim().toUpperCase(Locale.ROOT)).toList();

        if(labels.stream().distinct().count()!=labels.size())
        {
            throw  new SeatUnavailableException("Duplicate Seat labels are not allowed");
        }

        List<ShowSeat>seats=showSeatRepository.findForUpdate(showId,labels);
        if(seats.size()!=labels.size() || seats.stream().anyMatch(ShowSeat::isReserved))
        {
            throw  new SeatUnavailableException("One or more selected seats are unavailable");
        }
        seats.forEach(ShowSeat::reserved);
        show.reserved(labels.size());

        BigDecimal total=show.getTicketPrice().multiply( BigDecimal.valueOf(labels.size()));
        Booking booking = bookingRepository.save(new Booking(show, customer, total, labels));

        return BookingResponse.from(booking);
    }

    @Transactional
    public BookingResponse find(Long bookingId)
    {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new ResourseNotFoundException("Booking not found"));
        return BookingResponse.from(booking);
    }


    @Transactional
    public List<BookingResponse>findByProfileId(Long ProfileId)
    {
        if(!customerRepository.existsById(ProfileId))
        {
            throw  new ResourseNotFoundException("Profile not found");
        }
        List<BookingResponse> list = bookingRepository.findByCustomerIdOrderByBookAtDesc(ProfileId).stream()
                .map(BookingResponse::from).toList();
        return list;
    }

    @Transactional
    public BookingResponse cancel ( Long bookingId,Long profileId)
    {
        Booking booking = bookingRepository.findByIdAndCustomerId(bookingId, profileId)
                .orElseThrow(() -> new ResourseNotFoundException("Booking not found"));

        if(booking.getStatus()== BookingStatus.CONFIRMED)
        {
            List<ShowSeat> seats = showSeatRepository.findForUpdate(booking.getShow().getId(), booking.getSeatLabel());
            seats.forEach(ShowSeat::release);
            booking.getShow().release(booking.getSeatLabel().size());
            booking.cancel();
        }
        return BookingResponse.from(booking);
    }
}
