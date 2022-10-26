package dev.np.tools.lmtools.model;

import dev.np.tools.lmtools.model.types.ValueType;
import lombok.*;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "gear_attribute")
public class GearAttribute {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;

    @Column(name = "attribute_value")
    private String value;

    private ValueType type;

    @ManyToOne
    @JoinColumn(name = "gear_attributes_id")
    private GearAttributes gearAttributes;


}
