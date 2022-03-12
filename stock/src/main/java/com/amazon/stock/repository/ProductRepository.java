package com.amazon.stock.repository;

import com.amazon.stock.model.Category;
import com.amazon.stock.model.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product,Integer> {
    public Product findByProductIdAndStatusIsTrue(int productId);
    public List<Product> findAllByStatusIsTrue(Pageable pageable);
    public List<Product> findAllByStoreIdAndStatusIsTrue(int storeId, Pageable pageable);
    public List<Product> findAllByCategoriesContainsAndStatusIsTrue(Category category, Pageable pageable);
}
