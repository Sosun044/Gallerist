package com.sosunmuhammed.gallerist.service.impl;

import com.sosunmuhammed.gallerist.dto.DtoGallerist;
import com.sosunmuhammed.gallerist.dto.DtoGalleristUI;
import com.sosunmuhammed.gallerist.exception.BaseException;
import com.sosunmuhammed.gallerist.exception.ErrorMessage;
import com.sosunmuhammed.gallerist.exception.MessageType;
import com.sosunmuhammed.gallerist.mapper.GalleristMapper;
import com.sosunmuhammed.gallerist.model.Address;
import com.sosunmuhammed.gallerist.model.Gallerist;
import com.sosunmuhammed.gallerist.repository.AddressRepository;
import com.sosunmuhammed.gallerist.repository.GalleristRepository;
import com.sosunmuhammed.gallerist.service.IGalleristService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GalleristServiceImpl implements IGalleristService {


    private final GalleristRepository galleristRepository;

    private final AddressRepository addressRepository;

    public GalleristServiceImpl(GalleristRepository galleristRepository, AddressRepository addressRepository) {
        this.galleristRepository = galleristRepository;
        this.addressRepository = addressRepository;
    }

    private Gallerist createGallerist(DtoGalleristUI dtoGalleristUI){

        Address address = addressRepository.findById(dtoGalleristUI.getAddressId()).orElseThrow(
                ()->new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST,dtoGalleristUI.getAddressId().toString()))
        );
        Gallerist gallerist = GalleristMapper.toEntity(dtoGalleristUI);
        gallerist.setAddress(address);
        return gallerist;

    }

    @Override
    public DtoGallerist save(DtoGalleristUI dtoGalleristUI) {
        Gallerist savedGallerist = galleristRepository.save(createGallerist(dtoGalleristUI));
        return GalleristMapper.toDto(savedGallerist);
    }

    @Override
    public List<DtoGallerist> getAll() {
        return galleristRepository.findAll().stream().map(
                GalleristMapper::toDto
        ).collect(Collectors.toList());
    }

    @Override
    public DtoGallerist findGallerist(Long id) {
        Gallerist gallerist= galleristRepository.findById(id).orElseThrow(()->
                new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST,id.toString())));
        return GalleristMapper.toDto(gallerist);
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
        Address address = addressRepository.findById(dtoGalleristUI.getAddressId())
                .orElseThrow(() -> new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, dtoGalleristUI.getAddressId().toString())));
        GalleristMapper.updateGallerist(gallerist,dtoGalleristUI);
        gallerist.setAddress(address);
         Gallerist savedGallerist = galleristRepository.save(gallerist);
        return GalleristMapper.toDto(savedGallerist);
    }
}
