package com.ahf.shiro.pojo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Permssion {
	private Integer id;
	private String permssionname;
	private Date permssiontime;

}
