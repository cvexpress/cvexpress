package controllers;

import play.*;

import play.mvc.*;

import java.util.*;

import models.*;
import play.data.validation.*;


public class Application extends Controller {
	public static int numPerPage=10;
    public static int numPagination=10;
    public static void showPage(String current_category,int pageIndex,int pageType)
    {
    	 List<Post> olderPosts_ori = Post.findTaggedWith(current_category);
    	 session.put("preurl", request.url);
         System.out.println("hello"+request.url);
        
         int sz=olderPosts_ori.size();
         
         // 
         if (pageType==2) //previous
         {
        	 pageIndex=pageIndex-1;
         }else if(pageType==3) //next
         {
        	 pageIndex=pageIndex+1;
         }
         List<Integer> range_page=new ArrayList<Integer>();
         int start_page=(int)Math.floor((pageIndex-1)*1.0/numPagination)+1;
         int end_page=(int)(start_page+numPagination-1);
         int max_page=(int)Math.ceil(sz*1.0/numPerPage);
         end_page=Math.min(end_page, max_page);
         for (int i=start_page;i<=end_page;i++)
         {
        	 range_page.add(i);
         }
         int start_item=(pageIndex-1)*numPerPage;
         int end_item=start_item+numPerPage;
         end_item=Math.min(end_item, sz);
         List<Post> olderPosts = olderPosts_ori.subList(start_item, end_item);
         System.out.println(start_item);
         System.out.println(end_item);
         int active_page=pageIndex;
         int warning_type_pre=0;
         int warning_type_next=0;
         if(active_page==1)
         {
        	 warning_type_pre=1;
        	 
         }
         if(active_page==max_page)
         {
        	 warning_type_next=1;
         }
         render(olderPosts,range_page,active_page,current_category,warning_type_pre,warning_type_next);
    	
    }
    public static void index(String current_category) {
    	
    	/*Post frontPost = Post.find("order by postedAt desc").first();
        List<Post> olderPosts = Post.find(
            "order by postedAt desc"
        ).from(1).fetch(10);
        render(frontPost, olderPosts);*/
    	if (current_category==null)
    	{
    	 current_category="cv";
    	}
    	showPage(current_category,1,1);
    	/*int pageIndex=1;
    	int pageType=1;
    	if(args!=null)
    	{
    	if (args.length>=1)
    	{
    		current_category=(String)args[0];
    	}
    	if (args.length>=2)
    	{
    		pageIndex=(int)args[1];
    	}
    	if (args.length>=3)
    	{
    		pageType=(int)args[2];
    	}
    	}
    */ 
    	
        

    }
  
   
    
    
    public static void show(Long id) {
        Post post = Post.findById(id);
        session.put("preurl", request.url);
        System.out.println("hello"+request.url);
        render(post);
    }
    public static void postComment(Long postId, @Required  String content) {
        Post post = Post.findById(postId);
        String author="kuang zhanghui";
        if (validation.hasErrors()) {
            render("Application/show.html", post);
        }
        
        post.addComment(author, content);
        flash.success("Thanks for posting");
        
        show(postId);
   }
    public static void listTagged(String tag) {
        List<Post> posts = Post.findTaggedWith(tag);
        render(tag, posts);
    }
    public static void registerUser(String usernameN,String passwordN) {
    	
    	new User(usernameN, passwordN, "无名氏").save();
    	
         String url = session.get("preurl");
         System.out.print(url);
         if(url == null) {
             url = Play.ctxPath + "/";
         }
         redirect(url);
    }




}