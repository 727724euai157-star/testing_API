package com.apitesting.security;
import com.apitesting.entity.User; import io.jsonwebtoken.Jwts; import io.jsonwebtoken.security.Keys; import org.springframework.beans.factory.annotation.Value; import org.springframework.stereotype.Service; import java.nio.charset.StandardCharsets; import java.util.Date;
@Service public class JwtService {
 private final String secret; private final long expiration;
 public JwtService(@Value("${app.jwt.secret}") String secret,@Value("${app.jwt.expiration-ms}") long expiration){this.secret=secret;this.expiration=expiration;if(secret.getBytes(StandardCharsets.UTF_8).length<32)throw new IllegalArgumentException("JWT_SECRET must be at least 32 bytes");}
 private javax.crypto.SecretKey key(){return Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));}
 public String create(User u){long now=System.currentTimeMillis();return Jwts.builder().subject(u.getEmail()).claim("role",u.getRole().name()).issuedAt(new Date(now)).expiration(new Date(now+expiration)).signWith(key()).compact();}
 public String subject(String token){return Jwts.parser().verifyWith(key()).build().parseSignedClaims(token).getPayload().getSubject();}
}
