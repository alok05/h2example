package com.hackerrank.sample.service;

import com.hackerrank.sample.dto.Request;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public interface ManageData {
 void createRecord();

 List<Request> getRecords();

 Request getRecords(String id);


}
