
package com.ipsoft.guideservice.repo;

import com.ipsoft.guideservice.entity.Guide;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface GuideREPO extends CrudRepository<Guide, String> {
    Guide findByGuideId(String guideId);
    List<Guide> findAll();
}
