package controllers;
 
import models.*;
 
public class Security extends Secure.Security {
	
   
    	
    	static boolean authenticate(String email, String password) {
    		
    	    User instance=User.connect(email, password);
    	    boolean result=(instance!=null);
    	if(result)
    	{
    	    String fullname=instance.fullname;
	    	session.put("fullname", fullname);
    	    
    	   
    	}
    	 return result;
    	}
    	static void onDisconnected() {
    	    Application.index("cv");
    	}

    	static void onAuthenticated() {
    		//Application.index("cv");
    	}
    	static boolean check(String profile) {
    	    if("admin".equals(profile)) {
    	        return User.find("byEmail", connected()).<User>first().isAdmin;
    	    }
    	    return false;
    	}



    
    
}
