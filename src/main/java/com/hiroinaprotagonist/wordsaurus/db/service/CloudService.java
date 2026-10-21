package com.hiroinaprotagonist.wordsaurus.db.service;

import com.hiroinaprotagonist.wordsaurus.db.model.Cloud;

import java.util.List;
import java.util.UUID;

public interface CloudService {
    Cloud findCloudByTitle(String title);
    Cloud findCloudByCloudId(UUID cloudId);

    List<Cloud> findAllClouds();

    Cloud save(Cloud cloud);

    Cloud update(Cloud cloud);

    Cloud deleteByTitle(String title);

    Cloud deleteById(Integer id);
}
