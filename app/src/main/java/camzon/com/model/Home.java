package camzon.com.model;

/**
 * Created by Thuon on 3/22/2016.
 */
public class Home {
    private String title;
    private String image;
    private String time;
    private String description;

    public Home() {
    }

    public Home(String title, String image, String time, String description) {
        this.title = title;
        this.image = image;
        this.time = time;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
