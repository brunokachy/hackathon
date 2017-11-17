/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hbackend.persistence.services;

import com.hbackend.persistence.entity.portal.State_;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.LocalBean;
import javax.ejb.Startup;

/**
 *
 * @author Onyedika Okafor
 */
@Singleton
@LocalBean
@Startup
public class PortalInit {

    @EJB
    private PortalPersistence portalPersistence;

    @PostConstruct
    public void init() {
        setUpStates();
    }

    @PreDestroy
    public void destroy() {
        // Do stuff during webapp's shutdown.
    }

    private void setUpStates() {
        State_ s1 = portalPersistence.getStateByName(ProjectConstant.ABIA);
        if (s1 == null) {
            try {
                s1 = new State_();
                s1.setName(ProjectConstant.ABIA);
                s1.setCapital("Umuahia");
                portalPersistence.create(s1);
            } catch (Exception e) {
                System.err.println("setUpStates Error :::: " + e.getLocalizedMessage());
            }
        }

        State_ s2 = portalPersistence.getStateByName(ProjectConstant.ADAMAWA);
        if (s2 == null) {
            try {
                s2 = new State_();
                s2.setName(ProjectConstant.ANAMBRA);
                s2.setCapital("Awka");
                portalPersistence.create(s2);
            } catch (Exception e) {
                System.err.println("setUpStates Error :::: " + e.getLocalizedMessage());
            }
        }
    }
}
