package com.example.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.Rest.CustomAccessDeniedHandler;
import com.example.Rest.JwtAuthenticationTokenFilter;
import com.example.Rest.RestAuthenticationEntryPoint;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Bean
	public JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter() throws Exception {
		JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter = new JwtAuthenticationTokenFilter();
		jwtAuthenticationTokenFilter.setAuthenticationManager(authenticationManager());
		return jwtAuthenticationTokenFilter;
	}

	@Bean
	public RestAuthenticationEntryPoint restServicesEntryPoint() {
		return new RestAuthenticationEntryPoint();
	}

	@Bean
	public CustomAccessDeniedHandler customAccessDeniedHandler() {
		return new CustomAccessDeniedHandler();
	}

	@Bean
	@Override
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}

	protected void configure(HttpSecurity http) throws Exception {
		// Disable crsf cho đường dẫn /rest/**
		http.csrf().ignoringAntMatchers("/**");
		  http.cors().and();
		  http.authorizeRequests().antMatchers("/login**").permitAll();
		  http.antMatcher("/**").httpBasic().authenticationEntryPoint(restServicesEntryPoint()).and()
		    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().authorizeRequests()
		    .antMatchers(HttpMethod.GET, "/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
		    .antMatchers(HttpMethod.POST, "/add-user").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_PM')")
		    .antMatchers(HttpMethod.POST, "/get-all-projects").permitAll()
		    .antMatchers(HttpMethod.POST, "/log-time-sheet").permitAll()
		    .antMatchers(HttpMethod.POST, "/log-time-sheet/all").access("hasRole('ROLE_ADMIN')")
		    .antMatchers(HttpMethod.POST, "/log-time-sheet/user").permitAll()
		    .antMatchers(HttpMethod.POST, " /log-time-sheet/user/project").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_PM')")
		    .antMatchers(HttpMethod.POST, "/log-time-sheet/create").permitAll()
		    .antMatchers(HttpMethod.POST, "/project-detail").permitAll()
		    .antMatchers(HttpMethod.POST, " /user/user-projects").permitAll()
		    .antMatchers(HttpMethod.POST, " /remove-user").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_PM')")
		    .antMatchers(HttpMethod.POST, " /end-project").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_PM')")
		    .antMatchers(HttpMethod.POST, "/count-project-running").access("hasRole('ROLE_ADMIN')")
		    .antMatchers(HttpMethod.POST, "/count-project-hours").access("hasRole('ROLE_ADMIN')")
		    .antMatchers(HttpMethod.POST, "/user-multi-project").access("hasRole('ROLE_ADMIN')")
		    .antMatchers(HttpMethod.POST, "/project-filter").permitAll()
		    .antMatchers(HttpMethod.POST, "/get-technology").permitAll()
		    .antMatchers(HttpMethod.POST, "/user-filter").access("hasRole('ROLE_ADMIN')")
		    .antMatchers(HttpMethod.DELETE, "/**").access("hasRole('ROLE_ADMIN')").and()
		    .addFilterBefore(jwtAuthenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class)
		    .exceptionHandling().accessDeniedHandler(customAccessDeniedHandler());
	}
}