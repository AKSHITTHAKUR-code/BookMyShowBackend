package com.codeworld.BookMyShowBackend.controller;


import com.codeworld.BookMyShowBackend.dto.TheaterResponse;
import com.codeworld.BookMyShowBackend.service.CatalogService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/theaters")
public class TheaterController {

    private final CatalogService catalogService;

    public TheaterController(CatalogService catalogService) {
        this.catalogService = catalogService;
    }

    @GetMapping
    public List<TheaterResponse> theaters(@RequestParam String city)
    {
        return catalogService.theaters(city);
    }
}
