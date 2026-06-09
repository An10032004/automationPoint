package com.example.demo.service;
import com.example.demo.dto.AIrequest;
import com.example.demo.dto.AIresponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.example.demo.entity.Essay;
import com.example.demo.repository.EssayRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.example.demo.entity.Essay;
import com.example.demo.repository.EssayRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EssayService {
    private final RestTemplate restTemplate;
    private final EssayRepository essayRepository;
    public Essay scoreEssay(Long id){
        Essay essay = essayRepository.getEssayById(id).orElseThrow();
        AIrequest request = new AIrequest();
        request.setContent(essay.getContent());
        AIresponse response = restTemplate.postForObject("http://localhost:8000/predict", request, AIresponse.class);
        essay.setScore(response.getScore());
        essay.setFeedback(response.getFeedback());
        return essayRepository.save(essay);
    }
}
