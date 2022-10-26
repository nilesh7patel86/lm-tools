package dev.np.tools.lmtools.model;

import javax.persistence.*;

@Entity
@Table(name = "equipped_jewel")
public class EquippedJewel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
