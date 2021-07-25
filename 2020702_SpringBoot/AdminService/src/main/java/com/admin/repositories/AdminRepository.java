package com.admin.repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import com.admin.entities.Admin;

public interface AdminRepository extends JpaRepository<Admin, Integer>{

}
