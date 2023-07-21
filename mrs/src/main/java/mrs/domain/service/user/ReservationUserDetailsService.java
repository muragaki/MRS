package mrs.domain.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import mrs.domain.model.User;
import mrs.domain.repository.user.UserRepository;

@Service
public class ReservationUserDetailsService implements UserDetailsService {
	@Autowired
	UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		User user = userRepository.findOne(username);
		User user = userRepository.findById(username).get();
		if (user == null) {
			throw new UsernameNotFoundException(username + " is not found.");
		}
		return new ReservationUserDetails(user);
	}
	
	public int register(User user) {
		userRepository.save(user);
		return 0;
	}
	
	public boolean isExistUser(String username) {
        return userRepository.existsById(username);
      
    }
}