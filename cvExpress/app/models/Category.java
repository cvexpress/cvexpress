package models;
 
import java.util.*;
import javax.persistence.*;
 
import play.db.jpa.*;
import play.data.validation.*;

@Entity
public class Category extends Model implements Comparable<Category> {
	@Required

    public String name;
 
    private Category(String name) {
        this.name = name;
    }
 
    public String toString() {
        return name;
    }
 
    public int compareTo(Category otherClass) {
        return name.compareTo(otherClass.name);
    }
    public static Category findOrCreateByName(String name) {
    	Category tag = Category.find("byName", name).first();
        if(tag == null) {
            tag = new Category(name);
        }
        return tag;
    }
    public static List<Map> getCloud() {
        List<Map> result = Category.find(
            "select new map(t.name as tag, count(p.id) as pound) from Post p join p.tags as t group by t.name order by t.name"
        ).fetch();
        return result; 	
    }


}
