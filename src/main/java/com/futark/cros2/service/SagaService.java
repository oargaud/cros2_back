package com.futark.cros2.service;

import com.futark.cros2.entity.Saga;
import com.futark.cros2.repository.SagaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SagaService {

    @Autowired
    SagaRepo sagaRepo;


    public Saga saveSaga(Saga saga){
        return sagaRepo.save(saga);
    }

    public Saga getSaga(Long id){
        return sagaRepo.getById(id);
    }

    public List<Saga> getSagas(){
        return sagaRepo.findAll();
    }

    public Page<Saga> filtreSaga(String name, Integer page, Integer size, String sortProperty,String sortDirection){
        sortProperty = "name";
        Sort sort = Sort.by(Sort.Direction.ASC,sortProperty);
        if(sortDirection.equals("DESC")){
            sort = Sort.by(Sort.Direction.DESC,sortProperty);
        }
        Pageable pageable = PageRequest.of(page, size, sort);
        return sagaRepo.filtreSaga(name,pageable);
    }

    public Boolean delSaga(Long id){
        try {
            sagaRepo.deleteById(id);
            return true;
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

}
