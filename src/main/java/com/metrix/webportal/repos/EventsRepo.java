package com.metrix.webportal.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import com.metrix.webportal.models.Events;

public interface EventsRepo extends JpaRepository<Events, String> /*
                                                                   * 1. extends a JPA class that provide CRUD function
                                                                   * on Events
                                                                   * object
                                                                   */ {

}
