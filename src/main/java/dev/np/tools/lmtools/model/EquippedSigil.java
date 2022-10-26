package dev.np.tools.lmtools.model;

import javax.persistence.*;

@Entity
@Table(name = "equipped_sigil")
public class EquippedSigil {
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
