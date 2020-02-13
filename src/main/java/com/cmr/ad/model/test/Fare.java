package com.cmr.ad.model.test;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Fare {
    private Long nightSurcharge;
    private Long rideFare;
}
