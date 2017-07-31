package com.kenny.service.logistics.model.profit;

import java.util.List;

/**
 * Created by Kenny on 2017/7/25.
 */
public class ProfitSet {
    Profit profit;
    List<ProfitStatus> profitStatuses;

    public Profit getProfit() {
        return profit;
    }

    public void setProfit(Profit profit) {
        this.profit = profit;
    }

    public List<ProfitStatus> getProfitStatuses() {
        return profitStatuses;
    }

    public void setProfitStatuses(List<ProfitStatus> profitStatuses) {
        this.profitStatuses = profitStatuses;
    }
}
