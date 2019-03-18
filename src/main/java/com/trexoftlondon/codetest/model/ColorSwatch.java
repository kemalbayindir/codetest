package com.trexoftlondon.codetest.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.trexoftlondon.codetest.constant.Constants;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.awt.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ColorSwatch {

    @JsonProperty("color")
    private String color;

    @JsonProperty("skuId")
    private String skuId;

    String rgbColor;

    @JsonCreator
    public ColorSwatch(@JsonProperty("basicColor") String basicColor) {
        Color color = Constants.colors.get(basicColor);

        this.rgbColor = null != color ?
                String.format(Constants.RGB_PATTERN, color.getRed(), color.getGreen(), color.getBlue()) : "#ffffff";
    }
}
