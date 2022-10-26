package dev.np.tools.lmtools.model;

import dev.np.tools.lmtools.model.types.GradeType;
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
@Table(name = "gear_attributes")
public class GearAttributes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private GradeType grade;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "gearAttributes",cascade = CascadeType.ALL)
    private Set<GearAttribute> attributes = new java.util.LinkedHashSet<>();

    @ManyToOne
    @JoinColumn(name = "gear_id")
    private Gear gear;
}
