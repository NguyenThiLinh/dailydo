package com.example.dailydo.model.converter;

import com.example.dailydo.model.enums.Status;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class StatusConverter implements AttributeConverter<Status, Integer> {

    @Override
    public Integer convertToDatabaseColumn(Status status) {
        return status == null ? null : status.getCode();
    }

    @Override
    public Status convertToEntityAttribute(Integer code) {
        return code == null ? null : Status.fromCode(code);
    }
}
