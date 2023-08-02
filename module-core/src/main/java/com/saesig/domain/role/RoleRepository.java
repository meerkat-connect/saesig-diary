package com.saesig.domain.role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Long> {

    public Role findByName(String name);

    @Query("SELECT r FROM Role AS r left join fetch r.parentRole")
    public List<Role> findAll();

}
