package cz.upce.nnpia.spring;

import org.springframework.stereotype.Service;

@Service
public class CounterService {
    private int counter = 0;

    public void add(){
        this.counter++;
    }

    public int getCounter(){
        return counter;
    }
}
