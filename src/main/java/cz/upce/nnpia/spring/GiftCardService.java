package cz.upce.nnpia.spring;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

@Service
@Scope(value = "singleton", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class GiftCardService implements ProductService {
    private String name;
    private boolean purchased;
    private String price;

    @Override
    public void setName(String ownerName) {
        this.name = "This is gift card for "+ownerName;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String setPurchased() {
        if(!isPurchased()){
            purchased = true;
            return "Congratulations you have purchased a gift card!";
        }
        return "Unfortunately the gift card is already sold out :(";
    }

    @Override
    public boolean isPurchased() {
        return purchased;
    }

    @Override
    public void setPrice(String price){
        this.price = price;
    }

    @Override
    public String getPrice(){
        return price;
    }
}
