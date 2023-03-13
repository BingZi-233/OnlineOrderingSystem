package online.bingzi.usermodule.common.repository;

import online.bingzi.usermodule.common.entity.Dictionary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface DictionaryRepository extends JpaRepository<Dictionary, Long> {
    List<Dictionary> findByNameInIgnoreCase(Collection<String> names);
}