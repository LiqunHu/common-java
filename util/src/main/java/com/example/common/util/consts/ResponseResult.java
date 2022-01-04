package com.example.common.util.consts;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.annotation.JSONType;

import lombok.Data;

@Data
@JSONType(ignores = { "notSuccess", "success" })
public class ResponseResult<T> {

	private String errno;

	private String msg;

	private T info;

	public boolean isSuccess() {
		return StringUtils.equals(errno, ErrorCode.SUCCESS.getCode());
	}

	public boolean isNotSuccess() {
		return !isSuccess();
	}

	@Override
	public String toString() {
		return "[errno:" + errno + ", msg: " + msg + "]";
	}

	public static <M> ResponseResult<M> buildFailure(ErrorCode errorCode) {
		ResponseResult<M> r = new ResponseResult<>();
		r.errno = errorCode.getCode();
		r.msg = errorCode.getValue();
		return r;
	}

	public static <M> ResponseResult<M> buildFailure(String errno, String msg) {
		ResponseResult<M> r = new ResponseResult<>();
		r.errno = errno;
		r.msg = msg;
		return r;
	}

	public static <M> ResponseResult<M> buildSuccess(M m) {
		ResponseResult<M> r = new ResponseResult<>();
		r.errno = ErrorCode.SUCCESS.getCode();
		r.msg = ErrorCode.SUCCESS.getValue();
		r.info = m;
		return r;
	}
}
