package com.apitesting.entity;
import jakarta.persistence.*;
import java.time.Instant;
@Entity @Table(name="users", uniqueConstraints=@UniqueConstraint(columnNames="email"))
public class User {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @Column(nullable=false) private String name;
 @Column(nullable=false, unique=true) private String email;
 @Column(nullable=false) private String password;
 @Enumerated(EnumType.STRING) @Column(nullable=false) private Role role;
 @Column(name="created_at",nullable=false,updatable=false) private Instant createdAt;
 @Column(name="updated_at",nullable=false) private Instant updatedAt;
 @PrePersist void create(){createdAt=updatedAt=Instant.now();} @PreUpdate void update(){updatedAt=Instant.now();}
 protected User() {}
 public User(String n,String e,String p,Role r){name=n;email=e;password=p;role=r;}
 public Long getId(){return id;} public String getName(){return name;} public void setName(String v){name=v;}
 public String getEmail(){return email;} public void setEmail(String v){email=v;} public String getPassword(){return password;}
 public Role getRole(){return role;} public void setRole(Role v){role=v;} public Instant getCreatedAt(){return createdAt;} public Instant getUpdatedAt(){return updatedAt;}
}
