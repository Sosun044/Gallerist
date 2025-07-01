package com.sosunmuhammed.gallerist.service.impl;

import com.sosunmuhammed.gallerist.dto.DtoAddress;
import com.sosunmuhammed.gallerist.dto.DtoGallerist;
import com.sosunmuhammed.gallerist.dto.DtoGalleristUI;
import com.sosunmuhammed.gallerist.exception.BaseException;
import com.sosunmuhammed.gallerist.exception.ErrorMessage;
import com.sosunmuhammed.gallerist.exception.MessageType;
import com.sosunmuhammed.gallerist.model.Address;
import com.sosunmuhammed.gallerist.model.Gallerist;
import com.sosunmuhammed.gallerist.repository.AddressRepository;
import com.sosunmuhammed.gallerist.repository.GalleristRepository;
import com.sosunmuhammed.gallerist.service.IGalleristService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GalleristServiceImpl implements IGalleristService {

    @Autowired
    private GalleristRepository galleristRepository;

    @Autowired
    private AddressRepository addressRepository;

    private Gallerist createGallerist(DtoGalleristUI dtoGalleristUI){
        Optional<Address> optAddress = addressRepository.findById(dtoGalleristUI.getAddressId());
        if (optAddress.isEmpty()){
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST,dtoGalleristUI.getAddressId().toString()));
        }
        Gallerist gallerist = new Gallerist();
        gallerist.setCreateTime(new Date());
        BeanUtils.copyProperties(dtoGalleristUI,gallerist);
        gallerist.setAddress(optAddress.get());
        return gallerist;

    }

    @Override
    public DtoGallerist save(DtoGalleristUI dtoGalleristUI) {
        DtoGallerist dtoGallerist = new DtoGallerist();
        DtoAddress dtoAddress = new DtoAddress();
        Gallerist savedGallerist = galleristRepository.save(createGallerist(dtoGalleristUI));

        BeanUtils.copyProperties(savedGallerist,dtoGallerist);
        BeanUtils.copyProperties(savedGallerist.getAddress(),dtoAddress);
        dtoGallerist.setAddress(dtoAddress);

        return dtoGallerist;
    }

    @Override
    public List<DtoGallerist> getAll() {
        List<Gallerist> gallerists = galleristRepository.findAll();

        return gallerists.stream().map(gallerist -> {
            DtoAddress dtoAddress = new DtoAddress();
            DtoGallerist dtoGallerist = new DtoGallerist();
            BeanUtils.copyProperties(gallerist,dtoGallerist);
            BeanUtils.copyProperties(gallerist.getAddress(),dtoAddress);
            dtoGallerist.setAddress(dtoAddress);
            return dtoGallerist;
        }).collect(Collectors.toList());
    }

    @Override
    public DtoGallerist findGallerist(Long id) {
        Optional<Gallerist> optGallerist= galleristRepository.findById(id);
        if (optGallerist.isEmpty()){
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST,id.toString()));
        }
        DtoGallerist dtoGallerist = new DtoGallerist();
        DtoAddress dtoAddress = new DtoAddress();
        BeanUtils.copyProperties(optGallerist.get(),dtoGallerist);
        BeanUtils.copyProperties(optGallerist.get().getAddress(),dtoAddress);
        dtoGallerist.setAddress(dtoAddress);
        return dtoGallerist;
    }

    @Override
    public void deleteGallerist(Long id) {
        Gallerist gallerist = galleristRepository.findById(id).
                orElseThrow(()->new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST,id.toString())));
        galleristRepository.delete(gallerist);
    }


    @Override
    public DtoGallerist update(Long id, DtoGalleristUI dtoGalleristUI) {
         Gallerist gallerist = galleristRepository.findById(id).
                 orElseThrow(()-> new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST,id.toString())));
         gallerist.setFirstName(dtoGalleristUI.getFirstName());
         gallerist.setLastName(dtoGalleristUI.getLastName());

         Gallerist savedGallerist = galleristRepository.save(gallerist);
         DtoGallerist dtoGallerist = new DtoGallerist();
         BeanUtils.copyProperties(savedGallerist,dtoGallerist);

        return dtoGallerist;
    }
}
