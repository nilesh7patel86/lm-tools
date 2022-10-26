package dev.np.tools.lmtools.model;

import dev.np.tools.lmtools.model.types.GradeType;
import lombok.*;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "equipped_gear")
public class EquippedGear {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private GradeType grade;

    @ManyToOne
    @JoinColumn(name = "jewel_slot_1")
    private EquippedJewel jewelSlot1;

    @ManyToOne
    @JoinColumn(name = "jewel_slot_2")
    private EquippedJewel jewelSlot2;

    @ManyToOne
    @JoinColumn(name = "jewel_slot_3")
    private EquippedJewel jewelSlot3;

    @ManyToOne
    @JoinColumn(name = "sigil")
    private EquippedSigil sigil;

    @ManyToOne
    @JoinColumn(name = "gear_id")
    private Gear gear;


}
