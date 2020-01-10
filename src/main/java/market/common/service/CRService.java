package market.common.service;


import market.common.orm.model.CR;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface CRService {

    List<CR> findAll();

    void save(Long itemId, BigDecimal unitPrice, BigDecimal quantity);

    void remove(CR cr);

    CR findById(Long id);

    void finishCr();

    List<CR> findByDate(Date date);

    List<CR> findByDateBetween(Date from, Date to);

    void addToFreezer(Long item, BigDecimal qty);
}
