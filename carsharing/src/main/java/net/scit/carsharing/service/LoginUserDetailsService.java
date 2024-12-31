package net.scit.carsharing.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.scit.carsharing.dto.LoginUserDetails;
import net.scit.carsharing.entity.UserEntity;
import net.scit.carsharing.repository.UserRepository;

@Service
@RequiredArgsConstructor
@Slf4j
public class LoginUserDetailsService implements UserDetailsService {
   private final UserRepository repository;
   
   // Override는 부모로부터 상속받은 메소드를 다시 재정의하는 행위
   // 변경 가능 : 매개변수명과 접근지정자보다 큰 지정자로 바꾸는 것만 가능
   // loadUserByUsername()의 비밀번호 비교는 명시적으로 없다!
   // 자바의 다형성!!
   @Override
   public UserDetails loadUserByUsername(String userId) 
         throws UsernameNotFoundException {
      // Repository로 연결하는 코드
      UserEntity userEntity = repository.findByUserId(userId);

      if (userEntity != null) {   // 올바른 값을 입력
         // 일반 DTO로 변환하면 안되오!
         // LoginUserDetails로 변환해서 반환해야 함!
         // userDetails는 DTO의 한 종류
    	  LoginUserDetails userDetails = LoginUserDetails.toDTO(userEntity);              
         
         return userDetails;
      } else   {
         throw new UsernameNotFoundException("좀 돼라");      // 이 메세지를 사용하려면 새로운 클래스를 만들어야 한다
      }
   }
}
