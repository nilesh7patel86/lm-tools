package dev.np.tools.lmtools.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import dev.np.tools.lmtools.model.GearSet;
import dev.np.tools.lmtools.model.Jewel;
import dev.np.tools.lmtools.repository.GearSetRepository;
import dev.np.tools.lmtools.repository.JewelRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class DataLoader implements ApplicationRunner {
    private final ItemLoader<Jewel> jewelLoader;
    private final JewelRepository jewelRepository;
    private final ItemLoader<GearSet> gearSetLoader;
    private final GearSetRepository gearSetRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        List<Jewel> jewels = jewelLoader.load();
        List<GearSet> gearSets = gearSetLoader.load();

        if (jewels != null) {
            jewels.forEach(jewel -> {
                if (!jewelRepository.exists(Example.of(jewel))) {
                    jewelRepository.save(jewel);
                }
            });

            findAll(jewelRepository);
        }

        if (gearSets != null) {
            gearSets.forEach(gearSet -> {
                if (!gearSetRepository.exists(Example.of(gearSet))) {
                    gearSetRepository.save(gearSet);
                }
            });
            findAll(gearSetRepository);
        }
        Jewel saber_jewel = jewelRepository.findByName("Saber Jewel").orElseThrow(() -> new RuntimeException("|Saber Jewel| not found"));
        Jewel infantry_def_jewel = jewelRepository.findByName("Infantry DEF Jewel").orElseThrow(() -> new RuntimeException("|Infantry DEF Jewel| not found"));
        Jewel terror_jewel = jewelRepository.findByName("Terror Jewel").orElseThrow(() -> new RuntimeException("|Terror Jewel| not found"));


    }

    private void findAll(JpaRepository repository) throws JsonProcessingException {
        List all = repository.findAll();
        log.info(String.valueOf(all.size()));
        //all.forEach(o -> log.info(o.toString()));
//        writeYaml(all);

    }
}
