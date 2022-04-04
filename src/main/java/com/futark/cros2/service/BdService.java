package com.futark.cros2.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.futark.cros2.entity.Bd;
import com.futark.cros2.entity.Photo;
import com.futark.cros2.repository.BdRepo;
import com.futark.cros2.repository.PhotoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class BdService {

    @Autowired
    BdRepo bdRepo;

    @Autowired
    PhotoRepo photoRepo;


    public Bd saveBd(String bd,Optional<MultipartFile> photo) throws IOException {

        Bd newbd = new ObjectMapper().readValue(bd, Bd.class);

        if(photo.isPresent()&& photo!=null){
            Photo newPhoto = new Photo();
            newPhoto.setPhoto(photo.get().getBytes());
            newbd.setPhoto(photoRepo.save(newPhoto));
        }

       return bdRepo.save(newbd);
    }


    public Bd getBd(Long id){

//        Bd bd =  bdRepo.getById(id);
        Bd bd =  bdRepo.trouveCtePutainDeBD(id);

        return bd;
    }

    public List<Bd> getBds(){
        return bdRepo.findAll();
    }


    public Page<Bd> filtreBd(String titre, String tome, String statut, String saga, String edition, String illustrateur, String scenariste, Integer page, Integer size, String sortProperty, String sortDirection){

        Sort sort = Sort.by("saga").ascending().and(Sort.by("tome"));
        Pageable pageable = PageRequest.of(page, size, sort);
        String customSort = "titre";

        Long total = bdRepo.getTotal(titre,tome,statut,saga,edition,illustrateur,scenariste);
        List<Bd> bdList = bdRepo.test(titre,tome,statut,saga,edition,illustrateur,scenariste,size,page*size);

        Page<Bd> bdPage = new PageImpl<Bd>(bdList,pageable,total);

        return bdPage;
    }

    public Boolean delBd(Long id){
        try {
            bdRepo.deleteById(id);
            return true;
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }


    public Boolean delAllBd(){
        try {
            bdRepo.deleteAll();
            return true;
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }


    public Long totalPossede(){
        return bdRepo.totalPossede();
    }

}
