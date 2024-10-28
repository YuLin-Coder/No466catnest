package com.stray.cat.service;

import com.stray.cat.pojo.Sentence;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;

public interface SentenceService {

    Sentence findSentence();

    int addSentence(Sentence sentence);

    int deleteSentence(int sentenceId);

}
