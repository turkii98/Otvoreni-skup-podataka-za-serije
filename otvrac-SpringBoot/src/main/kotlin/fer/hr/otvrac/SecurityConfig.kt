package fer.hr.otvrac

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.config.web.servlet.invoke
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
class SecurityConfig {

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {

        http {
            cors{ }
            csrf{ disable() }
            sessionManagement {
                sessionCreationPolicy = SessionCreationPolicy.STATELESS
            }
            authorizeRequests {
                //authorize(HttpMethod.GET,"/car/{id}", hasAuthority("SCOPE_USER"), hasAuthority("SCOPE_ADMIN"))
                authorize(HttpMethod.GET,"/seriescsv", authenticated)
                authorize(HttpMethod.GET,"/json", authenticated)
                authorize(HttpMethod.GET,"/series", authenticated)
                authorize("/json", permitAll)
                //authorize(anyRequest, authenticated)
            }
            oauth2ResourceServer {
                jwt {}
            }
        }
        return http.build()
    }
}