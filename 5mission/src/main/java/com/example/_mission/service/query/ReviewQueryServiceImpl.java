package com.example._mission.service.query;

import com.example._mission.converter.ReviewConverter;
import com.example._mission.domain.Review;
import com.example._mission.domain.Store;
import com.example._mission.dto.ReviewResDTO;
import com.example._mission.global.apiPayload.code.StoreErrorCode;
import com.example._mission.global.apiPayload.exception.StoreException;
import com.example._mission.repository.ReviewRepository;
import com.example._mission.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true) // 조회 전용 -> 읽기 성능 최적화
public class ReviewQueryServiceImpl implements ReviewQueryService {

    private final StoreRepository storeRepository;
    private final ReviewRepository reviewRepository;

    @Override
    // [수정] 파라미터가 String storeName으로 변경됨
    public ReviewResDTO.ReviewPreViewListDTO getReviewList(String storeName, Integer page) {

        // 1. 가게 확인 (이름으로 찾기!)
        // [수정] findById -> findByName으로 변경
        Store store = storeRepository.findByStoreName(storeName)
                .orElseThrow(() -> new StoreException(StoreErrorCode.STORE_NOT_FOUND));

        // 2. 페이징 정보 생성
        PageRequest pageRequest = PageRequest.of(page, 10);

        // 3. 리뷰 목록 조회
        Page<Review> reviewPage = reviewRepository.findAllByStore(store, pageRequest);

        // 4. DTO 변환
        return ReviewConverter.toReviewPreViewListDTO(reviewPage);
    }
}