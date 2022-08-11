package io.fluent.qboxserver.api.repo;

import io.fluent.qboxserver.api.model.Api;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ApiRepo extends JpaRepository<Api, Long>, JpaSpecificationExecutor<Long> {
}
