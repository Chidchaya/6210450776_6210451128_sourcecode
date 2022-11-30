package th.ac.ku.ringsRunner.model;

public class Cart {

    private Rings rings;

    private int quantity;


    public Cart(Rings rings, int quantity){
        this.rings = rings;
        this.quantity = quantity;
    }

    public int getQuantity(){
        return quantity;
    }

    @Override
    public String toString() {
        return rings.toString()+
                "->" + quantity + "}";
    }

    public Rings getRings(){
        return rings;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setRings(Rings rings) {
        this.rings = rings;
    }
}
