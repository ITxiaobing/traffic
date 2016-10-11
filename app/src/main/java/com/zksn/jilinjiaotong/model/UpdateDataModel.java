package com.zksn.jilinjiaotong.model;

import java.util.List;


public class UpdateDataModel {
    private int returnCode;
    private List<YubaosBean> yubaos;

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
        private String zhanHao;
        private String riQi;
        private String shiCi;
        private String shiXiao;
        private String wenDu;
        private String ludian;
        private String qiYa;
        private String fengSu;
        private String fengXiang;
        private String shiDu;
        private String jiangShui;

        public String getZhanHao() {
            return zhanHao;
        }

        public void setZhanHao(String zhanHao) {
            this.zhanHao = zhanHao;
        }

        public String getRiQi() {
            return riQi;
        }

        public void setRiQi(String riQi) {
            this.riQi = riQi;
        }

        public String getShiCi() {
            return shiCi;
        }

        public void setShiCi(String shiCi) {
            this.shiCi = shiCi;
        }

        public String getShiXiao() {
            return shiXiao;
        }

        public void setShiXiao(String shiXiao) {
            this.shiXiao = shiXiao;
        }

        public String getWenDu() {
            return wenDu;
        }

        public void setWenDu(String wenDu) {
            this.wenDu = wenDu;
        }

        public String getLudian() {
            return ludian;
        }

        public void setLudian(String ludian) {
            this.ludian = ludian;
        }

        public String getQiYa() {
            return qiYa;
        }

        public void setQiYa(String qiYa) {
            this.qiYa = qiYa;
        }

        public String getFengSu() {
            return fengSu;
        }

        public void setFengSu(String fengSu) {
            this.fengSu = fengSu;
        }

        public String getFengXiang() {
            return fengXiang;
        }

        public void setFengXiang(String fengXiang) {
            this.fengXiang = fengXiang;
        }

        public String getShiDu() {
            return shiDu;
        }

        public void setShiDu(String shiDu) {
            this.shiDu = shiDu;
        }

        public String getJiangShui() {
            return jiangShui;
        }

        public void setJiangShui(String jiangShui) {
            this.jiangShui = jiangShui;
        }
    }
}
