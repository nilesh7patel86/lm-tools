package dev.np.tools.lmtools.model;

import lombok.*;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "equipped_gear_set")
public class EquippedGearSet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;

    @OneToOne
    @JoinColumn(name = "mainHand")
    private EquippedGear mainHand;

    @OneToOne
    @JoinColumn(name = "helmet")
    private EquippedGear helmet;

    @OneToOne
    @JoinColumn(name = "armor")
    private EquippedGear armor;

    @OneToOne
    @JoinColumn(name = "legs")
    private EquippedGear legs;

    @OneToOne
    @JoinColumn(name = "offHand")
    private EquippedGear offHand;

    @OneToOne
    @JoinColumn(name = "accessory_1")
    private EquippedGear accessory_1;

    @OneToOne
    @JoinColumn(name = "accessory_2")
    private EquippedGear accessory_2;

    @OneToOne
    @JoinColumn(name = "accessory_3")
    private EquippedGear accessory_3;

}
