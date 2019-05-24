package xyz.yimb.kesheweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.yimb.kesheweb.entity.College;

public interface CollegeRepository extends JpaRepository<College,Integer> {

}
