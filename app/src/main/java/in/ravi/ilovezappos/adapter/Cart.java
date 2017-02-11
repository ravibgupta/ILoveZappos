package in.ravi.ilovezappos.adapter;

public class Cart {

    private String id, image, brand, product, price;

    public Cart() {
    }

    public String getId() {
        return id;
    }

    public String getBrand() {
        return brand;
    }

    public String getProduct() {
        return product;
    }

    public String getPrice() {
        return price;
    }

    public Cart(String id, String image, String brand, String product, String price) {
        this.id = id;
        this.image = image;
        this.brand = brand;

        this.product = product;
        this.price = price;
    }

    public String getImage() {
        return image;
    }

}