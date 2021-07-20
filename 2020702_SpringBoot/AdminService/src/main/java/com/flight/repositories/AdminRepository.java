package com.flight.repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import com.flight.entities.Admin;

public interface AdminRepository extends JpaRepository<Admin, Integer>{

}
