package com.metrix.webportal.repos;

public interface TicketsRepo extends /*1. extends a JPA class that provide CRUD function on Tickets object*/{
    //1. Annotation for abstract function, filter the tickets records in DB against QR Code
    //2. Abstract function the return a Optional<Tickets> and accept a String parameter "qrcode"
}