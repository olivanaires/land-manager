package br.com.ota.backend.repository;

import br.com.ota.backend.model.Role;
import br.com.ota.backend.model.enums.RoleName;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends BaseRepository<Role, Long> {

    Role findByName(RoleName roleName);

}
