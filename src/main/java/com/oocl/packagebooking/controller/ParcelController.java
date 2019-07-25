package com.oocl.packagebooking.controller;

import com.oocl.packagebooking.constant.ParcelStatus;
import com.oocl.packagebooking.model.Parcel;
import com.oocl.packagebooking.repository.ParcelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ParcelController {

    @Autowired
    private ParcelRepository parcelRepository;

    @GetMapping("/parcels")
    public ResponseEntity getAllParcels(){
        return ResponseEntity.ok(parcelRepository.findAll());
    }


    @PostMapping("/parcels")
    public ResponseEntity CreateNewParcels(@RequestBody Parcel parcel) throws Exception{
        SimpleDateFormat simpleDate = new SimpleDateFormat("YYYY-MM-DD HH:MM:SS");
        parcel.setCreateTime(simpleDate.parse(new Date().toString()));
        parcel.setStatus(ParcelStatus.UnMakingAppointment);
        Parcel newParcel = parcelRepository.save(parcel);
        return ResponseEntity.ok(newParcel);
    }

    @GetMapping("/parcels/{status}")
    public ResponseEntity getParcelsFilterByStatus(@PathVariable(value = "status")int stauts){
        List<Parcel> parcels = parcelRepository.findAll();
        List<Parcel> findParcles = parcels.stream().filter(parcel -> parcel.getStatus() == stauts).collect(Collectors.toList());
        return ResponseEntity.ok(findParcles);
    }
}
