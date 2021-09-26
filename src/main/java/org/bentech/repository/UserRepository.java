package org.bentech.repository;

import org.bentech.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByUsername(String userName);
    Boolean existsByUsername(String userName);
    void deleteByUsername(String userName);
}
