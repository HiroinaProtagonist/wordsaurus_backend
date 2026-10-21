package com.hiroinaprotagonist.wordsaurus.db.service;

import com.hiroinaprotagonist.wordsaurus.db.model.Cloud;
import com.hiroinaprotagonist.wordsaurus.db.repo.CloudRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CloudServiceImpl {
    private CloudRepository cloudRepository;

    @Override
    public Cloud findCloudByTitle(String title) {
        Optional<Cloud> cloudOpt = cloudRepository.findCloudByTitle(title);
    }


}
