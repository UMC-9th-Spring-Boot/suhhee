package com.example._mission.service.command;

import com.example._mission.converter.ReviewConverter;
import com.example._mission.domain.Review;
import com.example._mission.domain.Store;
import com.example._mission.domain.User;
import com.example._mission.dto.ReviewReqDTO;
import com.example._mission.dto.ReviewResDTO;
import com.example._mission.global.apiPayload.code.GeneralErrorCode; // GeneralErrorCode 사용
import com.example._mission.global.apiPayload.exception.GeneralException; // GeneralException 사용
import com.example._mission.repository.ReviewRepository;
import com.example._mission.repository.StoreRepository;
import com.example._mission.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReviewCommandServiceImpl implements ReviewCommandService {

    // 필요한 리포지토리 주입
    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final StoreRepository storeRepository;

    @Override
    @Transactional
    public ReviewResDTO.AddReviewDTO addReview(ReviewReqDTO.AddReviewDTO dto, Long storeId) {

        // 유저 하드코딩
        Long hardcodedUserId = 1L;

        // 하드코딩된 ID로 유저 찾음
        User user = userRepository.findById(hardcodedUserId)
                .orElseThrow(() -> new GeneralException(GeneralErrorCode.MEMBER_NOT_FOUND));

        // URL로 받은 storeId로 가게 찾기
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new GeneralException(GeneralErrorCode.NOT_FOUND));

        // 컨버터를 사용(DTO -> Review 엔티티) (User, Store 정보 포함)
        Review newReview = ReviewConverter.toReview(dto, user, store);

        // Review 저장
        Review savedReview = reviewRepository.save(newReview);

        // 컨버터를 사용(응답 DTO로 변환) & 반환
        return ReviewConverter.toAddReviewDTO(savedReview);
    }
}