package dev.np.tools.lmtools.util;


import dev.np.tools.lmtools.model.Jewel;
import dev.np.tools.lmtools.model.JewelAttribute;
import dev.np.tools.lmtools.model.JewelAttributes;
import dev.np.tools.lmtools.model.types.GradeType;
import dev.np.tools.lmtools.model.types.JewelCategory;
import dev.np.tools.lmtools.model.types.ValueType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.*;

import static dev.np.tools.lmtools.model.types.GradeType.*;
import static dev.np.tools.lmtools.model.types.ValueType.NUMBER;
import static dev.np.tools.lmtools.model.types.ValueType.PERCENTAGE;

@Slf4j
@Component
public class JewelLoader implements ItemLoader<Jewel> {


    @Override
    public List<Jewel> load() throws IOException {
        List<String> strings = readLinesFrom("jewelstats.txt");
        //strings.forEach(log::info);
        Map<String, Jewel> jewels = new HashMap<>();
        for (String s : strings) {
            String[] split = s.split(SPLIT_REGEX);
            if (jewels.containsKey(split[0])) {
                addToJewel(jewels.get(split[0]), split);
            } else {
                jewels.put(split[0], buildJewel(split));
                addToJewel(jewels.get(split[0]), split);
            }
        }
        return new ArrayList<>(jewels.values());
    }

    private Jewel buildJewel(String[] split) {

        Jewel jewel = Jewel.builder()
                .name(split[0])
                .category(JewelCategory.of(split[1]))
                .build();
        jewel.setAttributes(initGrades(jewel));
        return jewel;
    }

    private Set<JewelAttributes> initGrades(Jewel jewel) {
        Set<JewelAttributes> grades = new HashSet<>();
        grades.add(JewelAttributes.builder().jewel(jewel).gradeType(COMMON).attributes(new HashSet<>()).build());
        grades.add(JewelAttributes.builder().jewel(jewel).gradeType(UNCOMMON).attributes(new HashSet<>()).build());
        grades.add(JewelAttributes.builder().jewel(jewel).gradeType(RARE).attributes(new HashSet<>()).build());
        grades.add(JewelAttributes.builder().jewel(jewel).gradeType(EPIC).attributes(new HashSet<>()).build());
        grades.add(JewelAttributes.builder().jewel(jewel).gradeType(LEGENDARY).attributes(new HashSet<>()).build());
        return grades;
    }

    private void addToJewel(Jewel jewel, String[] split) {
        String name = split[2];
        ValueType valueType = "percent".equals(split[3]) ? PERCENTAGE : NUMBER;
        addGradeAttribute(jewel, COMMON, name, valueType, split[4]);
        addGradeAttribute(jewel, UNCOMMON, name, valueType, split[5]);
        addGradeAttribute(jewel, RARE, name, valueType, split[6]);
        addGradeAttribute(jewel, EPIC, name, valueType, split[7]);
        addGradeAttribute(jewel, LEGENDARY, name, valueType, split[8]);

    }

    private void addGradeAttribute(Jewel jewel, GradeType gradeType, String name, ValueType valueType, String value) {

        JewelAttributes jewelAttributes = jewel.getAttributes().stream()
                .filter(grade -> gradeType.equals(grade.getGradeType()))
                .findFirst().orElseThrow(() -> new RuntimeException(""));
        jewelAttributes
                .getAttributes()
                .add(JewelAttribute.builder()
                        .name(name)
                        .jewelAttributes(jewelAttributes)
                        .type(valueType)
                        .value(parseValue(value))
                        .build());

    }

}
