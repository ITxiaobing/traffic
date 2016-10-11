package com.zksn.jilinjiaotong.model;

import java.util.List;

public class WeixingLeidaModel {
	private List<WeixingLeida> weiXing;
	public List<WeixingLeida> getWeiXing() {
		return weiXing;
	}
	public void setWeiXing(List<WeixingLeida> weiXing) {
		this.weiXing = weiXing;
	}


	public class WeixingLeida{
		private String time;
		private String imgUrl;
		public String getTime() {
			return time;
		}
		public void setTime(String time) {
			this.time = time;
		}
		public String getImgUrl() {
			return imgUrl;
		}
		public void setImgUrl(String imgUrl) {
			this.imgUrl = imgUrl;
		}
	}
}


