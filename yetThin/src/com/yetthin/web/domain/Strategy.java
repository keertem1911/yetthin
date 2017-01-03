package com.yetthin.web.domain;

public class Strategy {
    private String strategyId;

    private String strategyName;

    private String strategyType;

    private String strategyInvestCycle;

    private String strategyFundSize;

    public String getStrategyId() {
        return strategyId;
    }

    public void setStrategyId(String strategyId) {
        this.strategyId = strategyId;
    }

    public String getStrategyName() {
        return strategyName;
    }

    public void setStrategyName(String strategyName) {
        this.strategyName = strategyName;
    }

    public String getStrategyType() {
        return strategyType;
    }

    public void setStrategyType(String strategyType) {
        this.strategyType = strategyType;
    }

    public String getStrategyInvestCycle() {
        return strategyInvestCycle;
    }

    public void setStrategyInvestCycle(String strategyInvestCycle) {
        this.strategyInvestCycle = strategyInvestCycle;
    }

    public String getStrategyFundSize() {
        return strategyFundSize;
    }

    public void setStrategyFundSize(String strategyFundSize) {
        this.strategyFundSize = strategyFundSize;
    }
}