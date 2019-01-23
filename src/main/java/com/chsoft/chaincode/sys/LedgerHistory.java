package com.chsoft.chaincode.sys;

/**
 * Created by lixing on 2019/1/16.
 */
public class LedgerHistory {
    public String tx_id;

    public String value;

    public Timestamp timestamp;

    public String getTx_id() {
        return tx_id;
    }

    public void setTx_id(String tx_id) {
        this.tx_id = tx_id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public class Timestamp{

        public String seconds;

        public String nanos;

        public String getSeconds() {
            return seconds;
        }

        public void setSeconds(String seconds) {
            this.seconds = seconds;
        }

        public String getNanos() {
            return nanos;
        }

        public void setNanos(String nanos) {
            this.nanos = nanos;
        }
    }

}
