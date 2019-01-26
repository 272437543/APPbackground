package com.drake.APPbackground.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.drake.APPbackground.entity.SlideEntity;

public interface SlideMapper {
	
	@Select("SELECT * FROM main_slide")
	List<SlideEntity> getAllSlide();

}
