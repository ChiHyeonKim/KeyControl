package com.kakaoins.keycontrol.service;

import com.kakaoins.keycontrol.constans.ErrCode;
import com.kakaoins.keycontrol.domain.Key;
import com.kakaoins.keycontrol.dto.GenerateKeyDTO;
import com.kakaoins.keycontrol.dto.GetKeyDTO;
import com.kakaoins.keycontrol.exception.APIException;
import com.kakaoins.keycontrol.repository.KeyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KeyService {
    @Autowired
    KeyRepository keyRepository;

    /**
     * KEY 정보를 등록하는 API
     *
     */
    public void CreateKeyInfo(GenerateKeyDTO generateKeyDTO) {
        //이미 등록된 key 인지 확인 후 등록이 된 key는 등록 금지
        if (true == keyRepository.isKey(generateKeyDTO.getKey())){

            //이미 등록된 key는 등록 불가
            throw new APIException("이미 등록된 KEY 입니다.", ErrCode.E0000.getCode());

        }

        //등록이 안된 key만 등록 가능
        else {

            //key에 데이터 저장을 위해
            //DTO의 값을 Key로 복사
            Key key = new Key( generateKeyDTO.getKey()
                            , generateKeyDTO.getDescription()
                            , generateKeyDTO.getType()
                            , generateKeyDTO.getGenerator()
                            , generateKeyDTO.getMin_length()  );

            keyRepository.save(key);
        }
    }

    /**
     * 각 KEY 별로 새로운 KEY를 하나 발급 받는 API
     * 새로운 KEY 생성 -> Stack 저장 -> Stack의 제일 위 값 보여주기
     */
    public void GenerateKey(String str) {
        //HashMap에 KEY가 str 인게 있을 때만 HashSet에 새로운 key 생성
        if (true == keyRepository.isKey(str)) {


            //HashSet에 key 값이 없으면 생성
            //if (null == (keyRepository.get(str)).getValue()
            //    || "" == (keyRepository.get(str)).getValue()) {


            //STRING KEY로 만들지 NUMBER KEY로 만들지 결정

            //TYPE이 STRING인 경우에 STRING KEY 생성
            if( "string".equals(keyRepository.get(str).getType())){
                GenerateStringKey strkey = new GenerateStringKey();
                String Stringnewvalue = strkey.GenerateStringKey();

                //새로 만든 KEY와 기존에 저장된 KEY가 중복이 안될떄까지 계속 검사 후 생성
                while(keyRepository.isOverlap(str, Stringnewvalue)){
                    Stringnewvalue = strkey.GenerateStringKey();
                    keyRepository.isOverlap(str, Stringnewvalue);
                }

                //객체의 value에 newvalue를 세팅
                //(keyRepository.get(str)).setValue(Stringnewvalue);
                (keyRepository.get(str)).setValue(Stringnewvalue);
            }

            //NUMBER KEY 생성
            else{
                GenerateNumberKey numberkey = new GenerateNumberKey();
                String numbernewvalue;

                //min length
                int minlength = (keyRepository.get(str)).getMin_length();

                numbernewvalue = numberkey.GenerateNumberKey(minlength);

                //새로 만든 KEY와 기존에 저장된 KEY가 중복이 안될떄까지 계속 검사 후 생성

                while (keyRepository.isOverlap(str, numbernewvalue)) {
                    numbernewvalue = numberkey.GenerateNumberKey(minlength);
                    keyRepository.isOverlap(str, numbernewvalue);
                }

                //객체의 Stacck에 new key를 세팅
                //(keyRepository.get(str)).setValue(Long.toString(intnewvalue));
                (keyRepository.get(str)).setValue(numbernewvalue);
            }
            //}
        }
        //HASH MAP에 해당 KEY가 등록되지 않았을 경우
        //500 에러를 메시지로 표현
        else {
            throw new APIException("해당 KEY가 존재하지 않습니다.", ErrCode.E0000.getCode());
        }
    }

    /**
     * 새롭게 생성된 Stack 내부의 KEY를 RETURN 하는 함수
     */
    public GetKeyDTO GetStackKey(String str){
        GetKeyDTO getKeyDTO = new GetKeyDTO();
        getKeyDTO.setValue( keyRepository.get(str).getValue().peek() );

        return getKeyDTO;
    }
}
