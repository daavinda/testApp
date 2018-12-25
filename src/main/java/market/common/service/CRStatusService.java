package market.common.service;


import market.common.orm.model.CRStatus;

import java.util.Date;

public interface CRStatusService {

    void save(CRStatus crStatus);

    CRStatus findByDate(Date date);

    void updateStatus();

    void finishCr();

    Boolean getTodayCRStatus();
}
