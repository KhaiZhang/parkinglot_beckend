package com.oocl.packagebooking.repository;

import com.oocl.packagebooking.model.Parcel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface ParcelRepository extends JpaRepository<Parcel,Long> {

    @Transactional
    @Modifying
    @Query("update Parcel set status = :status where id = :id")
    public int updateStatusById(@Param(value = "status") int status,@Param(value = "id") long id);
}
