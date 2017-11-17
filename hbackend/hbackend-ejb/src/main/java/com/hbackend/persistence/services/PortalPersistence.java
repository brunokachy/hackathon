package com.hbackend.persistence.services;

import com.hbackend.persistence.entity.portal.PortalUser;
import com.hbackend.persistence.entity.portal.State_;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class PortalPersistence {

    @PersistenceContext(unitName = "HBackendPU")
    private EntityManager em;

    public <T> T create(T t) {
        em.persist(t);
        em.flush();
        return t;
    }

    public <T> T find(Class<T> type, Object id) {
        return em.find(type, id);
    }

    public <T> void delete(T t) {
        em.remove(em.merge(t));
    }

    public <T> T update(T t) {
        return em.merge(t);
    }

    //Portal User Business Method
    public PortalUser getActiveUserByEmailAddress(String emailAddress) {
        PortalUser user = null;
        try {
            user = em.createQuery("SELECT o FROM PortalUser o WHERE o.emailAddress =:emailAddress AND o.active = TRUE", PortalUser.class)
                    .setParameter("emailAddress", emailAddress)
                    .getSingleResult();
        } catch (Exception e) {
            e.getLocalizedMessage();
        }
        return user;
    }

    public PortalUser getUserByEmailAddress(String emailAddress) {
        PortalUser user = null;
        try {
            user = em.createQuery("SELECT o FROM PortalUser o WHERE o.emailAddress =:emailAddress", PortalUser.class)
                    .setParameter("emailAddress", emailAddress)
                    .getSingleResult();
        } catch (Exception e) {
            e.getLocalizedMessage();
        }
        return user;
    }

    public List<PortalUser> getAllInActiveUser() {
        List<PortalUser> users = new ArrayList<>();
        try {
            users = em.createQuery("SELECT o FROM PortalUser o WHERE o.active = FALSE", PortalUser.class)
                    .getResultList();
        } catch (Exception e) {
            e.getLocalizedMessage();
        }
        return users;
    }

    public List<PortalUser> getAllActiveUser() {
        List<PortalUser> users = new ArrayList<>();
        try {
            users = em.createQuery("SELECT o FROM PortalUser o WHERE o.active = TRUE", PortalUser.class)
                    .getResultList();
        } catch (Exception e) {
            e.getLocalizedMessage();
        }
        return users;
    }

    public List<PortalUser> getAllUser() {
        List<PortalUser> users = new ArrayList<>();
        try {
            users = em.createQuery("SELECT o FROM PortalUser o", PortalUser.class)
                    .getResultList();
        } catch (Exception e) {
            e.getLocalizedMessage();
        }
        return users;
    }

    public State_ getStateByName(String stateName) {
        State_ s = null;
        try {
            s = em.createQuery("SELECT o FROM State_ o WHERE o.name =:stateName", State_.class)
                    .setParameter("stateName", stateName)
                    .getSingleResult();
        } catch (Exception e) {
            e.getLocalizedMessage();
        }
        return s;
    }

}
