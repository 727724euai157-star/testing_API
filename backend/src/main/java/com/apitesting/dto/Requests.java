package com.apitesting.dto;
import jakarta.validation.constraints.*;
public final class Requests { private Requests(){} 
 public record Register(@NotBlank @Size(max=100) String name,@NotBlank @Email @Size(max=190) String email,@NotBlank @Size(min=8,max=100) String password){}
 public record Login(@NotBlank @Email String email,@NotBlank String password){}
 public record Update(@NotBlank @Size(max=100) String name,@NotBlank @Email @Size(max=190) String email){}
}
