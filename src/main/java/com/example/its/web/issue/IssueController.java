package com.example.its.web.issue;

import com.example.its.domain.issue.IssueService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/issues")
@RequiredArgsConstructor
public class IssueController {

    //ビジネスロジッククラス
    private final IssueService issueService;

    //課題一覧へ遷移
    @GetMapping
    public String showList(Model model){
        model.addAttribute("issueList", issueService.findAll());
        return "issues/list";
    }

    //課題作成画面へ遷移
    @GetMapping("/creationForm")
    public String showCreationForm(@ModelAttribute IssueForm form){
        return "issues/creationForm";
    }

    //作成ボタン押下時に一覧画面へ遷移
    @PostMapping(path = "create")
    public String create(@Validated IssueForm form, BindingResult bindingResult, Model model){
        //入力チェックエラーがある場合、課題作成画面へ戻す
        if (bindingResult.hasErrors()){
            return showCreationForm(form);
        }
        issueService.create(form.getSummary(), form.getDescription());
        return "redirect:/issues";
    }

    //詳細画面へ遷移
    @GetMapping("/{issueId}")
    public String showDetail(@PathVariable("issueId") long issueId, Model model){
        model.addAttribute("issue",issueService.findById(issueId));
        return "issues/detail";
    }

    //削除後に課題一覧へ遷移
    @PostMapping(path = "edit", params = "delete")
    public String edit(@RequestParam long issueId, Model model){
        issueService.delete(issueId);
        return showList(model);
    }
}
