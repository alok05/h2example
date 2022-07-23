package com.hackerrank.sample.dto;

import lombok.Data;

@Data
public class RecordResponse {

  private int id;

  private String date;

  private String firstName;

  private String lastName;

  private long phoneNumber;
}
