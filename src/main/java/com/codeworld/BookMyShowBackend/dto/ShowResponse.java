package com.codeworld.BookMyShowBackend.dto;

import com.codeworld.BookMyShowBackend.entity.Show;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record ShowResponse(
        Long id,
        MovieResponse movie,
         TheaterResponse theater,
        LocalDateTime startTime,
        LocalDateTime endTime,
        BigDecimal price,
        int toatalSeats,
        int availableSeats,
        List<String> availableSeatsLables
                           )

{
    public static ShowResponse from (Show show, List<String> availableSeatsLables)
    {
        return  new ShowResponse(
                show.getId(),MovieResponse.from(show.getMovie()),
                TheaterResponse.from(show.getTheater()),
                show.getStartTime(),
                show.getEndTime(),
                show.getTicketPrice(),
                show.getTotalSeats(),
                show.getAvailableSeats(),
                availableSeatsLables);
    }
}
