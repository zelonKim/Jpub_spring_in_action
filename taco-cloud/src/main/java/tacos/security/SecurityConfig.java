package tacos.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation
					.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation
					.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web
					.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web
					.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter { // 사용자의 HTTP 요청 경로에 대해 보안 처리를 원하는 대로 지정함.
	@Override
	protected void configure(HttpSecurity http) throws Exception { // HTTP 보안을 구성하는 메서드

/*
		http.authorizeRequests()
			//.antMatchers("요청 경로").보안 처리 메서드
			.antMatchers("/design","/orders").hasRole("ROLE_USER")
			.antMatchers("/", "/**").permitAll();
*/
		
		http.authorizeRequests() 
		.antMatchers("/design", "/orders").access("hasRole('ROLE_USER')") // "/design"와 "/orders" 경로는 ROLE_USER 사용자만 홈페이지 접근을 허용함.
		.antMatchers("/", "/**").access("permitAll") // "/" 와 "/**" 경로는 모든 사용자의 홈페이지 접근을 허용함.
		
		.and()
		.formLogin() // 커스텀 로그인 페이지가 있는 경로를 스프링 시큐리티에 알려줌.
		.loginPage("/login") // 커스텀 로그인 페이지 경로를 지정함.
		//.defaultSuccessUrl("/design", true) // 사용자가 직접 로그인 페이지로 이동한 후 로그인을 성공적으로 할 경우, 이동할 페이지를 지정함.
		  // 두번째 인자로 true를 전달할 경우, 사용자가 로그인 전에 어떤 페이지에 있었는지와 무관하게 로그인 후에는 무조건 지정된 페이지로 이동함.
		
		// 기본적으로 스프링은 '/login' 경로로 로그인 요청처리를 함.
		//.loginProcessingUrl("/authenticate") // 로그인 요청처리 경로를 직접 지정함.
		
		// 기본적으로 스프링은 'username'라는 사용자 이름 필드를 가짐.
		//.usernameParameter("user") // 사용자 이름 필드를 지정함.
		
		// 기본적으로 스프링은 'password'라는 비밀번호 필드를 가짐.
		//.passwordParameter("pwd") // 비밀번호 필드를 지정함.
		
		.and()
		.logout()
		.logoutSuccessUrl("/") // 로그아웃 이후에 이동할 페이지를 지정함.
		
		.and()
		.csrf();
		//.disable(); // REST API 서버로 실행되는 애플리케이션의 경우, CSRF 지원을 비활성화 해야함.
		

	}
	
	
	
	
	
	@Autowired
	DataSource dataSource; // 사용자 정보를 저장하는 테이블과 열이 정해져 있고, 쿼리가 미리 생성되어 있음.
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception { // 사용자 인증 정보를 구성하는 메서드
/*
		auth.inMemoryAuthentication() // 인메모리 사용자 스토어를 구성함으로써, 한 명 이상의 사용자를 처리할 수 있도록 함.
			.withUser("user1") // 사용자 이름을 인자로 전달함.
			.password("{noop}password1") // {noop}을 지정함으로써 비밀번호를 암호화하지 않음.
			.authorities("ROLE_USER") 
			.and()
			.withUser("user2")
			.password("{noop}password2")
			.authorities("ROLE_USER");
	} // ROLE_USER 사용자 정보를 직접 지정함.
*/
	
/*
		auth.jdbcAuthentication() // JDBC 기반 사용자 스토어를 구성함.
			.dataSource(dataSource)
			// schema.sql와 data.sql 파일의 SQL이 데이터소스로 지정된 데이터베이스(h2)에서 자동 실행됨.
			// PasswordEncoder를 사용해서 비밀번호를 의무적으로 암호화 해줘야 함.
			.usersByUsernameQuery("select username, password, enabled from users" + "where username=?") // 사용자 정보 인증 쿼리를 커스터마이징함.
			.authoritiesByUsernameQuery("select username, authority from authorities" + "where username=?") // 사용자 권한 쿼리를 커스터마이징함.
			//.groupAuthoritiesByUsername("select ... from ..." + "where ..."); // 사용자 그룹 권한 쿼리를 커스터마이징함.
				// 쿼리를 커스터마이징할 경우, 쿼리 매개변수는 하나이며 username이어야 함.
				
			//.passwordEncoder(new BCryptPasswordEncoder()); // .passwordEncoder(암호화 객체)
			.passwordEncoder(new NoEncodingPasswordEncoder());
*/
		
		
/*		
		auth.ldapAuthentication() // LDAP 기반 사용자 스토어
			.userSearchBase("ou=people") // 사용자를 찾기 위한 기준점 쿼리를 제공함. -> people 구성단위 부터 검색 시작 
			.userSearchFilter("(uid={0})")
			
			.groupSearchBase("ou=groups") // 그룹을 찾기 위한 기준점 쿼리를 지정함. -> groups 구성단위 부터 검색 시작
			.groupSearchFilter("member= {0}")
			
			.passwordCompare() // 비밀번호를 비교하는 방법으로 LDAP인증을 함. -> 로그인 창에 입력된 비밀번호가 LDAP 서버에 있는 'userPassword'속성값 (기본값)과 비교됨.
			
			.passwordEncoder(new BCryptPasswordEncoder()) // 해커가 LDAP 서버의 비밀번호를 가로채지 못하도록 하기 위해 암호화함.
			.passwordAttribute("userPasscode"); // 'userPasscode'속성값 (지정값)과 비교됨.
			
			// 기본적으로 localhost:33389 포트로 LDAP 서버가 접속됨.
			//.contextSource().url("ldap://tacocloud.com:389/dc=tacocloud, dc=com"); // LDAP 서버가 원격 컴퓨터에서 실행 중일 경우, 해당 서버의 위치를 지정함.
			// .contextSource().root("dc=tacocloud, dc=com"); // 내장 LDAP 서버의 루트 경로를 지정함.
			// .ldif("classpath:users.ldif"); // LDIF 파일을 찾을 수 있는 경로를 지정함.
*/
		
		
		
			auth.userDetailsService(userDetailsService) // 사용자 명세 서비스
				.passwordEncoder(encoder());
		
	}
}


