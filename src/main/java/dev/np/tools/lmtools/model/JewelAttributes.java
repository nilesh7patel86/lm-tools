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
@Table(name = "jewel_attributes")
public class JewelAttributes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private GradeType gradeType;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "jewelAttributes", cascade = CascadeType.ALL)
    private Set<JewelAttribute> attributes = new java.util.LinkedHashSet<>();

    @ManyToOne
    @JoinColumn(name = "jewel_id")
    private Jewel jewel;


}
