package com.localmood.domain.space.dto;

import java.util.List;
import java.util.Optional;

import com.localmood.common.utils.ArrayUtil;
import com.localmood.domain.space.entity.SpaceDish;
import com.localmood.domain.space.entity.SpaceSubType;
import com.localmood.domain.space.entity.SpaceType;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SpaceDetailDto {

	private Long id;
	private String name;
	private List<String> imgUrlList;
	private String address;
	private SpaceType type;
	private Optional<SpaceSubType> subType;
	private Optional<SpaceDish> dish;
	private String dishDesc;
	private String visitorNum;
	private String optionalService;
	private List<String> purpose;
	private String mood;
	private String music;
	private Optional<String[][]> positiveEval;
	private Optional<String[][]> negativeEval;
	private Boolean isScraped;

	@Builder
	public SpaceDetailDto(Long id, String name, List<String> imgUrlList, String address, SpaceType type, Optional<SpaceSubType> subType, Optional<SpaceDish> dish, String dishDesc, String visitorNum, String optionalService, String purpose, String mood, String music, Optional<String> positiveEval, Optional<String> negativeEval, Boolean isScraped){
		this.id = id;
		this.name = name;
		this.imgUrlList = imgUrlList;
		this.address = address;
		this.type = type;
		this.subType = subType;
		this.dish = dish;
		this.dishDesc = dishDesc;
		this.visitorNum = visitorNum;
		this.optionalService = optionalService;
		this.purpose = ArrayUtil.toArr(purpose);
		this.mood = mood;
		this.music = music;
		this.positiveEval = positiveEval.isEmpty() ? null :
				Optional.of(ArrayUtil.to2DArr(String.valueOf(positiveEval)));
		this.negativeEval = negativeEval.isEmpty() ? null :
				Optional.of(ArrayUtil.to2DArr(String.valueOf(negativeEval)));
		this.isScraped = isScraped;
	}
}
