package com.example.dailydo.model.converter;

import com.example.dailydo.model.enums.ActionType;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class ActionTypeConverter implements AttributeConverter<ActionType, Integer> {

    @Override
    public Integer convertToDatabaseColumn(ActionType actionType) {
        return actionType == null ? null : actionType.getCode();
    }

    @Override
    public ActionType convertToEntityAttribute(Integer code) {
        return code == null ? null : ActionType.fromCode(code);
    }
}
