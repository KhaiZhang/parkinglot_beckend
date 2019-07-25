package com.oocl.packagebooking.service.serviceImple;


import com.oocl.packagebooking.constant.ParcelStatus;
import com.oocl.packagebooking.model.Parcel;
import com.oocl.packagebooking.repository.ParcelRepository;
import com.oocl.packagebooking.service.ParcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ParcelServiceImpl implements ParcelService {

    @Autowired
    private ParcelRepository parcelRepository;

    @Override
    public Parcel addNewParcel(Parcel parcel) {
        parcel.setStatus(ParcelStatus.UnMakingAppointment);
        Parcel newParcel = parcelRepository.save(parcel);
        return newParcel;
    }

    @Override
    public String updateParcelStatus(Parcel parcel) {
        int result = 0;
        if(parcel.getStatus() == ParcelStatus.MadeAppointment){
            result = parcelRepository.updateStatusToAppointment(parcel.getStatus(), parcel.getId(),new Date());
        }
        else if(parcel.getStatus() == ParcelStatus.TOKEN){
            result = parcelRepository.updateStatusToToken(parcel.getStatus(), parcel.getId());
        }

        if(result == 1){
            return "update Successfully";
        }
        else {return "update fail";}
    }
}
