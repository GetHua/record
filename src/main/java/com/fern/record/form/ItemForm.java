package com.fern.record.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class ItemForm {

    private Long id;

    @NotEmpty
    private String name;

    private Long itemGroupId;
}
