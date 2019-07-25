package com.oocl.packagebooking.controller;

import com.oocl.packagebooking.constant.ParcelStatus;
import com.oocl.packagebooking.model.Parcel;
import com.oocl.packagebooking.repository.ParcelRepository;
import com.oocl.packagebooking.service.ParcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin("http://localhost:8082")
public class ParcelController {

    @Autowired
    private ParcelRepository parcelRepository;

    @Autowired
    private ParcelService parcelServiceImpl;

    @GetMapping("/parcels")
    public ResponseEntity getAllParcels(){
        return ResponseEntity.ok(parcelRepository.findAll());
    }


    @PostMapping("/parcels")
    public ResponseEntity CreateNewParcels(@RequestBody Parcel parcel) throws Exception{
        return ResponseEntity.ok(parcelServiceImpl.addNewParcel(parcel));
    }

    @GetMapping("/parcels/{status}")
    public ResponseEntity getParcelsFilterByStatus(@PathVariable(value = "status")int stauts){
        List<Parcel> allParcelsByStatus = parcelRepository.findAllParcelsByStatus(stauts);
        return ResponseEntity.ok(allParcelsByStatus);
    }

    @PutMapping("/parcels")
    public ResponseEntity changeParcelStatus(@RequestBody Parcel parcel){
        return ResponseEntity.ok(parcelServiceImpl.updateParcelStatus(parcel));
    }
}
