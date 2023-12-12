package MediTrack.Medi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.util.Date;

@Service
public class AuthenticationService {

    @Autowired
    private AuthenticationManager authenticationManager;
    // Generate a secure HS512 key
    static byte[] keyBytes = Keys.secretKeyFor(io.jsonwebtoken.SignatureAlgorithm.HS512).getEncoded();

    // Convert the keyBytes to a Base64-encoded string if needed
    static String base64Key = java.util.Base64.getEncoder().encodeToString(keyBytes);

    private static final String SECRET_KEY = base64Key;

    public String authenticate(String username, String password) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        return generateToken(authentication);
    }

    private String generateToken(Authentication authentication) {
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        return Jwts.builder()
                .setSubject(authentication.getName())
                .setIssuedAt(now)
                .setExpiration(new Date(nowMillis + 3600000)) // 1 hour expiration
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
    }
}
