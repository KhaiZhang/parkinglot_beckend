package com.oocl.packagebooking.service.serviceImple;

import com.oocl.packagebooking.constant.ParcelStatus;
import com.oocl.packagebooking.model.Parcel;
import com.oocl.packagebooking.repository.ParcelRepository;
import com.oocl.packagebooking.service.ParcelService;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ParcelServiceImplTest {

    @MockBean
    private ParcelRepository parcelRepository;

    @Autowired
    private ParcelService parcelServiceImpl;

    @Test
    public void should_add_new_parcel() {
        Parcel parcel = new Parcel();
        parcel.setCustomName("khai");
        parcel.setPhoneNumner(123124141L);
        when(parcelRepository.save(any(Parcel.class))).thenReturn(parcel);
        Parcel savedParcel = parcelServiceImpl.addNewParcel(parcel);
        Assertions.assertEquals(savedParcel,parcel);
    }

    @Test
    public void should_update_parcel_status() {

    }
}