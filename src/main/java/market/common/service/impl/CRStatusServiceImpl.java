package market.common.service.impl;

import market.common.orm.model.CRStatus;
import market.common.orm.repo.CRStatusRepository;
import market.common.service.CRStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CRStatusServiceImpl implements CRStatusService {

    @Autowired
    private CRStatusRepository crStatusRepository;


    @Override
    public void save(CRStatus crStatus) {
        crStatusRepository.saveAndFlush(crStatus);
    }

    @Override
    public CRStatus findByDate(Date date) {
        return crStatusRepository.findByDate(date);
    }

    @Override
    public void updateStatus() {
        CRStatus crStatus = findByDate(new Date());
        if (crStatus == null) {
            crStatus = new CRStatus();
            crStatus.setDate(new Date());
            crStatus.setCrDone(false);
        } else {
            crStatus.setCrDone(false);
        }
        save(crStatus);
    }

    @Override
    public void finishCr() {
        CRStatus crStatus = findByDate(new Date());
        if (crStatus == null) {
            crStatus = new CRStatus();
            crStatus.setDate(new Date());
            crStatus.setCrDone(true);
        } else {
            crStatus.setCrDone(true);
        }
        save(crStatus);
    }

    @Override
    public Boolean getTodayCRStatus() {
        CRStatus crStatus = findByDate(new Date());
        if (crStatus != null) {
            return crStatus.isCrDone();
        } else {
            return false;
        }
    }
}
