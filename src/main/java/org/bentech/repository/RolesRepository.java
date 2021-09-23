package org.bentech.repository;

import org.bentech.entity.UserRolesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolesRepository extends JpaRepository<UserRolesEntity, Long> {
}
