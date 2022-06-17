package com.hackerrank.sample.controller;

import com.hackerrank.sample.dto.Request;
import com.hackerrank.sample.dto.StringResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController {

   @RequestMapping(path = "/insert", method = RequestMethod.POST)
   public ResponseEntity createRecord(@RequestBody Request request){
    return (ResponseEntity) ResponseEntity.ok();
   }

}
