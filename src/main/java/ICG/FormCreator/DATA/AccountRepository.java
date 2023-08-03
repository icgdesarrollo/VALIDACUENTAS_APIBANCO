package ICG.FormCreator.DATA;

import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;

@Repository
public interface AccountRepository  extends CrudRepository<Account, Integer> {
    public Iterable<Account> findByAccountAndProductAndCurrency(String account,int product, int currency);
}