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
public class Role
{
    /** 
    *   1 = role knihy
    *   2 = role zákazník
    */   
    private int role = 1;
            
    public int getRole()
    {
        
        return role;
        
    }
        
    public void setRole(int selectedRole)
    {
        role = selectedRole;
        
    }
    
}
