package com.example.dailydo.model.converter;

import com.example.dailydo.model.enums.Priority;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class PriorityConverter implements AttributeConverter<Priority, Integer> {

    @Override
    public Integer convertToDatabaseColumn(Priority priority) {
        return priority == null ? null : priority.getCode();
    }

    @Override
    public Priority convertToEntityAttribute(Integer code) {
        return code == null ? null : Priority.fromCode(code);
    }
}
