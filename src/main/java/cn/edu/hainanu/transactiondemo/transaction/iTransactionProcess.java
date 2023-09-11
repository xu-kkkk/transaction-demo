package cn.edu.hainanu.transactiondemo.transaction;

import com.alibaba.fastjson.JSONObject;

@Deprecated
public interface iTransactionProcess {
    /**
     * 通过Transaction Hash 获取以太坊上的交易信息
     *
     * @param nodeURL
     * @param transactionHash
     * @return
     */
    public JSONObject getTransactionJsonOnEthereum(String nodeURL, String transactionHash);

    /**
     * 通过Transaction Hash 获取Cosmos上的交易信息
     *
     * @param nodeURL
     * @param transactionHash
     * @return
     */
    public JSONObject getTransactionJsonOnCosmos(String nodeURL, String transactionHash);
}
