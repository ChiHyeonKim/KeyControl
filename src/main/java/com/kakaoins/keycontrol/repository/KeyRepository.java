package com.kakaoins.keycontrol.repository;

import com.kakaoins.keycontrol.domain.Key;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Repository
public class KeyRepository {
    //KEY와 객체를 담을 HashMap 생성
    public static Map<String, Key> store = new HashMap<>();

    //KEY와 객체를 store에 저장
    public void save(Key key){
        store.put(key.getKey(), key);
    }

    //KEY의 객체를 store에서 가져오기
    public Key get(Key key) {
        return store.get(key.getKey());
    }

    //KEY를 이용해서 객체를 return 할 때 사용
    public Key get(String str) {
        return store.get(str);
    }

    //KEY가 등록되었는지 체크할 때 사용
    public boolean isKey(String str)
    {
        return store.containsKey(str);
    }

    /**
     * 새로 만든 STACK의 KEY와 기존에 저장된 STACK의 KEY가 중복이 있는지 검사하는 함수
     */
    public boolean isOverlap(String str1, String str2) {
        boolean flag = get(str1).getValue().contains(str2);

        return flag;
        /*
        for(String key : keyRepository.store.keySet()){
            if((null != (keyRepository.get(key)).getValue() || "" != (keyRepository.get(key)).getValue()) ) {
                if (str == keyRepository.get(key).getValue()) {
                    flag = true;
                    break;
                }
            }
        }
        */

    }

    //테스트 코드 작성 용도로 사용
    public void clearStore(){
        store.clear();
    }
}
