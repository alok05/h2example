package com.hackerrank.sample.service;

import com.hackerrank.sample.dto.RecordResponse;
import com.hackerrank.sample.dto.Request;
import com.hackerrank.sample.dto.StringResponse;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public interface ManageDataService {

  Optional<StringResponse> createRecord(Request request);

  Optional<List<RecordResponse>> getRecords();

  Optional<RecordResponse> getRecords(String userId) throws Exception;

  void deleteRecord(String userId);

}
