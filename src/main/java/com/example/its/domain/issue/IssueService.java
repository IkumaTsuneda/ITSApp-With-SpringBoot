package com.example.its.domain.issue;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class IssueService {

    //Daoクラス
    private final IssueRepository issueRepository;

    //全件取得処理
    public List<IssueEntity> findAll() {
        return issueRepository.findAll();
    }

    //課題作成処理
    @Transactional
    public void create(String summary, String description){
        issueRepository.insert(summary,description);
    }

    //詳細取得処理
    public IssueEntity findById(long issueId){
        return issueRepository.findById(issueId);
    }

    //削除処理
    public void delete(long issueId){
        issueRepository.delete(issueId);
    }
}
