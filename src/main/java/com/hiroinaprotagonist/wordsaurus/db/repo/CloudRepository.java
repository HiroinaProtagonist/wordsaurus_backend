package com.hiroinaprotagonist.wordsaurus.db.repo;

import com.hiroinaprotagonist.wordsaurus.db.model.Cloud;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CloudRepository extends JpaRepository<Cloud, Integer> {
    Cloud findCloudByTitle(String title);

    Cloud findCloudByCloudId(UUID cloudId);

    List<Cloud> findAllClouds();

    Cloud save(Cloud cloud);

}
