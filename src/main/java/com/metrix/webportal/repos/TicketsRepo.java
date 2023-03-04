package com.metrix.webportal.repos;

import com.metrix.webportal.models.Tickets;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface TicketsRepo extends JpaRepository<Tickets, String>/*1. extends a JPA class that provide CRUD function on Tickets object*/{
    //1. Annotation for abstract function, filter the tickets records in DB against QR Code
    @Query("SELECT t FROM Tickets t WHERE t.qrcode = :qrcode")
    Optional<Tickets> getTicketByQrCode(String qrcode);
    //2. Abstract function the return a Optional<Tickets> and accept a String parameter "qrcode"
}