package com.prvalentim.pontointeligente.api.response;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Response<T> {

	private T data;
	private List<String> errors= new ArrayList<String>();
}
