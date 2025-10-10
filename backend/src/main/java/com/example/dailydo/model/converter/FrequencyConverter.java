package com.example.dailydo.model.converter;

import com.example.dailydo.model.enums.Frequency;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class FrequencyConverter implements AttributeConverter<Frequency, Integer> {
    @Override
    public Integer convertToDatabaseColumn(Frequency frequency) {
        return frequency == null ? null : frequency.getCode();
    }

    @Override
    public Frequency convertToEntityAttribute(Integer code) {
        return code == null ? null : Frequency.fromCode(code);
    }
}
