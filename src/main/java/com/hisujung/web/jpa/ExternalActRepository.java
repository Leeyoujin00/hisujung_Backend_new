package com.hisujung.web.jpa;

import com.hisujung.web.entity.ExternalAct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExternalActRepository extends JpaRepository<ExternalAct, Long> {
}
