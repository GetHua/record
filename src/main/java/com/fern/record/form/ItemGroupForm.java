package com.fern.record.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class ItemGroupForm {

    private Long id;
    @NotEmpty
    private String name;
}
