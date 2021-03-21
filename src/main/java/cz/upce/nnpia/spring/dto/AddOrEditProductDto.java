package cz.upce.nnpia.spring.dto;


import org.springframework.web.multipart.MultipartFile;

public class AddOrEditProductDto {

    private Long id;

    private String name;

    private String description;

    private Integer rating;

    private Integer inStock;

    private Integer price;

    private Integer displayed;

    private MultipartFile image;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Integer getInStock() {
        return inStock;
    }

    public void setInStock(Integer inStock) {
        this.inStock = inStock;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getDisplayed() {
        return displayed;
    }

    public void setDisplayed(Integer displayed) {
        this.displayed = displayed;
    }

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }
}
