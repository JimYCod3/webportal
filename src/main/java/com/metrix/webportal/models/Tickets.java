package com.metrix.webportal.models;

import java.io.Serializable;
import java.util.Date;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
//1. Annotation for indicating this is an entity class
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Tickets implements Serializable {
    //2. Annotation for indicating this field is a primary key of the class
    //3. Annotation for indicating this field will auto generate seq. number when insert into DB
    private int id;

    //4a. Annotation for checking this field is not blank, if blank, view will show error message "Operator must be provided!"
    //5a. Annotation for checking this field cannot exceed 100 characters, if not, view will show error message "Operator must less than 100 characters!"
    //6a. Annotation indicating this field in DB is not nullable
    private String operator;

    //4b. Annotation for checking this field is not blank, if blank, view will show error message "QR Code must be provided!"
    //5b. Annotation for checking this field cannot exceed 255 characters, if not, view will show error message "QR Code must less than 255 characters!"
    //6b. Annotation indicating this field in DB is not nullable and a unique field    
    private String qrcode;

    private boolean isClaimed;

    //6c. Annotation indicating this field in DB is not nullable
    //7a. Annotation for checking this field value should be a past date, if not, view will show error message "Sell date time must be a past date time"
    private Date sellDtm;

    //6d. Annotation indicating this field in DB is not nullable
    //7b. Annotation for checking this field value should be a past date, if not, view will show error message "Claim date time must be a past date time"
    private Date claimDtm;

    @ManyToOne(fetch = FetchType.LAZY)
    private Events event;    
}
