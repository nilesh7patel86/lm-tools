package dev.np.tools.lmtools.model.types.converters;

import dev.np.tools.lmtools.model.types.GearType;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class GearTypeConverter implements AttributeConverter<GearType, String> {

    @Override
    public String convertToDatabaseColumn(GearType gearType) {
        return gearType.desc();
    }

    @Override
    public GearType convertToEntityAttribute(String desc) {
        return GearType.of(desc);
    }
}
