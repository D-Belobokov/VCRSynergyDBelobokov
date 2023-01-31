package com.example.vcrSynergyDbelobokov;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class User {
    int idUser;
    String firstName;
    String lastName;
    String email;
}
