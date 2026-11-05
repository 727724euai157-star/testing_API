package com.apitesting.service;
import com.apitesting.dto.Requests; import com.apitesting.entity.*; import com.apitesting.repository.UserRepository; import org.springframework.http.HttpStatus; import org.springframework.security.crypto.password.PasswordEncoder; import org.springframework.stereotype.Service; import org.springframework.web.server.ResponseStatusException;
@Service public class UserService {
 private final UserRepository repo; private final PasswordEncoder encoder; public UserService(UserRepository r,PasswordEncoder e){repo=r;encoder=e;}
 public User register(Requests.Register r){if(repo.existsByEmail(r.email()))throw new ResponseStatusException(HttpStatus.CONFLICT,"Email already registered");return repo.save(new User(r.name(),r.email(),encoder.encode(r.password()),Role.CUSTOMER));}
 public User login(Requests.Login r){User u=repo.findByEmail(r.email()).orElseThrow(()->new ResponseStatusException(HttpStatus.UNAUTHORIZED,"Invalid credentials"));if(!encoder.matches(r.password(),u.getPassword()))throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"Invalid credentials");return u;}
 public User get(long id){return repo.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"User not found"));}
 public User update(long id,Requests.Update r){User u=get(id);if(!u.getEmail().equals(r.email())&&repo.existsByEmail(r.email()))throw new ResponseStatusException(HttpStatus.CONFLICT,"Email already registered");u.setName(r.name());u.setEmail(r.email());return repo.save(u);}
 public void delete(long id){if(!repo.existsById(id))throw new ResponseStatusException(HttpStatus.NOT_FOUND,"User not found");repo.deleteById(id);}
}
