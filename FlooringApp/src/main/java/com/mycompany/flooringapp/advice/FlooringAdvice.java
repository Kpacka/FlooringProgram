/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringapp.advice;

import com.mycompany.flooringapp.dao.FlooringAuditDao;
import com.mycompany.flooringapp.dao.FlooringPersistenceException;
import com.mycompany.flooringapp.dto.Order;
import com.mycompany.flooringapp.service.FlooringLogic;

/**
 *
 * @author KylePackard
 */
public class FlooringAdvice {

    FlooringAuditDao auditDao;

    public FlooringAdvice(FlooringAuditDao auditDao) {
        this.auditDao = auditDao;
    }

    public void writeErrorLog(Exception ex) throws FlooringPersistenceException {

        String message = ex.toString();

        // Order toLog = myLogic.returnInventoryItem();
        // String itemName = toLog.getItemName();
        // auditDao.writeAuditEntry(itemName + " : " + name);
    }

    public void writeSaveLog() throws FlooringPersistenceException {

        String message = "SAVE";

        auditDao.writeAuditEntry(message);

    }

    public void writeEditLog() throws FlooringPersistenceException {
        String message = "EDIT";
        auditDao.writeAuditEntry(message);
    }

}
