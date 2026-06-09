package com.example.demo.dto;
import lombok.Data;
@Data
public class AIresponse {
    private Double score;
    private String feedback;
    public Double getScore() {
        return score;
    }
    public void setScore(Double score) {
        this.score = score;
    }
    public String getFeedback() {
        return feedback;
    }
    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
}