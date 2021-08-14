package me.lozm.global.config;

import lombok.RequiredArgsConstructor;
import me.lozm.api.auth.service.UserDetailsServiceImpl;
import me.lozm.global.jwt.JwtAuthenticationEntryPoint;
import me.lozm.global.jwt.JwtRequestFilter;
import me.lozm.global.security.UrlFilterInvocationSecurityMetadataSource;
import me.lozm.global.security.factory.UrlResourceMapFactoryBean;
import me.lozm.global.security.filter.PermitAllFilter;
import me.lozm.global.security.service.SecurityResourceService;
import me.lozm.global.security.voter.IpAddressVoter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.access.vote.AffirmativeBased;
import org.springframework.security.access.vote.RoleHierarchyVoter;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableWebSecurity(debug = true)
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsServiceImpl userDetailsService;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtRequestFilter jwtRequestFilter;
    private final SecurityResourceService securityResourceService;


    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable();

        //TODO Load access denied page
        httpSecurity.exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//                .maximumSessions(1)
//                .maxSessionsPreventsLogin(true);

        httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterAt(customFilterSecurityInterceptor(), FilterSecurityInterceptor.class);

        httpSecurity.authorizeRequests()
                .anyRequest().authenticated();
    }

//    @Override
//    public void configure(WebSecurity web) {
//        web.ignoring().antMatchers(
//                "/v2/api-docs", "/configuration/ui", "/swagger-resources/**",
//                "/configuration/security", "/swagger-ui.html", "/webjars/**",
//                "/swagger/**", "/h2-console/**");
//    }

    @Bean
    public PermitAllFilter customFilterSecurityInterceptor() throws Exception {
        String[] permitAllResourceArray = {
//                "/v2/api-docs", "/configuration/ui", "/swagger-resources/**",
//                "/configuration/security", "/swagger-ui.html", "/webjars/**",
//                "/swagger/**", "/h2-console/**", JwtAuthenticationController.AUTHENTICATE_PATH,
//                UserController.USER_PATH + "/**"
        };

        PermitAllFilter permitAllFilter = new PermitAllFilter(permitAllResourceArray);
        permitAllFilter.setSecurityMetadataSource(urlFilterInvocationSecurityMetadataSource());
        permitAllFilter.setAccessDecisionManager(affirmativeBased());
        permitAllFilter.setAuthenticationManager(authenticationManagerBean());
        return permitAllFilter;
    }

    @Bean
    public FilterInvocationSecurityMetadataSource urlFilterInvocationSecurityMetadataSource() {
        return new UrlFilterInvocationSecurityMetadataSource(urlResourceMapFactoryBean().getObject(), securityResourceService);
    }

    private UrlResourceMapFactoryBean urlResourceMapFactoryBean() {
        return new UrlResourceMapFactoryBean(securityResourceService);
    }

    @Bean
    public AccessDecisionManager affirmativeBased() {
        return new AffirmativeBased(getAccessDecisionVoters());
    }

    private List<AccessDecisionVoter<?>> getAccessDecisionVoters() {
//        return Arrays.asList(new RoleVoter());
        List<AccessDecisionVoter<? extends Object>> accessDecisionVoterList = new ArrayList<>();
        accessDecisionVoterList.add(new IpAddressVoter(securityResourceService));
        accessDecisionVoterList.add(roleHierarchyVoter());
        return accessDecisionVoterList;
    }

    @Bean
    public AccessDecisionVoter<? extends Object> roleHierarchyVoter() {
        return new RoleHierarchyVoter(roleHierarchy());
    }

    @Bean
    public RoleHierarchyImpl roleHierarchy() {
        return new RoleHierarchyImpl();
    }

}
