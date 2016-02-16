package hello;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

import static com.stormpath.spring.config.StormpathWebSecurityConfigurer.stormpath;

public class SpringSecurityWebAppConfig {

    @Configuration
    @Order(1)
    public static class ApiWebSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {
        protected void configure(HttpSecurity http) throws Exception {
            http.apply(stormpath())
                    .and()
                    .antMatcher("/greeting")
                    .authorizeRequests()
                    .anyRequest().authenticated()
                    .and()
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    .and()
                    .httpBasic();
        }
    }

    @Configuration
    @ConfigurationProperties(prefix="hello")
    public static class FormLoginWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {
        // Note: Add hello.admin-group href to application.yaml.
        private String adminGroup;

        public String getAdminGroup() {
            return adminGroup;
        }

        public void setAdminGroup(String adminGroup) {
            this.adminGroup = adminGroup;
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.apply(stormpath())
                    .and()
                    .authorizeRequests()
                    .anyRequest().hasRole(adminGroup);
        }
    }
}