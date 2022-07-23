package com.hackerrank.sample.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class Request {

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
  private String date;

  @NotNull
  @Length(min = 3, message = "Please provide at least 3 characters")
  private String firstName;

  private String lastName;

  @Digits(fraction = 0, integer = 10, message = "Please provide valid Phone number")
  private long phoneNumber;

}
