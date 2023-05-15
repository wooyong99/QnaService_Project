package com.exam.jwy.Answer;

import com.exam.jwy.Exception.DataNotFoundException;
import com.exam.jwy.Question.Question;
import com.exam.jwy.Question.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AnswerService {
    private final AnswerRepository answerRepository;
    private final QuestionRepository questionRepository;
    public void createAnswer(Question question, String content) {
        Answer answer = new Answer();
        answer.setContent(content);
        answer.setCreateDate(LocalDateTime.now());
        question.addAnswer(answer);

        questionRepository.save(question);
    }


    public List<Answer> getAnswerList(Integer id) {
        return answerRepository.findByQuestionId(id)
                .orElseThrow(() -> new DataNotFoundException("answerList not found"));
    }

    public void deleteAnswer(Integer id) {
        answerRepository.deleteById(id);
    }
}
