/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hbackend;

import com.hbackend.persistence.entity.portal.PortalUser.Designation;
import javax.ejb.Stateless;

/**
 *
 * @author Onyedika Okafor
 */
@Stateless
public class HelperClass {

    public Designation findDesignationByValue(String value) {
        for (Designation d : Designation.values()) {
            if (d.getDesignation().equalsIgnoreCase(value)) {
                return d;
            }
        }
        return null;
    }

}
