package com.zksn.jilinjiaotong.model;

import java.util.List;

public class Yubao {
	private List<YubaosBean> yubaos;
	private int returnCode;

	public int getReturnCode() {
		return returnCode;
	}

	public void setReturnCode(int returnCode) {
		this.returnCode = returnCode;
	}

	public List<YubaosBean> getYubaos() {
		return yubaos;
	}

	public void setYubaos(List<YubaosBean> yubaos) {
		this.yubaos = yubaos;
	}

	public static class YubaosBean {
		private String tianqi1;
		private String tianqi2;
		private String wendu1;
		private String wendu2;
		private String fengxiang1;
		private String fengxiang2;
		private String fengli1;
		private String fengli2;
		private String tianqidaima1;
		private String tianqidaima2;
		private String tianqidaima1_1;
		private String tianqidaima2_1;
		private String xiangqi;
		private String riqi;

		public String getTianqi1() {
			return tianqi1;
		}

		public void setTianqi1(String tianqi1) {
			this.tianqi1 = tianqi1;
		}

		public String getTianqi2() {
			return tianqi2;
		}

		public void setTianqi2(String tianqi2) {
			this.tianqi2 = tianqi2;
		}

		public String getWendu1() {
			return wendu1;
		}

		public void setWendu1(String wendu1) {
			this.wendu1 = wendu1;
		}

		public String getWendu2() {
			return wendu2;
		}

		public void setWendu2(String wendu2) {
			this.wendu2 = wendu2;
		}

		public String getFengxiang1() {
			return fengxiang1;
		}

		public void setFengxiang1(String fengxiang1) {
			this.fengxiang1 = fengxiang1;
		}

		public String getFengxiang2() {
			return fengxiang2;
		}

		public void setFengxiang2(String fengxiang2) {
			this.fengxiang2 = fengxiang2;
		}

		public String getFengli1() {
			return fengli1;
		}

		public void setFengli1(String fengli1) {
			this.fengli1 = fengli1;
		}

		public String getFengli2() {
			return fengli2;
		}

		public void setFengli2(String fengli2) {
			this.fengli2 = fengli2;
		}

		public String getTianqidaima1() {
			return tianqidaima1;
		}

		public void setTianqidaima1(String tianqidaima1) {
			this.tianqidaima1 = tianqidaima1;
		}

		public String getTianqidaima2() {
			return tianqidaima2;
		}

		public void setTianqidaima2(String tianqidaima2) {
			this.tianqidaima2 = tianqidaima2;
		}

		public String getTianqidaima1_1() {
			return tianqidaima1_1;
		}

		public void setTianqidaima1_1(String tianqidaima1_1) {
			this.tianqidaima1_1 = tianqidaima1_1;
		}

		public String getTianqidaima2_1() {
			return tianqidaima2_1;
		}

		public void setTianqidaima2_1(String tianqidaima2_1) {
			this.tianqidaima2_1 = tianqidaima2_1;
		}

		public String getXiangqi() {
			return xiangqi;
		}

		public void setXiangqi(String xiangqi) {
			this.xiangqi = xiangqi;
		}

		public String getRiqi() {
			return riqi;
		}

		public void setRiqi(String riqi) {
			this.riqi = riqi;
		}
	}

}
