package com.trexoftlondon.codetest.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FromTo {
    @JsonProperty("from")
    private String from;
    @JsonProperty("to")
    private String to;
}
