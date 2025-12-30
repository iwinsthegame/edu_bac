package com.rk.edu.repositories;



import com.rk.edu.model.TestSeries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestSeriesRepository extends JpaRepository<TestSeries, Long> {

    boolean existsByTitle(String title);
}
