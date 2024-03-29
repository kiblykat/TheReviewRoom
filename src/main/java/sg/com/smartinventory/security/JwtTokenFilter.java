package sg.com.smartinventory.security;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import sg.com.smartinventory.exceptions.ErrorResponse;

@Component
public class JwtTokenFilter extends OncePerRequestFilter {

  private JwtService jwtService;

  public JwtTokenFilter(JwtService jwtService) {
    this.jwtService = jwtService;
  }

  @Override
  protected void doFilterInternal(
      HttpServletRequest request,
      HttpServletResponse response,
      FilterChain filterChain) throws ServletException, IOException {

    try {
      String accessToken = jwtService.resolveToken(request);
      if (accessToken == null) {
        filterChain.doFilter(request, response);
        return;
      }

      Claims claims = jwtService.resolveClaims(request);

      if (claims != null & jwtService.validateClaims(claims)) {
        String email = claims.getSubject();

        Authentication authentication = new UsernamePasswordAuthenticationToken(email, "", new ArrayList<>());
        SecurityContextHolder.getContext().setAuthentication(authentication);
      }

    } catch (Exception ex) {
      response.setStatus(HttpStatus.FORBIDDEN.value());
      response.getWriter().write(new ErrorResponse("Authentication failure", LocalDateTime.now()).toString());
      response.setContentType(MediaType.APPLICATION_JSON_VALUE);

    }
    filterChain.doFilter(request, response);

  }
}
