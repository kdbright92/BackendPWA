//package com.example.BackendPWA.Security;
//
//import com.example.BackendPWA.User.Roles;
//import com.example.BackendPWA.User.User;
//import com.example.BackendPWA.User.UserServImpl;
//
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
//import org.springframework.security.config.http.SessionCreationPolicy;
//
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.security.web.DefaultSecurityFilterChain;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.CorsConfigurationSource;
//import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
//
//import java.util.Arrays;
//import java.util.List;
//
//@Configuration
//@EnableWebSecurity
//@RequiredArgsConstructor
//@EnableGlobalMethodSecurity(prePostEnabled = true)
//public class SecurityConfig {
//
//
//    private final UserServImpl userDetailsService;
//
//    private final BCryptPasswordEncoder bCryptPasswordEncoder;
//
//    private final MyCustomDSL customDsl;
//
//    @Bean
//    public MyCustomConfigurer myCustomConfigurer() {
//        return new MyCustomConfigurer();
//    }
//    private static class MyCustomConfigurer extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
//
//
//        @Bean
//        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//
//            http.csrf().disable()
//                    .authorizeRequests(authorizeRequests ->
//                            authorizeRequests
//                                    .requestMatchers("/user/register", "/user/login", "/admin/deleteAll", "/post/getAll", "/post/{id}", "/post/withUserInfo", "/friendship", "/friendship/sendRequest", "/comment/create ", "/friendship/acceptRequest/{friendshipId}", "/friendship/friends/{userId}", "/friendship/rejectRequest/{friendshipId}", "/friendship/requests/pending/**").permitAll()
//                                    .anyRequest().authenticated()
//                    )
//                    .apply(customDsl());
//
//            return http.build();
//        }
//
//        @Bean
//        public static MyCustomDSL customDsl() {
//            return new MyCustomDSL();
//        }
//
//
//        @Bean
//        CorsConfigurationSource corsConfigurationSource() {
//            CorsConfiguration configuration = new CorsConfiguration();
//            configuration.setAllowedOrigins(Arrays.asList("*"));
//            configuration.setAllowedMethods(Arrays.asList("GET", "POST", "DELETE", "PUT"));
//            configuration.setAllowedHeaders(Arrays.asList("*"));
//            configuration.setExposedHeaders(Arrays.asList("token"));
//
//            UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//            source.registerCorsConfiguration("/**", configuration);
//            return source;
//        }
//    }
//}
