package com.metrix.webportal.repos;

import com.metrix.webportal.models.Venues;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VenuesRepo extends JpaRepository<Venues, String>/*1. extends a JPA class that provide CRUD function on Venues object*/{
    
}
