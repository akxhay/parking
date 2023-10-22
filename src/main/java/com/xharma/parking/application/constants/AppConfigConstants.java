package com.xharma.parking.application.constants;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
public class AppConfigConstants {
    @Value("${application.parking.slots.size.small}")
    public Integer smallSlotSize;
    @Value("${application.parking.slots.size.medium}")
    public Integer mediumSlotSize;

    @Value("${application.parking.slots.size.large}")
    public Integer largeSlotSize;
    @Value("${application.parking.slots.size.xlarge}")
    public Integer xLargeSlotSize;


}
