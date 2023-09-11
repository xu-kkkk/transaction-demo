package cn.edu.hainanu.transactiondemo.entity;

/**
 * 实体类
 *
 * @author: Tiankai Xu
 * @create: 2023-09-10 13:27
 */
public class TransactionInfo {

    String from;
    String to;
    String value;

    public TransactionInfo(String from, String to, String value) {
        this.from = from;
        this.to = to;
        this.value = value;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
