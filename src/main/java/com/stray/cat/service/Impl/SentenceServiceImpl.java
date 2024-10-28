package com.stray.cat.service.Impl;

import com.stray.cat.dao.SentenceMapper;
import com.stray.cat.pojo.Sentence;
import com.stray.cat.service.SentenceService;
import com.stray.cat.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
@Transactional(rollbackFor = RuntimeException.class)
public class SentenceServiceImpl implements SentenceService {
    @Autowired
    SentenceMapper sentenceMapper;

    @Override
    public Sentence findSentence() {
        List<Sentence> sentences=sentenceMapper.queryAllSentence();
        Random rand = new Random();
        int i=rand.nextInt(sentences.size());
        Sentence sentence=sentences.get(i);
        Calendar now = Calendar.getInstance();
        Date date=new Date();
        String s= now.get(Calendar.YEAR)+"年"+ now.get(Calendar.MONTH) + 1+"月"+now.get(Calendar.DAY_OF_MONTH)+"日"+ DateUtil.getWeekOfDate(date);
        sentence.setSentenceDate(s);
        return sentence;
    }

    @Override
    public int addSentence(Sentence sentence) {
        return sentenceMapper.addSentence(sentence);
    }

    @Override
    public int deleteSentence(int sentenceId) {
        return sentenceMapper.deleteSentence(sentenceId);
    }
}
