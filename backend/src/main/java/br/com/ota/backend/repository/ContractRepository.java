package br.com.ota.backend.repository;

import br.com.ota.backend.model.Contract;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContractRepository extends BaseRepository<Contract, Long> {

    List<Contract> findAll(Sort sort);

}
