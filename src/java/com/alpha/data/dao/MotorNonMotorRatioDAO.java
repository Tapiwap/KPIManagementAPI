/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alpha.data.dao;

import com.alpha.data.controllers.MotorNonMotorRatioJpaController;
import com.alpha.data.controllers.exceptions.NonexistentEntityException;
import com.alpha.data.entities.MotorNonMotorRatio;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Tsotsoh
 */
public class MotorNonMotorRatioDAO {
    
    private final EntityManagerFactory emf;
    private final MotorNonMotorRatioJpaController controller;

    public MotorNonMotorRatioDAO() {
        emf = Persistence.createEntityManagerFactory("AlphaDirectKPIManagementPU");
        controller = new MotorNonMotorRatioJpaController(emf);
    }
    
    public void add(MotorNonMotorRatio object) throws Exception{
        controller.create(object);
    }
    
    public void batchAdd(List<MotorNonMotorRatio> objects) throws Exception {
        controller.batchCreate(objects);
    }
    
    public void edit(MotorNonMotorRatio object) throws Exception{
        controller.edit(object);
    }
    
    public void delete(String id) throws NonexistentEntityException{
        controller.destroy(id);
    }
}
