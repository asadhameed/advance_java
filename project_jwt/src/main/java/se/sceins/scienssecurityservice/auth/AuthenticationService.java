package se.sceins.scienssecurityservice.auth;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import se.sceins.scienssecurityservice.config.JwtService;
import se.sceins.scienssecurityservice.domain.Role;
import se.sceins.scienssecurityservice.domain.User;
import se.sceins.scienssecurityservice.domain.UserService;

@Service
public class AuthenticationService {
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private JwtService jwtService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	private static final Logger log = LoggerFactory.getLogger(AuthenticationService.class);

	public AuthenticationResponse register(RegisterRequest request) {
		// TODO Auto-generated method stub
		User user = new User();
		
		user.setFirstName(request.getFirstName());
		user.setLastName(request.getLastName());
		user.setEmail(request.getEmail());
		user.setPassword(this.passwordEncoder.encode(request.getPassword()));
		user.setRole(Role.USER);
		
		this.userService.saveUser(user);
		String token = this.jwtService.generateToken(user);
		AuthenticationResponse response = new AuthenticationResponse(token);
		return response;
	}

	public AuthenticationResponse authenticate(AuthenticateRequest request) {
		// TODO Auto-generated method stub
		
		this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
		
		User user = this.userService.getUserByEmail(request.getEmail()).orElseThrow();
		String token = this.jwtService.generateToken(user);
		return new AuthenticationResponse(token);
	}

}
