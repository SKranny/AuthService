package AuthService.security.service;

import AuthService.security.PersonDetails;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static io.jsonwebtoken.SignatureAlgorithm.HS512;

@Service
@Slf4j
public class JwtService {
    @Value("${jwt.secret-code}")
    private String secretKey;

    @Value("${jwt.life-time}")
    private Long lifeTime;

    //Todo: move to redis
    Set<String> blackList = new HashSet<>();

    public String generateJwtToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        PersonDetails personDetails = (PersonDetails) userDetails;

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

    public String getUserNameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    public Set<String> getRolesFromToken(String token) {
        return getClaimFromToken(token, (Function<Claims, Set<String>>) claims -> claims.get("roles", Set.class));
    }

    public String getEmailFromToken(String token) {
        return getClaimFromToken(token, claims -> claims.get("email", String.class));
    }

    private <T> T getClaimFromToken(String token, Function<Claims, T> claimResolver) {
        return claimResolver.apply(getAllClaimsFromToken(token));
    }

    @SneakyThrows
    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
    }

    public void blockToken(String token) {
        blackList.add(token);
    }

    public boolean isBlockedToken(String token) {
        return blackList.contains(token);
    }
}
