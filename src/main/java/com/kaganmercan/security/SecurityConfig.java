package com.kaganmercan.security;

import com.kaganmercan.bean.PasswordEncoderBean;
import com.kaganmercan.security.jwt.JwtAuthorizationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//lombok
@RequiredArgsConstructor

// In a lifecycle of spring, this annotation handles our authorization requests codes.
@EnableWebSecurity

//bean için ekledim
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    //field
    private final PasswordEncoderBean passwordEncoderBean;
    private final UserDetailsServiceCustom customUserDetailsService;



    // BEAN
    //import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("*")
                        .allowedMethods("*"); //POST,GET,
            }
        };
    }

    // JwtAuthorizationFilter
    @Bean
    public JwtAuthorizationFilter jwtAuthorizationFilterBeanMethod() {
        return new JwtAuthorizationFilter();
    }

    // AUTHENTICATION_MANAGER
    @Override
    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    //////////////////////////////////////////////////////////////////////////////////
    // Authentication
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoderBean.passwordEncoderMethod());
    }

    // This configures method, handles authorization through page url's.
    /*/ If we don't we don't authorize then 401 Unauthorized on certain sites
         for which we are not authorized./*/
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // Cross  Side Request Forgery and JWT will be using.
        http.csrf().disable();
//        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        //login
        http
                .authorizeRequests()
                    .antMatchers(HttpMethod.OPTIONS).permitAll()
                    .antMatchers("/login","/resources/**","/api/authentication/**","/static/**", "/css/**", "/js/**", "/images/**", "/assets/**", "/fonts/**", "/dis/**", "/vendor1/**", "/assets2/**").permitAll() //login and register
                    .antMatchers("/register","/resources/**","/static/**", "/css/**", "/js/**", "/images/**", "/assets/**", "/fonts/**", "/dis/**", "/vendor1/**", "/assets2/**").permitAll() //login and register
                    .antMatchers("/home").permitAll()
                .anyRequest().authenticated()
                    .and()
                .formLogin()
                    .loginPage("/login")
                    .loginProcessingUrl("/login")
                    .defaultSuccessUrl("/home.html", true)
                    .successHandler(new SavedRequestAwareAuthenticationSuccessHandler())
                    .permitAll()
                    .and()
                .logout()
                    .permitAll();


        // That method simply configure JWT authentication filter before Username and Password.
        http.addFilterBefore(jwtAuthorizationFilterBeanMethod(), UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers("/home/**","/login/**","/api-docs/**", "/swagger-ui/**", "/swagger-ui.html/**", "/h2-console/**", "/resources/**", "/static/**", "/css/**", "/js/**", "/images/**", "/assets/**", "/fonts/**", "/dis/**", "/vendor1/**", "/assets2/**");
    }
}
