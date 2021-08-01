package com.kakaoins.keycontrol.service;

import com.kakaoins.keycontrol.constans.ErrCode;
import com.kakaoins.keycontrol.domain.Key;
import com.kakaoins.keycontrol.exception.APIException;
import com.kakaoins.keycontrol.repository.KeyRepository;

public class KeyService {
    private final KeyRepository keyRepository = new KeyRepository();

    /**
     * KEY 정보를 등록하는 API
     *
     */
    public void CreateKeyInfo(Key key) {
        //이미 등록된 key 인지 확인 후 등록이 된 key는 등록 금지
        if (true == keyRepository.isKey(key.getKey())){

            //이미 등록된 key는 등록 불가
            throw new APIException("이미 등록된 KEY 입니다.", ErrCode.E0000.getCode());

        }

        //등록이 안된 key만 등록 가능
        else {
            Key newkey = new Key();

            newkey.setKey(key.getKey());
            newkey.setDescription(key.getDescription());
            newkey.setType(key.getType());
            newkey.setGenerator(key.getGenerator());
            newkey.setMin_length(key.getMin_length());

            keyRepository.save(newkey);
        }
    }

    /**
     * 각 KEY 별로 새로운 KEY를 하나 발급 받는 API
     * 새로운 KEY 생성 -> 객체에 저장 -> DB에 저장 -> DB의 VALUE 값 보여주기
     */
    public String GenerateKey(String str) {
        //새로운 KEY 생성

        //객체에 KEY 저장
        //HASH MAP의 KEY가 STR 인게 있는가
        if (true == keyRepository.isKey(str)) {

            //KEY값이 없으면 생성
            if (null == (keyRepository.get(str)).getValue()
                || "" == (keyRepository.get(str)).getValue()) {


                //STRING KEY로 만들지 NUMBER KEY로 만들지 결정

                //STRING KEY 생성
                GenerateStringKey strkey = new GenerateStringKey();

                if( "string".equals(keyRepository.get(str).getType())){
                    String Stringnewvalue = strkey.GenerateStringKey();

                    //새로 만든 KEY와 기존에 저장된 KEY가 중복이 안될떄까지 계속 검사 후 생성
                    while(isOverlap(Stringnewvalue)){
                        Stringnewvalue = strkey.GenerateStringKey();
                        isOverlap(Stringnewvalue);
                    }

                    //객체의 value에 newvalue를 세팅
                    (keyRepository.get(str)).setValue(Stringnewvalue);
                }

                //NUMBER KEY 생성
                else{
                    GenerateIntKey intkey = new GenerateIntKey();
                    int intnewvalue = 0;

                    //min length
                    int minlength = (keyRepository.get(str)).getMin_length();

                    intnewvalue = intkey.GenerateIntKey(minlength);

                    //새로 만든 KEY와 기존에 저장된 KEY가 중복이 안될떄까지 계속 검사 후 생성

                    while(isOverlap(intnewvalue)){
                        intnewvalue = intkey.GenerateIntKey(minlength);
                        isOverlap(intnewvalue);
                    }

                    //객체의 value에 newvalue를 세팅
                    (keyRepository.get(str)).setValue(Long.toString(intnewvalue));
                    }
            }
        }
        //HASH MAP에 해당 KEY가 등록되지 않았을 경우
        //500 에러를 메시지로 표현
        else {
            throw new APIException("해당 KEY가 존재하지 않습니다.", ErrCode.E0000.getCode());
        }

        return (keyRepository.get(str)).getValue();
    }

    /**
     * 새로 만든 KEY와 기존에 저장된 KEY가 중복이 있는지 검사하는 함수
     */
    public boolean isOverlap(String str) {
        boolean flag = false;

        for(String key : keyRepository.store.keySet()){
            if((null != (keyRepository.get(key)).getValue() || "" != (keyRepository.get(key)).getValue()) ) {
                if (str == keyRepository.get(key).getValue()) {
                    flag = true;
                    break;
                }
            }
        }

        return flag;
    }

    public boolean isOverlap(int num) {
        boolean flag = false;

        for(String key : keyRepository.store.keySet()){
            if( (null != (keyRepository.get(key)).getValue() || "" != (keyRepository.get(key)).getValue())){
                if( Integer.toString(num) == keyRepository.get(key).getValue()){
                    flag = true;
                    break;
                }
            }
        }

        return flag;
    }
}
