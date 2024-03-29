package sg.com.smartinventory.security;

import java.util.Base64;
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

  private final JwtParser jwtParser;

  private final String TOKEN_HEADER = "Authorization";
  private final String TOKEN_PREFIX = "Bearer ";

  String encodedString = Base64.getEncoder().encodeToString(JWT_SECRET_KEY.getBytes());

  // Create Signing Key (create signature of JWT, ensure message wasn't changed).
  // This method expects the string argument to be a Base64-encoded secret key
  // byte array. It does not assume a general string, like a user password for
  // example, as the signing key. JJWT assumes Base64 encoding because if you're
  // specifying a string password that is not Base64-encoded, you are considered
  // using a poorly formed or weak key. This uses older deprecated method, with
  // dependance on the older jaxb-api. It should be eventually replaced with the
  // updated secure method instead. JJWT provides a utility to help you generate
  // sufficient secure-random keys suitable for spec-compliant signing via the
  // io.jsonwebtoken.security.Keys class's secretKeyFor method. Note that a Base64
  // string is not considered safe to show to anyone. Base64 encoding is not
  // encryption - the value still needs to be kept secret. How you do this is up
  // to you (encrypt it, etc).
  public JwtService() {
    this.jwtParser = Jwts
        .parser()
        // Should not be a string, but a Base64-encoded secret key byte array.
        .setSigningKey(encodedString);
  }

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