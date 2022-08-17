package io.fluent.qboxserver.api.repo;

import io.fluent.qboxserver.api.model.RemoteService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface RemoteServiceRepo extends JpaRepository<RemoteService, Long>, JpaSpecificationExecutor<Long> {
}
