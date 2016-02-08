package hello;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import static com.stormpath.spring.config.StormpathWebSecurityConfigurer.stormpath;

@Configuration
public class SpringSecurityWebAppConfig extends WebSecurityConfigurerAdapter {
    protected void doConfigure(HttpSecurity http) throws Exception {
        http
                .apply(stormpath()).and()
                .authorizeRequests()
                .antMatchers("/").permitAll();
    }
}