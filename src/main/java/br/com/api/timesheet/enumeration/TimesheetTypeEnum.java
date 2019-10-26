package br.com.api.timesheet.enumeration;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TimesheetTypeEnum {

    REGULAR("RG", "Normal"),
    HOLIDAY("HO", "Feriado"),
    DAY_OFF("DO", "Folga"),
    VACATION("VC", "Férias");

    private final String code;
    private final String description;
}
