package ch.fhnw.pizza.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import com.nimbusds.jose.jwk.source.ImmutableSecret;

import static org.springframework.security.config.Customizer.withDefaults;

import javax.crypto.spec.SecretKeySpec;
import org.springframework.http.HttpMethod;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    @Value("${jwt.key}")
    private String jwtKey;

    @Bean
    public UserDetailsService userDetailsService() {
        return new InMemoryUserDetailsManager(
            User.withUsername("myuser")
                .password("{noop}password")
                .authorities("CARS_READ", "USER_READ","ROLE_USER")
                .build(),
            User.withUsername("myadmin")
                .password("{noop}password")
                .authorities("ADMIN_READ","ADMIN_WRITE","ROLE_ADMIN")
                .build());
    }
    

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                .requestMatchers("/authentication/**").permitAll() // /authentication/* should be reachable for unauthenticated users
                .requestMatchers(HttpMethod.POST, "admin/*","/cars/*", "/caruser/*","/rentals/*", "/locations/*").hasAuthority("SCOPE_ADMIN_WRITE") // Only users with the scope ADMIN_WRITE can access these endpoints
                .requestMatchers(HttpMethod.PUT, "admin/*","/cars/*", "/caruser/*","/rentals/*", "/locations/*").hasAuthority("SCOPE_ADMIN_WRITE") // Only users with the scope ADMIN_WRITE can access these endpoints
                .requestMatchers(HttpMethod.DELETE, "admin/*","/cars/*", "/caruser/*","/rentals/*", "/locations/*").hasAnyAuthority("SCOPE_ADMIN_WRITE") // Only users with the scope ADMIN_WRITE can access these endpoints
                .requestMatchers(HttpMethod.GET, "/cars/*").hasAnyAuthority("SCOPE_ADMIN_READ", "SCOPE_USER_READ") // Only users with the scope ADMIN_READ or USER_READ can access these endpoints
                .requestMatchers(HttpMethod.GET, "admin/*", "/caruser/*","/rentals/*", "/locations/*").hasAuthority("SCOPE_ADMIN_READ") // Only users with the scope ADMIN_READ can access these endpoints
                .requestMatchers(                   "/**", //allow access to the home page
                                                    "/swagger-ui.html", //allow access to the swagger UI
                                                    "/v3/api-docs/**",  //allow access to the swagger API documentation
                                                    "/swagger-ui/**",   //allow access to the swagger UI
                                                    "/h2-console/**")   //allow access to the h2-console
                                                    .permitAll() 
                .anyRequest().authenticated() // All other requests need to be authenticated
                //.anyRequest().hasAuthority("SCOPE_READ")
            )   
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .oauth2ResourceServer(oAuth -> oAuth.jwt(Customizer.withDefaults()))
            .httpBasic(withDefaults())
            .build();
        }

    @Bean
    JwtEncoder jwtEncoder() {
        return new NimbusJwtEncoder(new ImmutableSecret<>(jwtKey.getBytes()));
    }

    @Bean
    public JwtDecoder jwtDecoder() {
        byte[] bytes = jwtKey.getBytes();
        SecretKeySpec originalKey = new SecretKeySpec(bytes, 0, bytes.length,"RSA");
        return NimbusJwtDecoder.withSecretKey(originalKey).macAlgorithm(MacAlgorithm.HS512).build();
    }


    
}
