package dev.np.tools.lmtools.util;


import dev.np.tools.lmtools.model.*;
import dev.np.tools.lmtools.model.types.GearType;
import dev.np.tools.lmtools.model.types.GradeType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static dev.np.tools.lmtools.model.types.GradeType.*;
import static dev.np.tools.lmtools.model.types.ValueType.NUMBER;
import static dev.np.tools.lmtools.model.types.ValueType.PERCENTAGE;

@Slf4j
@Component
public class GearSetLoader implements ItemLoader<GearSet> {

    @Override
    public List<GearSet> load() throws IOException {
        List<String> strings = readLinesFrom("gearstats.txt");
        List<GearSet> sets = new ArrayList<>();
        GearSet gearSet = null;
        Gear gearItem = null;
        for (String s : strings) {
            String[] tokens = s.split(SPLIT_REGEX);
            if (s.startsWith("SetName|")) {
                if (gearSet != null) sets.add(gearSet);

                String setName = tokens[1];
                gearSet = GearSet.builder()
                        .name(setName)
                        .gearItems(new HashSet<>())
                        .build();
            } else if (s.startsWith("Main-hand|")
                    || s.startsWith("Helmet|")
                    || s.startsWith("Armor|")
                    || s.startsWith("Legs|")
                    || s.startsWith("Off-hand|")
                    || s.startsWith("Accessory|")) {

                assert gearSet != null;
                gearItem = Gear.builder()
                        .gearSet(gearSet)
                        .gearType(GearType.of(tokens[0]))
                        .name(tokens[1])
                        .build();
                gearItem.setAttributesByGrade(initGrades(gearItem));

            } else if (s.startsWith("Player Level")) {
                assert gearItem != null;
                gearItem.setRequiredLevel(Integer.parseInt(s.replace("Player Level ", "")));
            } else if (s.isEmpty()) {
                assert gearSet != null;
                gearSet.getGearItems().add(gearItem);
            } else {
                assert gearItem != null;
                addAttribute(gearItem, COMMON, tokens[0], tokens[1]);
                addAttribute(gearItem, UNCOMMON, tokens[0], tokens[2]);
                addAttribute(gearItem, RARE, tokens[0], tokens[3]);
                addAttribute(gearItem, EPIC, tokens[0], tokens[4]);
                addAttribute(gearItem, LEGENDARY, tokens[0], tokens[5]);
                addAttribute(gearItem, MYTHIC, tokens[0], tokens[6]);
            }
        }
        return sets;
    }

    private void addAttribute(Gear gearItem, GradeType gradeType, String attributeName, String value) {
        GearAttributes gearAttributes = gearItem.getAttributesByGrade().stream().filter(grade -> gradeType.equals(grade.getGrade()))
                .findFirst().orElseThrow(() -> new RuntimeException(""));
        gearAttributes
                .getAttributes().add(GearAttribute.builder()
                        .name(attributeName)
                        .gearAttributes(gearAttributes)
                        .type(value.contains("%") ? PERCENTAGE : NUMBER)
                        .value(parseValue(value))
                        .build());
    }

    private Set<GearAttributes> initGrades(Gear gearItem) {
        Set<GearAttributes> grades = new HashSet<>();
        grades.add(GearAttributes.builder().gear(gearItem).grade(COMMON).attributes(new HashSet<>()).build());
        grades.add(GearAttributes.builder().gear(gearItem).grade(UNCOMMON).attributes(new HashSet<>()).build());
        grades.add(GearAttributes.builder().gear(gearItem).grade(RARE).attributes(new HashSet<>()).build());
        grades.add(GearAttributes.builder().gear(gearItem).grade(EPIC).attributes(new HashSet<>()).build());
        grades.add(GearAttributes.builder().gear(gearItem).grade(LEGENDARY).attributes(new HashSet<>()).build());
        grades.add(GearAttributes.builder().gear(gearItem).grade(MYTHIC).attributes(new HashSet<>()).build());
        return grades;
    }
}
