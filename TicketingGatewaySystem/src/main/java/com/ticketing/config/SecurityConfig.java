import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.CookieClearingLogoutHandler;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;

@EnableWebSecurity
public class SecurityConfig {

@Bean
public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
  http
    .authorizeRequests()
      .requestMatchers("/manager/**").hasRole("MANAGER")
      .requestMatchers("/admin/**").hasRole("ADMIN")
      .requestMatchers("/user/**").hasRole("USER")
      .anyRequest().authenticated()
    .and()
    .exceptionHandling()
      .accessDeniedPage("/access-denied")
    .and()
    .logout()
      .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
      .addLogoutHandler(cookieClearingLogoutHandler())
      .logoutSuccessUrl("/login?logout=true")
      .invalidateHttpSession(true)
      .deleteCookies("JSESSIONID")
    .and()
    .csrf().disable();

  return http.build();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public LogoutHandler cookieClearingLogoutHandler() {
    return new CookieClearingLogoutHandler("JSESSIONID");
  }
}
