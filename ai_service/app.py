from fastapi import FastAPI
from pydantic import BaseModel

import torch

from transformers import (
    AutoTokenizer,
    AutoModelForSequenceClassification
)

MODEL_PATH = "./model"

tokenizer = AutoTokenizer.from_pretrained(
    MODEL_PATH
)

model = AutoModelForSequenceClassification.from_pretrained(
    MODEL_PATH
)

model.eval()

app = FastAPI()


class EssayRequest(BaseModel):
    content: str


class EssayResponse(BaseModel):
    score: float
    feedback: str


@app.post("/predict", response_model=EssayResponse)
def predict(request: EssayRequest):

    inputs = tokenizer(
        request.content,
        return_tensors="pt",
        truncation=True,
        padding=True,
        max_length=512
    )

    with torch.no_grad():

        outputs = model(**inputs)

        score = outputs.logits.squeeze().item()

    if score >= 8:
        feedback = "Excellent essay"

    elif score >= 6:
        feedback = "Good essay"

    elif score >= 4:
        feedback = "Average essay"

    else:
        feedback = "Needs improvement"

    return {
        "score": round(score, 2),
        "feedback": feedback
    }