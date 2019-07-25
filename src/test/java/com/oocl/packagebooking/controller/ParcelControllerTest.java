package com.oocl.packagebooking.controller;

import com.google.gson.Gson;
import com.oocl.packagebooking.constant.ParcelStatus;
import com.oocl.packagebooking.model.Parcel;
import com.oocl.packagebooking.repository.ParcelRepository;
import com.oocl.packagebooking.service.ParcelService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ParcelControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ParcelRepository parcelRepository;

    @MockBean
    private ParcelService parcelService;

    @Test
    public void should_get_All_parcels() throws Exception{

        Parcel firstParcel = new Parcel("Khai",13416135555L,ParcelStatus.UnMakingAppointment,new Date());
        Parcel secondParcel = new Parcel("Ground",13416133355L,ParcelStatus.UnMakingAppointment,new Date());
        ArrayList<Parcel> parcels = new ArrayList<>();
        parcels.add(firstParcel);
        parcels.add(secondParcel);
        when(parcelRepository.findAll()).thenReturn(parcels);
        mockMvc.perform(get("/parcels"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    public void should_get_parcels_filter_status() throws Exception{

        Parcel parcel = new Parcel("Ground",13416133355L,ParcelStatus.UnMakingAppointment, new Date());
        parcel.setStatus(ParcelStatus.MadeAppointment);
        ArrayList<Parcel> parcels = new ArrayList<>();
        parcels.add(parcel);
        when(parcelRepository.findAllParcelsByStatus(ParcelStatus.MadeAppointment)).thenReturn(parcels);
        mockMvc.perform(get("/parcels/{status}",ParcelStatus.MadeAppointment))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].status").value(ParcelStatus.MadeAppointment));
    }

    @Test
    public void should_return_success_when_update_parcels_status_success() throws Exception{
        Parcel parcel = new Parcel();
        parcel.setCustomName("Ke");
        parcel.setPhoneNumner(13416135454L);
        parcel.setStatus(ParcelStatus.TOKEN);
        when(parcelService.updateParcelStatus(any(Parcel.class))).thenReturn("update Successfully");
        mockMvc.perform(put("/parcels").contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(parcel)))
                .andExpect(status().isOk())
                .andExpect(content().string("update Successfully"));
    }

    @Test
    public void should_return_fail_when_update_parcels_status_fail() throws Exception{
        Parcel parcel = new Parcel();
        parcel.setCustomName("Ke");
        parcel.setPhoneNumner(13416135454L);
        parcel.setStatus(ParcelStatus.TOKEN);
        when(parcelService.updateParcelStatus(any(Parcel.class))).thenReturn("update fail");
        mockMvc.perform(put("/parcels").contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(parcel)))
                .andExpect(status().isOk())
                .andExpect(content().string("update fail"));
    }
}