package com.hisujung.web.jpa.chating;

import com.hisujung.web.entity.chating.ChatingJoinInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatingJoinInfoRepository extends JpaRepository<ChatingJoinInfo, Long> {
}
