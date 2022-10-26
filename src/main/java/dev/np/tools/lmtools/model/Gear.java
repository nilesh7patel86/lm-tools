package dev.np.tools.lmtools.model;

import dev.np.tools.lmtools.model.types.GearType;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "gear")
public class Gear {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;

    private GearType gearType;

    private Integer requiredLevel;



    @OneToMany(fetch = FetchType.EAGER, mappedBy = "gear",cascade = CascadeType.ALL)
    private Set<GearAttributes> attributesByGrade = new java.util.LinkedHashSet<>();
    @ManyToOne
    @JoinColumn(name = "gear_set_id")
    private GearSet gearSet;


}
