package com.futark.cros2.repository;

import com.futark.cros2.entity.Control;
import com.futark.cros2.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TodoRepo extends JpaRepository<Todo,Long> {

//    List<Todo> findAllByOrderByPriorityDscCreationAsc();

}
