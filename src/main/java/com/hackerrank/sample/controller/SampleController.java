package com.hackerrank.sample.controller;

import com.hackerrank.sample.dto.RecordResponse;
import com.hackerrank.sample.dto.Request;
import com.hackerrank.sample.dto.StringResponse;
import com.hackerrank.sample.service.ManageDataService;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/endpoint")
public class SampleController {

  @Autowired
  ManageDataService manageDataService;

  @PostMapping(path = "/insert", produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> createRecord(@Valid @RequestBody Request request) {
    Optional<StringResponse> stringResponse = manageDataService.createRecord(request);
    if (stringResponse.isPresent()) {
      return ResponseEntity.status(HttpStatus.CREATED)
          .body(stringResponse.get());
    }
    return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
  }

  @GetMapping(value = "/select", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity getRecords() throws Exception {
    Optional<List<RecordResponse>> recordResponseList = manageDataService.getRecords();
    if (recordResponseList.isPresent() && recordResponseList.get().size() > 0) {
      return ResponseEntity.ok(recordResponseList.get());
    }
    return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
  }

//  @RequestMapping(path="/app/{age}")
//  public String getMessage(@PathVariable("age") String age) {
//
//    String msg = String.format("%s is %s years old", "Alok", age);
//
//    return msg;
//  }

  @GetMapping(path = "/select/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity getRecord(@PathVariable("userId") String userId) throws Exception {

    Optional<RecordResponse> recordResponse = manageDataService.getRecords(userId);
    if (recordResponse.isPresent()) {
      return ResponseEntity.ok(recordResponse.get());
    } else {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

  }

  @DeleteMapping(value = "/delete/{userId}")
  public ResponseEntity<?> deleteRecord(@PathVariable("userId") String userId)
      throws EmptyResultDataAccessException {
    manageDataService.deleteRecord(userId);
    return ResponseEntity.status(HttpStatus.OK).build();
  }

}
