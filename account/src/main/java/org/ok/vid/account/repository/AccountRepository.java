package org.ok.vid.account.repository;

import org.ok.vid.account.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    List<Account> findBySymbol(String symbol);

    List<Account> findByName(String name);

    List<Account> findBySector(String sector);

    Boolean existsBySymbolAndNameAndSector(String symbol, String name, String sector);
}
