/* 
 * AImpl.java  
 * 
 * version TODO
 *
 * 2015年10月9日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zcbspay.platform.instead.utils.validator.impl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.zcbspay.platform.instead.utils.RegExpValidatorUtil;
import com.zcbspay.platform.instead.utils.StringUtil;
import com.zcbspay.platform.instead.utils.validator.Ns;

/**
 * 【NS】数字字符 或 特殊字符
 *
 * @author yangpeng
 * @version
 * @date 2015年10月8日 下午6:28:07
 * @since
 */

public class NsImpl implements ConstraintValidator<Ns, String> {
	private int max;
	private int min;
	private boolean isNull;

	/**
	 * 初始参数,获取注解中length的值
	 */
	@Override
	public void initialize(Ns arg0) {
		this.max = arg0.max();
		this.min = arg0.min();
		this.isNull = arg0.isNull();
	}

	@Override
	public boolean isValid(String str, ConstraintValidatorContext constraintValidatorContext) {
		if (isNull == true && StringUtil.isEmpty(str)) {
			return true;
		} else {
			boolean isok = false;
			if (StringUtil.isNotEmpty(str)) {
				if (str.length() <= max && str.length() >= min) {
					isok = !RegExpValidatorUtil.IsIntNs(str);

				}
			} else {
				constraintValidatorContext.disableDefaultConstraintViolation();// 禁用默认的message的值
				// 重新添加错误提示语句
				constraintValidatorContext.buildConstraintViolationWithTemplate("该域不能为空").addConstraintViolation();
			}
			return isok;
		}
	}
}
