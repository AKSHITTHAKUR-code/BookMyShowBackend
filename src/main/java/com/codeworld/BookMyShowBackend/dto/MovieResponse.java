package com.codeworld.BookMyShowBackend.dto;

import com.codeworld.BookMyShowBackend.entity.Movie;

public record MovieResponse(
                            Long id,
                            String tittle,
                            String Language,
                            String genre,
                            Integer DurationMinutes,
                            String certificate,
                            String posterUrl,
                            String trailerUrl )

{
    public static MovieResponse from (Movie movie)
    {
        return new MovieResponse(movie.getId(),
                movie.getTitle(),
                movie.getLanguage(),
                movie.getGenre(),
                movie.getDurationMinutes(),
                movie.getCertificate(),
                movie.getPosterUrl(),
                movie.getTrailerUrl()
        );

    }
}
