package com.eteration.simplebanking.controller;


// This class is a place holder you can change the complete implementation

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TransactionStatus {

    private String status;
    private String approvalCode;



}
