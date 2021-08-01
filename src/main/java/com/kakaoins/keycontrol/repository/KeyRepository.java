package com.kakaoins.keycontrol.repository;

import com.kakaoins.keycontrol.domain.Key;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class KeyRepository {
    //KEY와 객체를 담을 HashMap 생성
    public  Map<String, Key> store = new HashMap<>();

    //KEY 객체를 store에 저장
    public void save(Key key){
        store.put(key.getKey(), key);

    }

    //KEY 객체를 store에서 가져오기
    public Key get(Key key){
        return store.get(key.getKey());

    }

    //KEY를 이용해서 객체를 찾을 때 사용
    public Key get(String str){
        return store.get(str);
    }

    //KEY가 등록되었는지 체크할 때 사용
    public boolean isKey(String str){
        return store.containsKey(str);
    }

    //테스트 코드 작성 용도로 사용
    public void clearStore(){
        store.clear();
    }
}
