/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com504.jpaexample1.impl.dao.jpa;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.solent.com504.jpaexample1.model.dao.AppointmentDAO;
import org.solent.com504.jpaexample1.model.dto.Appointment;
import org.solent.com504.jpaexample1.model.dto.Person;

/**
 *
 * @author cgallen
 */
public class AppointmentDAOJpaImpl implements AppointmentDAO {

    final static Logger LOG = LogManager.getLogger(PersonDAOJpaImpl.class);

    private EntityManager entityManager;

    public AppointmentDAOJpaImpl(EntityManager em) {
        this.entityManager = em;
    }

    @Override
    public Appointment findById(Long id) {
        Appointment appointment = entityManager.find(Appointment.class, id);
        return appointment;
    }

    @Override
    public Appointment save(Appointment appointment) {
        
        entityManager.getTransaction().begin();
        entityManager.persist(appointment);
        entityManager.getTransaction().commit();
        return appointment;
    }

    @Override
    public List<Appointment> findAll() {
        TypedQuery<Appointment> q = entityManager.createQuery("SELECT a FROM Appointment a", Appointment.class);
        List<Appointment> appointmentList = q.getResultList();
        return appointmentList;
    }

    @Override
    public Appointment delete(Appointment appointment) {
        try {
            entityManager.getTransaction().begin();
            Query q = entityManager.createQuery("DELETE FROM Appointment a WHERE a.id=:id");
            q.setParameter("id", appointment.getId());
            q.executeUpdate();
            entityManager.getTransaction().commit();
            return appointment;
        }
        catch (Exception e){
            LOG.error(e.toString());
            return appointment;
        }
       
    }

    @Override
    public void deleteById(Long id) {
        entityManager.getTransaction().begin();
        Query q = entityManager.createQuery("DELETE FROM Appointment a WHERE a.id=:id");
        q.setParameter("id", id);
        q.executeUpdate();
        entityManager.getTransaction().commit();
    }

    @Override
    public void deleteAll() {
        entityManager.getTransaction().begin();
        entityManager.createQuery("DELETE FROM Appointment").executeUpdate();
        entityManager.getTransaction().commit();
}

    @Override
    public List<Appointment> findByPersonA(Person personA) {
        String queryString = "SELECT a from Appointment a WHERE  a WHERE a.person LIKE " + personA.toString(); // WHERE TRUE=TRUE masn WHERE always has a predicate \";
        TypedQuery<Appointment> query = entityManager.createQuery(queryString, Appointment.class);
        List<Appointment> appointmentList = query.getResultList();
        return appointmentList;
    }

    @Override
    public List<Appointment> findByPersonB(Person personB) {
        String queryString = "SELECT a from Appointment a WHERE  a WHERE a.person LIKE " + personB.toString(); // WHERE TRUE=TRUE masn WHERE always has a predicate \";
        TypedQuery<Appointment> query = entityManager.createQuery(queryString, Appointment.class);
        List<Appointment> appointmentList = query.getResultList();
        return appointmentList;
    }

    @Override
    public List<Appointment> findByDate(Integer year, Integer month, Integer hour, Integer minutes) {
        if (year != null && month != null && hour != null && minutes !=null) {
            String queryString = "SELECT a from Appointment a WHERE ";
            
            queryString = queryString + "a.year LIKE " + year;
            queryString = queryString + " AND a.month LIKE " + month;
            queryString = queryString + " AND a.hour LIKE " + hour;
            queryString = queryString + " AND a.minutes LIKE " + minutes;
                    
   
            TypedQuery<Appointment> query = entityManager.createQuery(queryString, Appointment.class);
            List<Appointment> appointmentList = query.getResultList();
            return appointmentList;
         }
        else {
            List<Appointment> appointmentList = null;
            return appointmentList;
        }
        
        
    }

}
