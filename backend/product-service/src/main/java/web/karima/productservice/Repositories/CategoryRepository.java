package web.karima.productservice.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import web.karima.productservice.Entities.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
