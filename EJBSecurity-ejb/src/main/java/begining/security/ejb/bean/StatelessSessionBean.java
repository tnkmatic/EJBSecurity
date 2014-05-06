/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package begining.security.ejb.bean;

import java.security.Principal;
import java.util.logging.Level;
import javax.annotation.Resource;
import javax.annotation.security.DeclareRoles;
import javax.ejb.Local;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;

/**
 *
 * @author Eiichi Tanaka
 */
@Stateless
@Local(StatelessSessionBeanLocal.class)
@DeclareRoles({"administrator", "general"})
public class StatelessSessionBean implements StatelessSessionBeanLocal {
    private static final java.util.logging.Logger logger = 
        java.util.logging.Logger.getLogger(StatelessSessionBean.class.getName());
    
    @Resource
    private SessionContext sessionCtx;

    @Override
    public void bussinessLogic01() {
        final Principal principal = sessionCtx.getCallerPrincipal();
        
        //プリンシパル取得
        logger.log(Level.INFO, principal.getName());
        
        //管理者ロールか
        if (sessionCtx.isCallerInRole("administrator")) {
            logger.log(Level.INFO, "Role = administrator");
        } else {
            logger.log(Level.INFO, "NOT administrator Roles");
        }

        //一般ロールか
        if (sessionCtx.isCallerInRole("general")) {
            logger.log(Level.INFO, "Role = general");
        } else {
            logger.log(Level.INFO, "NOT general Roles");
        }
    }
}
