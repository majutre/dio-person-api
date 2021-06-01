package com.digitalinnovationone.personrestapi.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PhoneType {

    HOME,
    MOBILE,
    COMMERCIAL;

    private final String description = null;

}
