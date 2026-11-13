package com.codeworld.BookMyShowBackend.controller;


import com.codeworld.BookMyShowBackend.dto.ShowResponse;
import com.codeworld.BookMyShowBackend.service.CatalogService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/show")
public class ShowController {

    private final CatalogService catalogService;

    public ShowController(CatalogService catalogService) {
        this.catalogService = catalogService;
    }


    @GetMapping
    public List<ShowResponse> shows(@RequestParam String city,
        @RequestParam LocalDate date)
    {
        return catalogService.shows(city, date);

    }
}
