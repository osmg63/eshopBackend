package com.hackathon.mobil.service;

import com.hackathon.mobil.dao.UserRepo;
import com.hackathon.mobil.dto.CreateUserRequest;
import com.hackathon.mobil.entity.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;



import java.util.Optional;


@Service
public class UserService implements UserDetailsService {
    private final UserRepo repo;

    public UserService(UserRepo repo) {
        this.repo = repo;
    }

    public User insert(User user){
        return repo.save(user);
    }
    public ResponseEntity<String> createUser(CreateUserRequest request) throws Exception {
        User newUser = User.builder()
                .name(request.name())
                .lastName(request.lastName())
                .gmail(request.gmail())
                .username(request.username())
                .password(new BCryptPasswordEncoder().encode(request.password()))
                .authorities(request.authorities())
                .accountNonExpired(true)
                .credentialsNonExpired(true)
                .isEnabled(true)
                .accountNonLocked(true)
                .build();
        System.out.println("kullanıcı eklendi" + newUser.getAuthorities());
        repo.save(newUser);
        return ResponseEntity.ok("başarılı");
    }
    public void  delete(int  id){
        User user= repo.findById(id).orElseThrow();
        repo.delete(user);
    }
    public Optional<User> find(int id){
        return repo.findById(id);
    }

    public Optional<User> getUserByUserName(String username) {
        return repo.getUserByUsername(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user=repo.findByUsername(username);
        return user.orElseThrow(EntityNotFoundException::new);
    }
}
