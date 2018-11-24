package market.common.service.impl;

import market.common.orm.model.Application;
import market.common.orm.repo.ApplicationRepository;
import market.common.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by devinda on 4/17/18.
 */
@Service
public class ApplicationServiceImpl implements ApplicationService {

    @Autowired
    private ApplicationRepository applicationRepository;

    @Override
    public List<Application> getAllApplications() {
        return applicationRepository.findAll();
    }

    @Override
    public void saveApplication(Application application) {
        applicationRepository.saveAndFlush(application);
    }

    @Override
    public Application getApplicationById(Long id) {
        if (id == null || id == 0) return new Application();
        return applicationRepository.findOne(id);
    }

    @Override
    public Application findByName(String name) {
        return applicationRepository.findByApplicationName(name);
    }

}
