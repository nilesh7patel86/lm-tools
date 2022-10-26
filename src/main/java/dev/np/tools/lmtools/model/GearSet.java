package dev.np.tools.lmtools.model;

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
@Table(name = "gear_set")
public class GearSet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "gearSet",cascade = CascadeType.ALL)
    private Set<Gear> gearItems = new java.util.LinkedHashSet<>();


}
