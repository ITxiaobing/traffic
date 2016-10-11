package com.zksn.jilinjiaotong.model;

import java.util.List;

/**
 * 
 * @author wlc
 * ��ʱ��Ԥ��
 */
public class Fenshiduan {
    private String city_name;
    private String data_time;
    private List<DFList> dfList;
    
	public String getCity_name() {
		return city_name;
	}
	public void setCity_name(String city_name) {
		this.city_name = city_name;
	}
	public String getData_time() {
		return data_time;
	}
	public void setData_time(String data_time) {
		this.data_time = data_time;
	}
	public List<DFList> getDfList() {
		return dfList;
	}
	public void setDfList(List<DFList> dfList) {
		this.dfList = dfList;
	}
}
