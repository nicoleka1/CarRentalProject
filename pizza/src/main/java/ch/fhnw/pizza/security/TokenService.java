package ch.fhnw.pizza.security;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.stream.Collectors;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

@Service
public class TokenService {

    //use the encoder bean from SecurityConfig
    private final JwtEncoder encoder;

    //Inject into the constructor
    public TokenService(JwtEncoder encoder) {
        this.encoder = encoder;
    }

    public String generateToken(Authentication authentication) {
        // note the current time to determine the issuedAt and expiresAt
        Instant now = Instant.now(); 

        // the scope is the JWT scope SCOPE_READ which is checked in SecurityConfig during authentication, this is not the role-based scope
        String scope = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .filter(authority -> !authority.startsWith("ROLE"))
                .collect(Collectors.joining(" "));

        // create the JWT claims - name/value pairs
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("self") //issuer by this application
                .issuedAt(now) //issued now
                .expiresAt(now.plus(12, ChronoUnit.HOURS)) //expires in 12 hour
                .subject(authentication.getName()) //subject is the username
                .claim("scope", scope) //scope is the scope created above
                .build();

        // create the JWT encoder parameters
        var encoderParameters = JwtEncoderParameters.from(JwsHeader.with(MacAlgorithm.HS512).build(), claims); //use the symmetric key algorithm HS512 for signing the JWT
        return this.encoder.encode(encoderParameters).getTokenValue(); //encode the JWT and return the token value
    }
    
}
