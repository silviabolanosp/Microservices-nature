package com.example.prices.calculator;

import com.example.prices.config.PlanConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;

@Component
public class PriceCalculator {

	@Autowired
    private final PlanConfig planConfig;

    public PriceCalculator(PlanConfig planConfig) {
        this.planConfig = planConfig;
    }

 
    public BigDecimal calculatePrice(ZonedDateTime rentStartDate, ZonedDateTime rentEndDate, String planName) {
        Assert.notNull("Plan name is required", planName);

        long exactDaysCount = ChronoUnit.DAYS.between(rentStartDate, rentEndDate);
        long daysCount = exactDaysCount + (isOverlappingNextDay(exactDaysCount, rentStartDate, rentEndDate) ? 1 : 0);

        BigDecimal pricePerDay = planConfig.getPlan().getOrDefault(planName.toLowerCase(), BigDecimal.ZERO);
        return pricePerDay.multiply(new BigDecimal(daysCount));
    }

    private boolean isOverlappingNextDay(long exactDaysCount, ZonedDateTime rentStartDate, ZonedDateTime rentEndDate) {
        ZonedDateTime exactEndTime = rentStartDate.plus(exactDaysCount, ChronoUnit.DAYS);
        Duration overlappedDayDuration = Duration.between(exactEndTime, rentEndDate);
        return overlappedDayDuration.getSeconds() > 0;
    }

}