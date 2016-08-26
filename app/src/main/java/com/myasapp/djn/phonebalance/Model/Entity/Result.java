package com.myasapp.djn.phonebalance.Model.Entity;



import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/8/26.
 */
public class Result implements Serializable {


    /**
     * phoneno : 18493105401
     * iccid : 898602C99816C0445401
     * feeStartDate : 2016-03-31T16:00:00.000Z
     * feeEndDate : 2016-04-30T15:59:59.999Z
     * cardpackage : 3G/月 全国流量
     * totalGprs : 3072
     * cardtype : 物联网11位184号段
     * isMonthly : true
     * history : [{"month":"2016年5月","usedGprs":3081.1787109375,"_id":"57522600d97cc6e81054f237"},{"month":"2016年5月","usedGprs":3081.1787109375,"_id":"575226c9d97cc6e810550dae"},{"month":"2016年7月","usedGprs":3228,"_id":"57b40b786640867800759eaf"}]
     * iotinfo : {"status":"0","message":"正确","result":[{"total_gprs":"1787873"}]}
     * balance : {"status":"0","message":"正确","result":[{"balance":"36.85"}]}
     * gprsrealtime : {"status":"71","message":"已超出API最大流控限制","result":[]}
     * userstatus : {"status":"0","message":"正确","result":[{"STATUS":"00"}]}
     */

    private String phoneno;
    private String iccid;
    private String feeStartDate;
    private String feeEndDate;
    private String cardpackage;
    private Double totalGprs;
    private String cardtype;
    private boolean isMonthly;
    private double surplus;

    /**
     * status : 0
     * message : 正确
     * result : [{"total_gprs":"1787873"}]
     */

    private IotinfoBean iotinfo;
    /**
     * status : 0
     * message : 正确
     * result : [{"balance":"36.85"}]
     */

    private BalanceBean balance;
    /**
     * status : 71
     * message : 已超出API最大流控限制
     * result : []
     */

    private GprsrealtimeBean gprsrealtime;
    /**
     * status : 0
     * message : 正确
     * result : [{"STATUS":"00"}]
     */

    private UserstatusBean userstatus;
    /**
     * month : 2016年5月
     * usedGprs : 3081.1787109375
     * _id : 57522600d97cc6e81054f237
     */

    private List<HistoryBean> history;

    public String getPhoneno() {
        return phoneno;
    }

    public void setPhoneno(String phoneno) {
        this.phoneno = phoneno;
    }

    public String getIccid() {
        return iccid;
    }

    public void setIccid(String iccid) {
        this.iccid = iccid;
    }

    public String getFeeStartDate() {
        return feeStartDate;
    }

    public void setFeeStartDate(String feeStartDate) {
        this.feeStartDate = feeStartDate;
    }

    public String getFeeEndDate() {
        return feeEndDate;
    }

    public void setFeeEndDate(String feeEndDate) {
        this.feeEndDate = feeEndDate;
    }

    public String getCardpackage() {
        return cardpackage;
    }

    public void setCardpackage(String cardpackage) {
        this.cardpackage = cardpackage;
    }

    public Double getTotalGprs() {
        return totalGprs;
    }

    public void setTotalGprs(Double totalGprs) {
        this.totalGprs = totalGprs;
    }

    public String getCardtype() {
        return cardtype;
    }

    public void setCardtype(String cardtype) {
        this.cardtype = cardtype;
    }

    public boolean isIsMonthly() {
        return isMonthly;
    }

    public void setIsMonthly(boolean isMonthly) {
        this.isMonthly = isMonthly;
    }

    public IotinfoBean getIotinfo() {
        return iotinfo;
    }

    public void setIotinfo(IotinfoBean iotinfo) {
        this.iotinfo = iotinfo;
    }

    public BalanceBean getBalance() {
        return balance;
    }

    public void setBalance(BalanceBean balance) {
        this.balance = balance;
    }

    public GprsrealtimeBean getGprsrealtime() {
        return gprsrealtime;
    }

    public void setGprsrealtime(GprsrealtimeBean gprsrealtime) {
        this.gprsrealtime = gprsrealtime;
    }

    public UserstatusBean getUserstatus() {
        return userstatus;
    }

    public void setUserstatus(UserstatusBean userstatus) {
        this.userstatus = userstatus;
    }

    public List<HistoryBean> getHistory() {
        return history;
    }

    public void setHistory(List<HistoryBean> history) {
        this.history = history;
    }

    public double getSurplus() {
        return surplus;
    }

    public void setSurplus(double surplus) {
        this.surplus = surplus;
    }

    public static class IotinfoBean implements Serializable {
        private String status;
        private String message;
        /**
         * total_gprs : 1787873
         */

        private List<ResultBean> result;

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public List<ResultBean> getResult() {
            return result;
        }

        public void setResult(List<ResultBean> result) {
            this.result = result;
        }

        public static class ResultBean implements Serializable {
            private Double total_gprs;

            public Double getTotal_gprs() {
                return total_gprs;
            }

            public void setTotal_gprs(Double total_gprs) {
                this.total_gprs = total_gprs;
            }
        }
    }

    public static class BalanceBean implements Serializable {
        private String status;
        private String message;
        /**
         * balance : 36.85
         */

        private List<ResultBean> result;

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public List<ResultBean> getResult() {
            return result;
        }

        public void setResult(List<ResultBean> result) {
            this.result = result;
        }

        public static class ResultBean implements Serializable {
            private Double balance;

            public Double getBalance() {
                return balance;
            }

            public void setBalance(Double balance) {
                this.balance = balance;
            }
        }
    }

    public static class GprsrealtimeBean implements Serializable {
        private String status;
        private String message;
        private List<?> result;

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public List<?> getResult() {
            return result;
        }

        public void setResult(List<?> result) {
            this.result = result;
        }
    }

    public static class UserstatusBean implements Serializable {
        private String status;
        private String message;
        /**
         * STATUS : 00
         */

        private List<ResultBean> result;

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public List<ResultBean> getResult() {
            return result;
        }

        public void setResult(List<ResultBean> result) {
            this.result = result;
        }

        public static class ResultBean implements Serializable {
            private String STATUS;

            public String getSTATUS() {
                return STATUS;
            }

            public void setSTATUS(String STATUS) {
                this.STATUS = STATUS;
            }
        }
    }

    public static class HistoryBean implements Serializable {
        private String month;
        private double usedGprs;
        private String _id;

        public String getMonth() {
            return month;
        }

        public void setMonth(String month) {
            this.month = month;
        }

        public double getUsedGprs() {
            return usedGprs;
        }

        public void setUsedGprs(double usedGprs) {
            this.usedGprs = usedGprs;
        }

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }
    }
}
