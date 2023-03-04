package com.metrix.webportal.repos;

import com.metrix.webportal.models.Events;
import com.metrix.webportal.models.Tickets;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.Optional;

public interface EventsRepo extends JpaRepository<Events, String>/*1. extends a JPA class that provide CRUD function on Events object*/{

}
