package com.futark.cros2.service;

import com.futark.cros2.entity.Control;
import com.futark.cros2.entity.Edition;
import com.futark.cros2.repository.ControlRepo;
import com.futark.cros2.repository.EditionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ControlService {

    @Autowired
    ControlRepo controlRepo;


    public Control saveControl(Control control){
        return controlRepo.save(control);
    }

    public Control getControl(Long id){
        return controlRepo.getById(id);
    }

    public List<Control> getControls(){
        return controlRepo.findAllByOrderByTypeAscStageAscTypeAscNameAsc();
//        List<Control> list = controlRepo.findAllByOrderByTypeAscStageAscTypeAscNameAsc();
//        String order = "l'ordre de la liste est : ";
//        for (Control control:list ) {
//            order += control.getId() + " ";
//        }
//        System.out.println(order);
//        return list;
    }

//    public Page<Control> filtreEdition(String name, Integer page, Integer size, String sortProperty,String sortDirection){
//        Sort sort = Sort.by(Sort.Direction.ASC,sortProperty);
//        if(sortDirection.equals("DESC")){
//            sort = Sort.by(Sort.Direction.DESC,sortProperty);
//        }
//        Pageable pageable = PageRequest.of(page, size, sort);
//        return controlRepo.filtreEdition(name,pageable);
//    }

    public Boolean delControl(Long id){
        try {
            controlRepo.deleteById(id);
            return true;
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

}
