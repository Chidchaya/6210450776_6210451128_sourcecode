package th.ac.ku.ringsRunner.model;

import java.util.UUID;

public class Rings {

    private UUID id;
    private String name;
    private int size;
    private int price;
    private int amount;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
    public Rings (UUID id ,String name , int size, int price, int amount){
        this.id = id;
        this.name = name;
        this.size = size;
        this.price = price;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
    @Override
    public String toString() {
        return "{" +
                id +
                "->" + name +
                "->" + size +
                "->" + price +
                "->" + amount ;
    }


}
