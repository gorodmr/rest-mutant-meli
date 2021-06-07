package com.meli.mutant.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.meli.mutant.util.JsonUtil;
import lombok.*;

import java.math.BigDecimal;

/**
 * Pojo for statistics and ratios of humans and mutants
 *
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class StatisticsDna {

    @JsonProperty("human")
    private Long human = 0L;

    @JsonProperty("mutant")
    private Long mutant = 0L;

    @JsonProperty(value = "ratio", defaultValue = "0")
    private Double ratio;


    /**
     * Serializes current instance
     * @return Serialized class
     **/
    public String toString() {
        return JsonUtil.toJsonString(this);
    }
}
