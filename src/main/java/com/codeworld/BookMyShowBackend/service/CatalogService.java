package com.codeworld.BookMyShowBackend.service;


import com.codeworld.BookMyShowBackend.dto.MovieResponse;
import com.codeworld.BookMyShowBackend.dto.ShowResponse;
import com.codeworld.BookMyShowBackend.dto.TheaterResponse;
import com.codeworld.BookMyShowBackend.repository.MovieRepository;
import com.codeworld.BookMyShowBackend.repository.ShowRepository;
import com.codeworld.BookMyShowBackend.repository.ShowSeatRepository;
import com.codeworld.BookMyShowBackend.repository.TheaterRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class CatalogService {


    private final MovieRepository movieRepository;
    private final TheaterRepository theaterRepository;
    private final ShowRepository showRepository;
    private  final ShowSeatRepository showSeatRepository;


    public CatalogService(MovieRepository movieRepository, TheaterRepository theaterRepository, ShowRepository showRepository, ShowSeatRepository showSeatRepository) {
        this.movieRepository = movieRepository;
        this.theaterRepository = theaterRepository;
        this.showRepository = showRepository;
        this.showSeatRepository = showSeatRepository;
    }



    public List<MovieResponse> movies()
    {
        return movieRepository.findByActiveTrueOrderByTitle().stream().map(MovieResponse::from).toList();

    }

    public List<TheaterResponse>theaters(String city)
    {
        return theaterRepository.findByCityIgnoreCaseOrderByName(city).stream().map(TheaterResponse::from).toList();
    }

    public List<ShowResponse>shows(String city, LocalDate date)
    {
        LocalDateTime from = date.atStartOfDay();
        return showRepository.findActiveShow(city,from,from.plusDays(1)).stream()
                .map(show ->ShowResponse.from(show,showSeatRepository.findAvailableLabels(show.getId()))).toList();
    }
}
