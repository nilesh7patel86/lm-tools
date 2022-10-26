package dev.np.tools.lmtools.repository;

import dev.np.tools.lmtools.model.Jewel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JewelRepository extends JpaRepository<Jewel,Long> {
    Optional<Jewel> findByName(String s);
}
