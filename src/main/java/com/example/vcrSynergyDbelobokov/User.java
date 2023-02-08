package com.example.vcrSynergyDbelobokov;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.testng.annotations.Test;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Test
public class User {
    int idUser;
    String firstName;
    String lastName;
    String email;
}
