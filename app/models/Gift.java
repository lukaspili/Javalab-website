package models;

import java.awt.Image;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import play.data.validation.Constraints.Required;
import play.db.ebean.Model;
import play.db.ebean.Model.Finder;

@Entity
@Table(name="mygift_gift")
public class Gift extends Model {

	private static final long serialVersionUID = 1L;
	
	@Id
	public Long id;
	
	@Required
	public String name;
	
	@Required
	public String description;
	
	public Long minPrice;
	
	public Long maxPrice;
	
	public String link;
	
	public Image image;
	
	@Required
	public long lastModified;
	
	@Required
	public String category;
	
	@Required
	public String priority;
	
	@Required
	public boolean isAvailable;
	
	@Required
	public boolean isBought;
	
	
	public static Finder<Long,Gift> find = new Finder<Long, Gift>(
			Long.class, Gift.class
	);
	
	public static void create(Gift gift) {
		gift.save();
	}
	
	public static void delete(Long id) {
		find.ref(id).delete();
	}
		
	public static List<Gift> all() {
		return find.all();
	}

}
