package com.apitesting.dto;
import com.apitesting.entity.User; import java.time.Instant;
public final class Responses { private Responses(){}
 public record UserView(Long id,String name,String email,String role,Instant createdAt,Instant updatedAt){public static UserView from(User u){return new UserView(u.getId(),u.getName(),u.getEmail(),u.getRole().name(),u.getCreatedAt(),u.getUpdatedAt());}}
 public record Token(String accessToken,String tokenType){public Token(String t){this(t,"Bearer");}}
 public record Error(String error,String message,int status,Instant timestamp){}
}
