package tacos.security;

import org.springframework.security.crypto.password.PasswordEncoder;


public class NoEncodingPasswordEncoder implements PasswordEncoder {
	@Override
	public String encode(CharSequence rawPwd) {
		return rawPwd.toString();  // 로그인 창에 입력된 비밀번호를 암호화하지 않고, String으로 반환함.
	}
	 
	@Override
	public boolean matches(CharSequence rawPwd, String encodedPwd) {
		return rawPwd.toString().equals(encodedPwd); // encode()에서 반환된 비밀번호가 데이터베이스에서 가져온 비밀번호와 같은지 비교함.
	}
	
}