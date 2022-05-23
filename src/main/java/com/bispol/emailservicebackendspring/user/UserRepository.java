package com.bispol.emailservicebackendspring.user;

import com.bispol.emailservicebackendspring.user.dto.UserDtoQueryInfo;
import com.bispol.emailservicebackendspring.user.dto.UserDtoQueryLogin;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository()
interface UserRepository extends org.springframework.data.repository.Repository<User, Long> {
    void save(User user);

    List<User> findBy();

    Optional<User> findByUsername(String username);

    @Query("SELECT u FROM User u WHERE u.username=?1")
    Optional<UserDtoQueryLogin> findLoginQueryByUsername(String username);

    boolean existsByUsernameOrEmail(String username, String email);

    @Query("SELECT u FROM User u")
    List<UserDtoQueryInfo> findDtoBy();

    Optional<User> findById(long userId);

    void delete(User user);
}
