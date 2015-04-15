package com.main.model.dto;

import java.util.List;

/**
 * PackALunch
 * Created by sadra on 12/9/14.
 */
public class DinerDto extends CustomerDto {

    private AccountDto accountDto;
    private List<MealDayDto> dinerSchedule;

    public AccountDto getAccountDto() {
        return accountDto;
    }

    public DinerDto setAccountDto(AccountDto accountDto) {
        this.accountDto = accountDto;
        return this;
    }

    public List<MealDayDto> getDinerSchedule() {
        return dinerSchedule;
    }

    public DinerDto setDinerSchedule(List<MealDayDto> dinerSchedule) {
        this.dinerSchedule = dinerSchedule;
        return this;
    }

    @Override
    public String toString() {
        return "DinerDto{" +
                "id=" + super.getId() +
                ", accountDto=" + accountDto +
                ", dinerSchedule=" + dinerSchedule +
                '}';
    }
}
