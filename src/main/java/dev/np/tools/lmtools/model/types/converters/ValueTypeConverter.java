package dev.np.tools.lmtools.model.types.converters;

import dev.np.tools.lmtools.model.types.ValueType;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class ValueTypeConverter implements AttributeConverter<ValueType, String> {

    @Override
    public String convertToDatabaseColumn(ValueType valueType) {
        return valueType.desc();
    }

    @Override
    public ValueType convertToEntityAttribute(String string) {
        return ValueType.of(string);
    }
}
