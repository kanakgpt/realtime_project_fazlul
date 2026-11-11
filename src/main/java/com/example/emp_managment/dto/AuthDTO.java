package com.example.emp_managment.dto;

public class AuthDTO {

    public record RegisterRequest(String email, String password) {}

    public record LoginRequest(String email, String password) {}

    public record AuthResponse(String token) {}
}