package com.challenge.topcoder.javaspringchildhooddream.repository;

import com.challenge.topcoder.javaspringchildhooddream.entity.TrainEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TrainRepository extends JpaRepository<TrainEntity, Long> {

    List<TrainEntity> findBySharingTracks(boolean sharingTracks);

    List<TrainEntity> findByGradeCrossing(boolean gradeCrossing);

    List<TrainEntity> findByNameContaining(String name);
}
