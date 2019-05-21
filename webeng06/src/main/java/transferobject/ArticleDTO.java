package transferobject;

import java.io.Serializable;

public class ArticleDTO implements Serializable {

    private String name;
    private int id;
    private int amount;
    private float price;

    public ArticleDTO() {

    }

    public ArticleDTO (String _name, int _id, int _amount, float _price) {
        this.setName(_name);
        this.setId(_id);
        this.setAmount(_amount);
        this.setPrice(_price);
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
