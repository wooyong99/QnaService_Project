package com.exam.jwy.Answer;

import com.exam.jwy.Exception.DataNotFoundException;
import com.exam.jwy.Question.Question;
import com.exam.jwy.Question.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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
        Optional<List> op_a_list = answerRepository.findByQuestionId(id);
        if(op_a_list.isPresent()){
            return op_a_list.get();
        }
        throw new DataNotFoundException("answerList not found");
    }
}
