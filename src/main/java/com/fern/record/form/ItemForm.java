package com.fern.record.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class ItemForm {

    private String id;

    @NotEmpty
    private String name;

    private String itemGroupId;
}
