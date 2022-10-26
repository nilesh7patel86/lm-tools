package dev.np.tools.lmtools.model.types.converters;

import dev.np.tools.lmtools.model.types.GradeType;

import javax.persistence.Converter;
import javax.persistence.AttributeConverter;

@Converter(autoApply = true)
public class GradeTypeConverter implements AttributeConverter<GradeType, String> {

    @Override
    public String convertToDatabaseColumn(GradeType gradeType) {
        return gradeType.desc();
    }

    @Override
    public GradeType convertToEntityAttribute(String string) {
        return GradeType.of(string);
    }
}
