package com.apitesting.controller;
import com.apitesting.dto.*;import com.apitesting.entity.User;import com.apitesting.service.UserService;import jakarta.validation.Valid;import org.springframework.http.HttpStatus;import org.springframework.security.core.Authentication;import org.springframework.security.core.GrantedAuthority;import org.springframework.web.bind.annotation.*;
@RestController @RequestMapping("/api/users") public class UserController {private final UserService service;public UserController(UserService s){service=s;}
 private User authorize(long id,Authentication a){User u=service.get(id);boolean admin=a.getAuthorities().stream().map(GrantedAuthority::getAuthority).anyMatch("ROLE_ADMIN"::equals);if(!admin&&!u.getEmail().equals(a.getName()))throw new org.springframework.security.access.AccessDeniedException("You may access only your own profile");return u;}
 @GetMapping("/{id}") public Responses.UserView get(@PathVariable long id,Authentication a){return Responses.UserView.from(authorize(id,a));}
 @PutMapping("/{id}") public Responses.UserView update(@PathVariable long id,@Valid @RequestBody Requests.Update r,Authentication a){authorize(id,a);return Responses.UserView.from(service.update(id,r));}
 @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT) public void delete(@PathVariable long id,Authentication a){if(a.getAuthorities().stream().noneMatch(x->x.getAuthority().equals("ROLE_ADMIN")))throw new org.springframework.security.access.AccessDeniedException("Administrator role required");service.delete(id);}
}
