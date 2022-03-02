package com.exam.examserver.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.exam.examserver.Models.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>{

}
