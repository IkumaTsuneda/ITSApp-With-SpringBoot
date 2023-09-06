package com.example.its.domain.issue;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface IssueRepository {
    @Select("select * from issues")
    //全件取得
    List<IssueEntity> findAll();

    @Insert("insert into issues (summary, description) values (#{summary}, #{description})")
    //課題作成
    void insert(String summary, String description);

    //詳細取得
    @Select("select * from issues where id = #{issueId}")
    IssueEntity findById(long issueId);

    @Delete("delete from issues where id = #{issueId}")
    void delete(long issueId);
}
