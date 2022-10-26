package dev.np.tools.lmtools.model;

import dev.np.tools.lmtools.model.types.JewelCategory;
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
@Table(name = "jewel")
public class Jewel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;

    private JewelCategory category;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "jewel", cascade = CascadeType.ALL)
    private Set<JewelAttributes> attributes = new java.util.LinkedHashSet<>();

}
