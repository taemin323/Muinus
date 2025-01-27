package com.hexa.muinus.users.service;

import com.hexa.muinus.store.domain.Store;
import com.hexa.muinus.store.repository.StoreRepository;
import com.hexa.muinus.users.UserRepository;
import com.hexa.muinus.users.domain.user.Users;
import com.hexa.muinus.users.dto.ConsumerRegisterRequestDto;
import com.hexa.muinus.users.dto.StoreOwnerRegisterRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final StoreRepository storeRepository;

    public Users registerConsumer(ConsumerRegisterRequestDto requestDto) {
        Users user = Users.builder()
                .userName(requestDto.getUserName())
                .email(requestDto.getUserEmail())
                .telephone(requestDto.getUserTelephone())
                .userType(Users.UserType.U)
                .point(requestDto.getUserPoint())
                .build();
        return userRepository.save(user);
    }

    public Store registerStoreOwner(StoreOwnerRegisterRequestDto requestDto) {
        Users user = Users.builder()
                .userName(requestDto.getUserName())
                .email(requestDto.getUserEmail())
                .telephone(requestDto.getUserTelephone())
                .userType(Users.UserType.U)
                .point(requestDto.getUserPoint())
                .build();
        Users savedUser = userRepository.save(user);

        Store store = Store.builder()
                .user(savedUser)
                .name(requestDto.getStoreName())
                .address(requestDto.getStoreAddress())
                .location(requestDto.getLocation())
                .storeImageUrl(requestDto.getFliMarketImageUrl())
                .flimarketYn(requestDto.getIsFliMarketAllowed())
                .phone(requestDto.getStorePhoneNumber())
                .build();
        return storeRepository.save(store);
    }
}
