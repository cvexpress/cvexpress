package models;
 
import java.util.*;

import javax.persistence.*;
 
import play.db.jpa.*;
import play.data.validation.*;


@Entity
public class Post extends Model {
    
	@Required
    public String title;
	@Required
    public Date postedAt;
    
    @Lob
    @Required
    @MaxSize(10000)
    public String content;
    @Required
    @ManyToOne
    public User author;
    @OneToMany(mappedBy="post", cascade=CascadeType.ALL)
    public List<Comment> comments;
    @ManyToMany(cascade=CascadeType.PERSIST)
    public Set<Category> tags;
     
    public Post(User author, String title, String content) {
        this.comments = new ArrayList<Comment>();
        this.tags = new TreeSet<Category>();
        this.author = author;
        this.title = title;
        this.content = content;
        this.postedAt = new Date();
    }
    public Post tagItWith(String name) {
        tags.add(Category.findOrCreateByName(name));
        return this;
    }
    public static List<Post> findTaggedWith(String tag) {
        return Post.find(
            "select distinct p from Post p join p.tags as t where t.name = ? order by p.postedAt desc", tag
        ).fetch();
    }

    
    public Post addComment(String author, String content) {
        Comment newComment = new Comment(this, author, content).save();
        this.comments.add(newComment);
        this.save();
        return this;
    }
    public String toString() {
        return title ;
    }
    public String generateAbstract()
    {
    	//return HTML.html2text(content);
    	//String abst=content;
    	//abst=subStr(subStr(abst,"<img"),"<iframe");
    	//return abst;
    	return "";
    }
    public String subStr(String ori,String str)
    {
    	 int p=ori.indexOf(str);
    	 if(p==-1)
    	 {
    		 return ori;
    	 }else
    	 {
    	 return ori.substring(0, p);
    	 }
    }


 
}
