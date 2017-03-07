package com.yetthin.web.domain;

public class Concept {
    private String conceptId;

    private String conceptName;

    private String conceptCreateTime;

    private Double conceptHeatLevel;

    public String getConceptId() {
        return conceptId;
    }

    public void setConceptId(String conceptId) {
        this.conceptId = conceptId;
    }

    public String getConceptName() {
        return conceptName;
    }

    public void setConceptName(String conceptName) {
        this.conceptName = conceptName;
    }

    public String getConceptCreateTime() {
        return conceptCreateTime;
    }

    public void setConceptCreateTime(String conceptCreateTime) {
        this.conceptCreateTime = conceptCreateTime;
    }

    public Double getConceptHeatLevel() {
        return conceptHeatLevel;
    }

    public void setConceptHeatLevel(Double conceptHeatLevel) {
        this.conceptHeatLevel = conceptHeatLevel;
    }
}