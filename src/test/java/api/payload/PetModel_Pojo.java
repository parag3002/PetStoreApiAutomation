package api.payload;

public class PetModel_Pojo
{

	private int id;
	private int category_id;
	private String category_name;
	private String name;
	private String photoUrls;
	private int tags_id;
	private String tags_name;
	private String status;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCategory_id() {
		return category_id;
	}
	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}
	public String getCategory_name() {
		return category_name;
	}
	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhotoUrls() {
		return photoUrls;
	}
	public void setPhotoUrls(String photoUrls) {
		this.photoUrls = photoUrls;
	}
	public int getTags_id() {
		return tags_id;
	}
	public void setTags_id(int tags_id) {
		this.tags_id = tags_id;
	}
	public String getTags_name() {
		return tags_name;
	}
	public void setTags_name(String tags_name) {
		this.tags_name = tags_name;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
	
}


//{
//	  "id": 92233768547714390,
//	  "category": {
//	    "id": 0,
//	    "name": "Dragon"
//	  },
//	  "name": "Dragon",
//	  "photoUrls": [
//	    "https://screenrant.com/harry-potter-dragons-breeds/"
//	  ],
//	  "tags": [
//	    {
//	      "id": 0,
//	      "name": "Dangerous"
//	    }
//	  ],
//	  "status": "parag"
//	}