package in.handyman.raven.lib.agadia.xenon.model;

import com.fasterxml.jackson.databind.JsonNode;

public class XenonResult {

    private String question;
    private String predictedAttributionValue;
    private Float score;
    private JsonNode bboxes;

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getPredictedAttributionValue() {
        return predictedAttributionValue;
    }

    public void setPredictedAttributionValue(String predictedAttributionValue) {
        this.predictedAttributionValue = predictedAttributionValue;
    }

    public Float getScore() {
        return score;
    }

    public void setScore(Float score) {
        this.score = score;
    }

    public JsonNode getBboxes() {
        return bboxes;
    }

    public void setBboxes(JsonNode bboxes) {
        this.bboxes = bboxes;
    }
}
