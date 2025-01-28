package com.hexa.muinus.users.service;

import com.hexa.muinus.store.domain.Store;
import com.hexa.muinus.store.repository.StoreRepository;
import com.hexa.muinus.users.UserRepository;
import com.hexa.muinus.users.domain.user.Users;
import com.hexa.muinus.users.dto.ConsumerRegisterRequestDto;
import com.hexa.muinus.users.dto.StoreOwnerRegisterRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final StoreRepository storeRepository;

    @Transactional
    public Users registerConsumer(ConsumerRegisterRequestDto requestDto) {
        Users user = Users.builder()
                .userName(requestDto.getUserName())
                .email(requestDto.getUserEmail())
                .telephone(requestDto.getUserTelephone())
                .userType(Users.UserType.U)
                .point(requestDto.getUserPoint())
                .build();
        log.info("Registering consumer " + user.getUserName());
        return userRepository.save(user);
    }

    @Transactional
    public int registerStoreOwner(StoreOwnerRegisterRequestDto requestDto) {
        Users user = Users.builder()
                .userName(requestDto.getUserName())
                .email(requestDto.getUserEmail())
                .telephone(requestDto.getUserTelephone())
                .userType(Users.UserType.U)
                .point(requestDto.getUserPoint())
                .build();
        Users savedUser = userRepository.save(user);

        return storeRepository.saveStore(
                savedUser.getUserNo(),
                requestDto.getStoreName(),
                requestDto.getLocation().getX(),
                requestDto.getLocation().getY(),
                requestDto.getStoreAddress(),
                requestDto.getRegistrationNumber(),
                requestDto.getIsFliMarketAllowed().toString(),
                requestDto.getFliMarketSectionCount()
                );
    }
}
