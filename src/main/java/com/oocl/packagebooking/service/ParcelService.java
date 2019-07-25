package com.oocl.packagebooking.service;


import com.oocl.packagebooking.model.Parcel;
import org.springframework.stereotype.Service;


public interface ParcelService {
    public Parcel addNewParcel(Parcel parcel);
    public String updateParcelStatus(Parcel parcel);
}
