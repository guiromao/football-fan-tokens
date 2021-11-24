package co.trucom.footballfantokens.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import co.trucom.footballfantokens.models.FanToken;

public interface TokenRepository extends JpaRepository<FanToken, String> {

}
