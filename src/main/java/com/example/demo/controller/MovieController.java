package com.example.demo.controller;

import com.example.demo.model.Movie;
import com.example.demo.services.MovieService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;
import java.util.Map;

@Controller
@RequestMapping("/")
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/movie")
    public Movie findByTitle(@RequestParam String title) {
        return movieService.findByTitle(title);
    }
    @GetMapping("/movies")
    public Collection<Movie> findByTitleLike(@RequestParam String title) {
        return movieService.findByTitleLike(title);
    }

    @GetMapping("/graph")
    public Map<String, Object> graph(@RequestParam(value = "limit",required = false) Integer limit) {
        return movieService.graph(limit == null ? 100 : limit);
    }





}
