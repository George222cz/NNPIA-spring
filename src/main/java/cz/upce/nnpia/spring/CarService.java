package cz.upce.nnpia.spring;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

@Service
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class CarService implements ProductService {
    private String name;
    private boolean purchased;
    private String price;

    @Override
    public void setName(String carName){
        this.name = carName;
    }

    @Override
    public String getName(){
        return name;
    }

    @Override
    public String setPurchased(){
        purchased = true;
        return "Your selected car was purchased by you";
    }

    @Override
    public boolean isPurchased(){
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
