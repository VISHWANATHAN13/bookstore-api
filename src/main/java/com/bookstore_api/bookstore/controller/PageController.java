package com.bookstore_api.bookstore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PageController {

	@GetMapping("/")
	public List<String> homePage() {
        return List.of("backend, is, alive");
	}

}
