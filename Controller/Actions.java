/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

/**
 *
 * @author pavel
 */
public class Actions {
    
    /** 
    *   1 = akce kniha
    *   2 = akce zaměření
    *   3 = akce databáze
    *   4 = akce výpůjčka 
    *   5 = akce zakazník
    */   
    private int action = 1;       

    
    public void setAction(int selectedAction)
    {
        action = selectedAction;
        
    }
    
    public int getAction()
    {
        
        return action;
        
    }
    
    
    
}
