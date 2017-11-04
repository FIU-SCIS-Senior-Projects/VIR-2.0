package com.vir.controller;

import java.security.Principal;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vir.exception.ApiError;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;

@RestController
@RequestMapping("/api")
@Api(tags = "user")
public class UserController {
	
	@ApiOperation(value = "Get the current user.", notes= "You always have to get authenticated first with /login"	)
	@ApiResponse(code = 400, message = "Generic error", response = ApiError.class)
	@GetMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
	public Principal user(Principal user) {
		return user;
	}
}
