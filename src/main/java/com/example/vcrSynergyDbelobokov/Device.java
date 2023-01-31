package com.example.vcrSynergyDbelobokov;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Device {
    int id;
    String typeDevice;
    String manufacturer;
    String owner;
    String statusRepair;
    String note;
}

