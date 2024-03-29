package sg.com.smartinventory.security;

import java.util.Date;
import java.util.concurrent.TimeUnit;

// import jakarta.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
// import org.springframework.security.core.AuthenticationException;
// import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
// import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import sg.com.smartinventory.entities.User;

@Service
public class JwtService {
  private static final String JWT_SECRET_KEY = "mysecretkey";

  @Value("${jwt.session.period:3600000}")
  private long jwtSessionPeriod;

  // private final JwtParser jwtParser;

  private final String TOKEN_HEADER = "Authorization";
  private final String TOKEN_PREFIX = "Bearer ";

  // create Signing Key (create signature of JWT, ensure message wasnt changed)
  // public JwtService() {
  // this.jwtParser = Jwts
  // .parser()
  // .setSigningKey("mysecretkey");
  // }

  // Create JWT Token
  public String createToken(User user) {
    Claims claims = Jwts.claims().setSubject(user.getUsername());
    claims.put("userName", user.getUsername());
    claims.put("firstName", user.getFirstName());
    claims.put("lastName", user.getLastName());

    Date tokenCreateTime = new Date();
    Date tokenValidity = new Date(tokenCreateTime.getTime() + TimeUnit.MINUTES.toMillis(jwtSessionPeriod));

    // Builds JWT and returns it as a String.
    return Jwts.builder()
        .setClaims(claims)
        .setExpiration(tokenValidity)
        .signWith(SignatureAlgorithm.HS256, JWT_SECRET_KEY)
        .compact();
  }

}