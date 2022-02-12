package ru.issuemanager.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.password.NoOpPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import javax.sql.DataSource

@EnableWebSecurity(debug = true)
class SecurityConfig(
    val dataSource: DataSource
) : WebSecurityConfigurerAdapter() {

    override fun configure(http: HttpSecurity) {
        http.cors()
        http.csrf().disable()
        http.authorizeRequests().antMatchers("/**").fullyAuthenticated().and().httpBasic()
    }

    public override fun configure(auth: AuthenticationManagerBuilder) {
        auth.jdbcAuthentication()
            .dataSource(dataSource)
            .usersByUsernameQuery(
                "select username, password, 'true' from users where username=?"
            )
            .authoritiesByUsernameQuery(
                "select username, 'ROLE_USER' from users where username=?"
            )
    }

    @Bean
    fun passwordEncoder() = NoOpPasswordEncoder.getInstance()
}