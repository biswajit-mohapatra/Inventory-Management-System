package com.gl.microservices.poc.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gl.microservices.poc.util.URI;

@RestController
@RequestMapping(value = URI.HEARTBEAT)
public class HeartbeatController {

	@GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
	public String heartbeat() {
		return "OK";
	}
}
