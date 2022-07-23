package com.hackerrank.sample.service;

import com.hackerrank.sample.dto.RecordResponse;
import com.hackerrank.sample.dto.Request;
import com.hackerrank.sample.dto.StringResponse;
import com.hackerrank.sample.dto.UserEntity;
import com.hackerrank.sample.exception.RecordNotFoundException;
import com.hackerrank.sample.repository.UserRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ManageDataServiceImpl implements ManageDataService {

  UserRepository userRepository;
  ModelMapper modelMapper;

  public ManageDataServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
    this.modelMapper = new ModelMapper();
  }

  @Override
  public Optional<StringResponse> createRecord(Request request) {
    UserEntity userEntity = modelMapper.map(request, UserEntity.class);
    userRepository.save(userEntity);
    StringResponse stringResponse = new StringResponse(String.valueOf(userEntity.getId()));
    return Optional.of(stringResponse);
  }

  @Override
  public Optional<List<RecordResponse>> getRecords() {
    List<UserEntity> userEntities = userRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
//    List<RecordResponse> dtos = userEntities
//        .stream()
//        .map(user -> modelMapper.map(user, RecordResponse.class))
//        .collect(Collectors.toList());
    List<RecordResponse> recordResponseList = mapList(userEntities, RecordResponse.class);
    modelMapper.map(userEntities, recordResponseList);
    return Optional.of(recordResponseList);
  }

  @Override
  public Optional<RecordResponse> getRecords(String userId) throws RecordNotFoundException {
    Optional<UserEntity> userEntity = userRepository.findById(Integer.parseInt(userId));
    RecordResponse recordResponse = null;
    if (userEntity.isPresent() && userEntity.get() != null) {
      recordResponse = modelMapper.map(userEntity.get(), RecordResponse.class);
    } else {
      throw new RecordNotFoundException(userId);
    }
    return Optional.of(recordResponse);
  }

  @Override
  public void deleteRecord(String userId) throws EmptyResultDataAccessException {
    userRepository.deleteById(Integer.parseInt(userId));
  }

  <S, T> List<T> mapList(List<S> source, Class<T> targetClass) {
    return source
        .stream()
        .map(element -> modelMapper.map(element, targetClass))
        .collect(Collectors.toList());
  }
}
