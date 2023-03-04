package com.metrix.webportal.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

//1. Annotation for indicating this is an entity class
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Events implements Serializable {
    //2. Annotation for indicating this field is a primary key of the class
    @Id
    //3. Annotation for indicating this field will auto generate seq. number when insert into DB
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    //4. Annotation for checking this field is not blank, if blank, view will show error message "Event Name must be provided!"
    @NotBlank(message = "Event Name must be provided!")
    //5. Annotation for checking this field cannot exceed 255 characters, if not, view will show error message "Event Name must less than 255 characters!"
    @Size(max = 255, message = "Event Name must less than 255 characters!" )
    //6. Annotation indicating this field in DB is not nullable
    @NotNull
    private String eventName;

    private boolean isStartSell;

    @Temporal(TemporalType.TIMESTAMP)
    //7. Annotation for checking this field value should be a future date, if not, view will show error message "Event Date must be a future date"
    @Future(message = "Event Date must be a future date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date eventDtm; 

    @ManyToOne(fetch = FetchType.LAZY)
    private Venues venue;

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, fetch=FetchType.LAZY)
    @ToString.Exclude
    private Collection<Tickets> tickets = new ArrayList<>();

}