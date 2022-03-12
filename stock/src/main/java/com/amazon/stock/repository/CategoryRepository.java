package com.amazon.stock.repository;

import com.amazon.stock.model.Category;
import com.amazon.stock.model.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends PagingAndSortingRepository<Category,Integer> {
    public Category findByCategoryIdAndStatusIsTrue(int categoryId);
    public List<Category> findAllByStatusIsTrue();
    public List<Product> findAllByStatusIsTrueAndCategoryId(Pageable pageable,int categoryId);
}
