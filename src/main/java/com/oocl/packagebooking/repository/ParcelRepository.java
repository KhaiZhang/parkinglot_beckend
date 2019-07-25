package com.oocl.packagebooking.repository;

import com.oocl.packagebooking.model.Parcel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParcelRepository extends JpaRepository<Parcel,Long> {
}
