package com.trexoftlondon.codetest.model;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.JsonNode;
import com.trexoftlondon.codetest.constant.Constants;
import com.trexoftlondon.codetest.constant.PriceLabelFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.text.DecimalFormat;
import java.text.NumberFormat;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonIgnoreType
public class Price {

    @JsonIgnore
    String was;
    @JsonIgnore
    String now;
    @JsonIgnore
    String from;
    @JsonIgnore
    String to;

    @JsonIgnore
    Boolean isThereReduceEvent;
    @JsonIgnore
    String formattedThen1;
    @JsonIgnore
    String formattedThen2;
    @JsonIgnore
    String formattedThen;


    @JsonCreator
    public Price(@JsonProperty("now") JsonNode now, @JsonProperty("was") String was,
                 @JsonProperty("then1") String then1, @JsonProperty("then2") String then2) {
        this.was = was;
        this.from = Price.val(now, "from");
        this.to = Price.val(now, "to");
        if (!this.from.isEmpty())
            this.now = this.to;
        else
            this.now = now.asText();


        this.isThereReduceEvent = !was.isEmpty();

        // reduce control
        if (this.isThereReduceEvent) {
            this.formattedThen1 = Price.formatThen(then1);
            this.formattedThen2 = Price.formatThen(then2);
            this.formattedThen = then2.isEmpty() ? this.formattedThen1 : this.formattedThen2;
        }
    }

    public static String val(JsonNode node, String key) {
        JsonNode targetNode = node.get(key);
        if (null != targetNode)
            return targetNode.asText();
        else return "";
    }

    public static String formatThen(String then) {
        return then.isEmpty() ? "" : String.format(Constants.PATTERN_THEN_FORMAT, then);
    }

    public static String formatPriceLabel(String was, String then, String now, PriceLabelFormat labelFormat) {
        if (labelFormat == PriceLabelFormat.ShowWasThenNow)
            return String.format(Constants.PATTERN_PRICE_LABEL, was, then, now);
        else if (labelFormat == PriceLabelFormat.ShowPercDscount) {
            Double wasDouble = Double.parseDouble(was);
            Double nowDouble = Double.parseDouble(now);
            Double perc = 100 * (wasDouble - nowDouble) / wasDouble;
            return String.format(Constants.PATTERN_PERC_FORMAT, perc, now);
        } else //PriceLabelFormat.ShowWasNow
            return String.format(Constants.PATTERN_PRICE_LABEL, was, "", now);
    }

    public static String formatPrice(String priceStr) {
        Double nowPrice = Double.parseDouble(priceStr);
        String formattedNowPrice = String.format("£%.2f", nowPrice);
        if (nowPrice > 10) {
            NumberFormat formatter = new DecimalFormat("##");
            formattedNowPrice = String.format("£%s", formatter.format(nowPrice));
        }
        return formattedNowPrice;
    }

}
