package com.yfbx.rxdemo.bean;

import java.util.List;

/**
 * Author: Edward
 * Date: 2017/11/18 18:54
 * Description:
 */
public class User {


    private String company;
    private String sessionId;
    private boolean bindPhoneFlag;
    private List<Modles> modles;

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public boolean isBindPhoneFlag() {
        return bindPhoneFlag;
    }

    public void setBindPhoneFlag(boolean bindPhoneFlag) {
        this.bindPhoneFlag = bindPhoneFlag;
    }

    public List<Modles> getModles() {
        return modles;
    }

    public void setModles(List<Modles> modles) {
        this.modles = modles;
    }


    @Override
    public String toString() {
        return "User{" +
                "company='" + company + '\'' +
                ", sessionId='" + sessionId + '\'' +
                ", bindPhoneFlag=" + bindPhoneFlag +
                ", modles=" + modles +
                '}';
    }

    class Modles {

        private String modleName;
        private String thekey;

        public String getModleName() {
            return modleName;
        }

        public void setModleName(String modleName) {
            this.modleName = modleName;
        }

        public String getThekey() {
            return thekey;
        }

        public void setThekey(String thekey) {
            this.thekey = thekey;
        }


        @Override
        public String toString() {
            return "Modles{" +
                    "modleName='" + modleName + '\'' +
                    ", thekey='" + thekey + '\'' +
                    '}';
        }
    }
}
