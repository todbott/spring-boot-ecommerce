package com.ecommerce.security;

//import com.ecommerce.security.AuthenticationFilter;
//import com.ecommerce.security.AuthorizationFilter;
import com.ecommerce.services.ApplicationUserDetailsService;
import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import static com.ecommerce.security.SecurityConstants.LOGIN_URL;
import static com.ecommerce.security.SecurityConstants.SIGN_UP_URL;
import static com.ecommerce.security.SecurityConstants.MAIN_SHOP_URL;
import static com.ecommerce.security.SecurityConstants.SEARCH_SHOP_URL;
import static com.ecommerce.security.SecurityConstants.UPLOAD_URL;
import static com.ecommerce.security.SecurityConstants.ADMIN_HOME_URL;
import static com.ecommerce.security.SecurityConstants.BUY_ITEM_URL;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {



    private ApplicationUserDetailsService userDetailsService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }

    public SecurityConfiguration(ApplicationUserDetailsService userDetailsService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userDetailsService = userDetailsService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable().authorizeRequests()
                .antMatchers(HttpMethod.POST
                , LOGIN_URL
                , SIGN_UP_URL
                , MAIN_SHOP_URL
                , SEARCH_SHOP_URL).permitAll()

                .antMatchers(HttpMethod.GET
                , LOGIN_URL
                , MAIN_SHOP_URL
                , SEARCH_SHOP_URL).permitAll()
                
                .anyRequest().authenticated()
                .and()
                .addFilter(new AuthenticationFilter(authenticationManager()))
                .addFilter(new AuthorizationFilter(authenticationManager()))
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
        return source;
    }



    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }




}
