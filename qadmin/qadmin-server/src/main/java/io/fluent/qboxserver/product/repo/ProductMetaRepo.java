package io.fluent.qboxserver.product.repo;

import io.fluent.qboxserver.product.model.ProductMeta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductMetaRepo extends JpaRepository<ProductMeta, Long>, JpaSpecificationExecutor<Long> {

  ProductMeta findProductMetaByName(String name);
}
