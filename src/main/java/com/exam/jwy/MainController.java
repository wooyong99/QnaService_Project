package com.exam.jwy;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class MainController {
  @RequestMapping("/jwy")
  @ResponseBody
  public String index() {
    return "안녕하세요";
  }

  @PostMapping("/posttest")
  @ResponseBody
  public String showPost(@RequestParam(defaultValue = "0") int age) {
    return """
          <h1>입력값: %d</h1>
          <h1>posttest페이지입니다.</h1>
        """.formatted(age);
  }

  @GetMapping("/gettest")
  @ResponseBody
  public String showGet() {
    return """
        <form method="post" action="/posttest"> 
          <input type="number" name="age" placeholder="나이를 입력해주세요"/>
          <input type="submit" value="testpost페이지로이동"/>
        </form>
        """;
  }

  @GetMapping("/gugudan")
  @ResponseBody
  public String gugudan(@RequestParam(defaultValue = "9") int dan, @RequestParam(defaultValue = "9") int limit) {
    return IntStream.rangeClosed(1, limit)
        .mapToObj(i -> "%d * %d = %d".formatted(dan, i, dan * i))
        .collect(Collectors.joining("<br>"));
  }

  @GetMapping(value={"/mbti","/mbti/{name}"})
  @ResponseBody
  public String mbti(@PathVariable(required = false) String name) {
    if(name==null){
      name="미입력";
    }
    return switch(name){
      case "정우용" -> "ESFP";
      case "김형곤" -> "INFP";
      case "송형진" -> "ISFJ";
      default -> "모름";
    };
  }

  @GetMapping("/saveSessionAge/{value}")
  public String saveSessionAge(@PathVariable String value,  HttpServletRequest req){
    HttpSession session = req.getSession();
    session.setAttribute("age", value);
    return "redirect:/getSession";
  }

  @GetMapping("/getSession")
  @ResponseBody
  public String getSessionAge(@SessionAttribute(name="age", required = false) String value){
    return String.valueOf(value);
  }

  @GetMapping("/addArticle")
  @ResponseBody
  public String addArticle(String title, String body){
    int id = 1;
    Article article = new Article(id, title, body);
    return "%d번 게시글 생성".formatted(article.id);
  }

  @GetMapping("/addArticle2")
  @ResponseBody
  public String addArticle2(String title, String body){
    Article article = new Article(title, body);
    articles.add(article);
    return "%d번 게시글 생성".formatted(article.id);
  }

  private List<Article> articles = new ArrayList();
  @GetMapping("/getArticle/{id}")
  @ResponseBody
  public Article getArticle(@PathVariable int id){
    Integer finalId = id;
    Article article = articles.stream()
        .filter(a -> a.id == finalId)
        .findFirst()
        .get();
    return article;
  }
}
@AllArgsConstructor
class Article{
  private static int last_id = 1;
  public int id;
  public String title;
  public String body;
  public Article(String title, String body){
    this(last_id, title, body);
    this.last_id++;
  }
}
