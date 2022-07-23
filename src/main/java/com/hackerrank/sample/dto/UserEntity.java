package com.hackerrank.sample.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "user_information")
@Data
public class UserEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;

  private String date;

  private String firstName;

  private String lastName;

  private long phoneNumber;
}
