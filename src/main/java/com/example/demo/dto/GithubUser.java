package com.example.demo.dto;

import lombok.Data;

@Data
public class GithubUser {
    private String name;
    private String login;
    private Long id;
    private String bio;
}
