package com.trexoftlondon.codetest.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.trexoftlondon.codetest.constant.Constants;
import com.trexoftlondon.codetest.constant.PriceLabelFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Product {

    @JsonProperty("productId")
    private long productId;
    @JsonProperty("title")
    private String title;
    String nowPrice;
    String priceLabel;

    @JsonIgnore
    FromTo nowObj;

    @JsonIgnore
    String nowStr;

    @JsonIgnore
    Price price;

    @JsonCreator
    public Product(@JsonProperty("price") Price priceSource) {
        if (!priceSource.getFrom().isEmpty() && !priceSource.getTo().isEmpty())
            this.priceLabel = String.format(Constants.PATTERN_PRICE_LABEL,
                                            priceSource.getFrom(),
                                            priceSource.getFormattedThen(),
                                            priceSource.getTo());

        this.nowStr = priceSource.getNow();
        this.nowPrice = Price.formatPrice(priceSource.getNow());
        this.price = priceSource;
    }

    @JsonProperty("colorSwatches")
    private List<ColorSwatch> colorSwatches;


    public Product arrangePriceLabelFormat(PriceLabelFormat labelType) {
        this.priceLabel = Price.formatPriceLabel( getPrice().getWas(),
                                                getPrice().getFormattedThen(),
                                                getPrice().getNow(),
                                                labelType);
        return this;
    }
}
