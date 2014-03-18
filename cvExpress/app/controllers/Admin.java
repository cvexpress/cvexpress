package controllers;
 
import play.*;
import play.data.validation.Required;
import play.mvc.*;
 
import java.util.*;
 
import models.*;
 
@With(Secure.class)
public class Admin extends Controller {
    
	@Before
    static void setConnectedUser() {
        if(Security.isConnected()) {
            User user = User.find("byEmail", Security.connected()).first();
            renderArgs.put("user", user.fullname);
        }
    }
	public static void createPost() 
    {   
    	 render();
    }
	public static void publishPost(@Required  String content,@Required  String title,@Required  String postCategory)
	{
		//System.out.println(content);
		//System.out.println(title);
		//System.out.println(postCategory);
		String type="";
		if(postCategory.equals("机器视觉"))
		{
			type="cv";
		}else if (postCategory.equals("机器学习"))
		{
			type="ml";
		}else if (postCategory.equals("创业"))
		{
			type="startup";
		}else if (postCategory.equals("招聘"))
		{
			type="recruit";
		}else if (postCategory.equals("外包"))
		{
			type="freelancer";
		}
		System.out.println(type);
		// find the author;
		User user = User.find("byEmail", Security.connected()).first();
		new Post(user, title, content).tagItWith(type).save();
		Application.index(type); 
	}
 
    public static void index() {
        render();
    }
   

    
}
