package bowling.marsellie.onboarding.service;

import bowling.marsellie.onboarding.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findByUsernameIgnoreCase(username)
                .map(user -> new User(user.getUsername(), user.getPassword(), Collections.singleton(user.getRole())))
                .orElseThrow(() -> new UsernameNotFoundException("Username not found [%s]".formatted(username)));
    }
}
