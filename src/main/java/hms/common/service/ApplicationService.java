package hms.common.service;

import hms.common.orm.model.Application;

import java.util.List;

/**
 * Created by devinda on 4/17/18.
 */
public interface ApplicationService {

    List<Application> getAllApplications();

    void saveApplication(Application application);

    Application getApplicationById(Long id);

    Application findByName(String name);

}
