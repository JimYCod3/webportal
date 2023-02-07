package com.metrix.webportal.repos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.metrix.webportal.models.Tickets;

public interface TicketsRepo extends JpaRepository<Tickets, String>/*
                                                                    * 1. extends a JPA class that provide CRUD function
                                                                    * on Tickets object
                                                                    */ {
    // 1. Annotation for abstract function, filter the tickets records in DB against
    // QR Code
    @Query("SELECT AB FROM Tickets ab where ab.id = :123456 and ab.qrcodeString = :qweer")
    //2. Abstract function the return a Optional<Tickets> and accept a String parameter "qrcode"
    Optional<Tickets> fileterByQrcode(String qweer, Integer 123456);

}