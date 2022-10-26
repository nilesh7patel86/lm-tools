package dev.np.tools.lmtools.model.types.converters;


import dev.np.tools.lmtools.model.types.JewelCategory;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class JewelCategoryConverter implements AttributeConverter<JewelCategory, String> {

    @Override
    public String convertToDatabaseColumn(JewelCategory jewelCategory) {
        return jewelCategory.desc();
    }

    @Override
    public JewelCategory convertToEntityAttribute(String string) {
        return JewelCategory.of(string);
    }
}
