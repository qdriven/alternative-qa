package io.fluent.qboxserver.testcase.repo;

import io.fluent.qboxserver.testcase.model.TestCase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface TestCaseRepo extends JpaRepository<TestCase, Long>, JpaSpecificationExecutor<Long> {
}
