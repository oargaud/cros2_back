package com.futark.cros2.service;

import com.futark.cros2.entity.Artiste;
import com.futark.cros2.repository.ArtisteRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArtisteService {

    @Autowired
    ArtisteRepo artisteRepo;


    public Artiste saveArtiste(Artiste saga){
        return artisteRepo.save(saga);
    }

    public Artiste getArtiste(Long id){
        return artisteRepo.getById(id);
    }

    public List<Artiste> getArtistes(){
        return artisteRepo.findAll();
    }

    public Page<Artiste> filtreArtiste(String lastname, String firstname, Integer page, Integer size, String sortProperty,String sortDirection){
        Sort sort = Sort.by(Sort.Direction.ASC,sortProperty);
        if(sortDirection.equals("DESC")){
            sort = Sort.by(Sort.Direction.DESC,sortProperty);
        }
        Pageable pageable = PageRequest.of(page, size, sort);
        return artisteRepo.filtreArtiste(lastname,firstname,pageable);
    }

    public Boolean delArtiste(Long id){
        try {
            artisteRepo.deleteById(id);
            return true;
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

}
