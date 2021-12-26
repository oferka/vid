package org.ok.account.repository;

import org.ok.account.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, String> {

    List<Account> findBySymbol(String symbol);

    List<Account> findByName(String name);

    List<Account> findBySector(String sector);
}
