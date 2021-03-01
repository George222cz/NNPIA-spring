package cz.upce.nnpia.spring;

public interface ProductService {
    void setName(String productName);

    String getName();

    String setPurchased();

    boolean isPurchased();

    void setPrice(String price);

    String getPrice();
}
