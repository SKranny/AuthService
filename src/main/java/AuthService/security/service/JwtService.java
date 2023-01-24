package AuthService.security.service;

import AuthService.security.PersonDetails;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static io.jsonwebtoken.SignatureAlgorithm.HS512;

@Service
@Slf4j
public class JwtService {
    @Value("${jwt.secret-code}")
    private String secretKey;

    @Value("${jwt.life-time}")
    private Long lifeTime;

    public String generateJwtToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        PersonDetails personDetails = (PersonDetails) userDetails;

        claims.put("id", personDetails.getId());
        claims.put("email", personDetails.getEmail());
        claims.put("roles", personDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toSet()));

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(personDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + lifeTime))
                .signWith(HS512, secretKey)
                .compact();
    }
}
