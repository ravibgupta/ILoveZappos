package in.ravi.ilovezappos.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Product implements Parcelable {

    @SerializedName("brandName")
    private String brandName;

    @SerializedName("thumbnailImageUrl")
    private String thumbnailImageUrl;

    @SerializedName("productId")
    private String productId;

    @SerializedName("originalPrice")
    private String originalPrice;

    @SerializedName("styleId")
    private String styleId;

    @SerializedName("colorId")
    private String colorId;

    @SerializedName("price")
    private String price;

    @SerializedName("percentOff")
    private String percentOff;

    @SerializedName("productUrl")
    private String productUrl;

    @SerializedName("productName")
    private String productName;

    public Product(String brandName, String thumbnailImageUrl, String productId, String originalPrice, String styleId, String colorId, String price, String percentOff, String productUrl, String productName) {

        this.brandName = brandName;
        this.thumbnailImageUrl = thumbnailImageUrl;
        this.productId = productId;
        this.originalPrice = originalPrice;
        this.styleId = styleId;
        this.colorId = colorId;
        this.price = price;
        this.percentOff = percentOff;
        this.productUrl = productUrl;
        this.productName = productName;
    }

    public String getBrandName() {
        return brandName;
    }

    public String getThumbnailImageUrl() {
        return thumbnailImageUrl;
    }

    public String getProductId() {
        return productId;
    }

    public String getOriginalPrice() {
        return originalPrice;
    }

    public String getStyleId() {
        return styleId;
    }

    public String getColorId() {
        return colorId;
    }

    public String getPrice() {
        return price;
    }

    public String getPercentOff() {
        return percentOff;
    }

    public String getProductUrl() {
        return productUrl;
    }

    public String getProductName() {
        return productName;
    }


    public static final Creator<Product> CREATOR = new Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    protected Product(Parcel in) {
        brandName = in.readString();
        thumbnailImageUrl = in.readString();
        productId = in.readString();
        originalPrice = in.readString();
        styleId = in.readString();
        colorId = in.readString();
        price = in.readString();
        percentOff = in.readString();
        productUrl = in.readString();
        productName = in.readString();
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(brandName);
        parcel.writeString(thumbnailImageUrl);
        parcel.writeString(productId);
        parcel.writeString(originalPrice);
        parcel.writeString(styleId);
        parcel.writeString(colorId);
        parcel.writeString(price);
        parcel.writeString(percentOff);
        parcel.writeString(productUrl);
        parcel.writeString(productName);
    }
}
