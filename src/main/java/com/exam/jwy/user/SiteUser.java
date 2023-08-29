package com.exam.jwy.user;

import com.exam.jwy.Question.Question;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Setter
@NoArgsConstructor
public class SiteUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String username;
    private String password;
    private String role;
    @Column(unique = true)
    private String email;
    @OneToMany(mappedBy = "siteUser", cascade = CascadeType.ALL)
    private List<Question> questionList = new ArrayList<>();
    public void addQuestion(Question question){
        question.setSiteUser(this);
        getQuestionList().add(question);
    }
}
