package api.payload;

import java.util.List;

//{
//		  "id": 92233768547714390,
//		  "category": {
//		    "id": 0,
//		    "name": "Dragon"
//		  },
//		  "name": "Dragon",
//		  "photoUrls": [
//		    "https://screenrant.com/harry-potter-dragons-breeds/"
//		  ],
//		  "tags": [
//		    {
//		      "id": 0,
//		      "name": "Dangerous"
//		    }
//		  ],
//		  "status": "parag"
//		}




public class Pet 
{
	    private long id;
	    private Category category;
	    private String name;
	    private List<String> photoUrls;
	    private List<Tag> tags;
	    private String status;

	    // Getter and setter methods for all the fields

	    public long getId() {
	        return id;
	    }

	    public void setId(long id) {
	        this.id = id;
	    }

	    public Category getCategory() {
	        return category;
	    }

	    public void setCategory(Category category) {
	        this.category = category;
	    }

	    public String getName() {
	        return name;
	    }

	    public void setName(String name) {
	        this.name = name;
	    }

	    public List<String> getPhotoUrls() {
	        return photoUrls;
	    }

	    public void setPhotoUrls(List<String> photoUrls) {
	        this.photoUrls = photoUrls;
	    }

	    public List<Tag> getTags() {
	        return tags;
	    }

	    public void setTags(List<Tag> tags) {
	        this.tags = tags;
	    }

	    public String getStatus() {
	        return status;
	    }

	    public void setStatus(String status) {
	        this.status = status;
	    }
	}



