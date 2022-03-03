package com.futark.cros2.service;

import com.futark.cros2.entity.Edition;
import com.futark.cros2.repository.EditionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EditionService {

    @Autowired
    EditionRepo editionRepo ;


    public Edition saveEdition(Edition edition){
        return editionRepo.save(edition);
    }

    public Edition getEdition(Long id){
        return editionRepo.getById(id);
    }

    public List<Edition> getEditions(){
        return editionRepo.findAll();
    }

    public Page<Edition> filtreEdition(String name, Integer page, Integer size, String sortProperty,String sortDirection){
        Sort sort = Sort.by(Sort.Direction.ASC,sortProperty);
        if(sortDirection.equals("DESC")){
            sort = Sort.by(Sort.Direction.DESC,sortProperty);
        }
        Pageable pageable = PageRequest.of(page, size, sort);
        return editionRepo.filtreEdition(name,pageable);
    }

    public Boolean delEdition(Long id){
        try {
            editionRepo.deleteById(id);
            return true;
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

}
