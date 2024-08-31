package gg.falmer.data.repository;

import gg.falmer.data.model.ClientData;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientDataRepository extends CrudRepository<ClientData, Long> {
}
