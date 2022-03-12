package com.amazon.stock.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.MappedSuperclass;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
abstract class ShareEntity {
    private boolean status;
    private String modifyDate;
    private String createDate;
    private int modifyUserId;
    private int createUserId;
}
