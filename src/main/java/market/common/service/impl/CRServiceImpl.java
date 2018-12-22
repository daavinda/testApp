package market.common.service.impl;

import market.common.orm.repo.CRRepository;
import market.common.service.CRService;
import org.springframework.beans.factory.annotation.Autowired;

public class CRServiceImpl implements CRService {

    @Autowired
    private CRRepository crRepository;


}
