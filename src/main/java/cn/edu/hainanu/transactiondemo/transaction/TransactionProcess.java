package cn.edu.hainanu.transactiondemo.transaction;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.EthLog;
import org.web3j.protocol.core.methods.response.EthTransaction;
import org.web3j.protocol.core.methods.response.Transaction;
import org.web3j.protocol.http.HttpService;


import java.io.IOException;

/**
 * @author: Tiankai Xu
 * @create: 2023-09-03 16:17
 */
@Deprecated
public class TransactionProcess implements iTransactionProcess {
    @Override
    public JSONObject getTransactionJsonOnEthereum(String nodeURL, String transactionHash) {
        Web3j web3j = Web3j.build(new HttpService(nodeURL));

        EthTransaction ethTransaction = null;
        EthLog ethLog = null;

        String address = "0x23B0Eb582eD4d624fa22c3eB619F14fEd8f9dd9f";
        EthFilter ethFilter = new EthFilter(
                DefaultBlockParameterName.EARLIEST,
                DefaultBlockParameterName.LATEST,
                address
        );

        try {
            ethTransaction = web3j.ethGetTransactionByHash(transactionHash).send();
            ethLog = web3j.ethGetLogs(ethFilter).send();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (ethTransaction != null) {
            // Success
            Transaction resultTransaction = ethTransaction.getResult();
//            System.out.println("Gas price: " + resultTransaction.getGasPrice());
//            System.out.println("tx input: " + resultTransaction.getInput());

            JSONObject jsonEthLog = (JSONObject) JSONObject.toJSON(ethLog);
            System.out.println("ethLog: " + jsonEthLog);

            JSONObject jsonTransaction = (JSONObject) JSONObject.toJSON(resultTransaction);
//            System.out.println(jsonTransaction);
            return jsonTransaction;
        } else {
            // Fail
            System.out.println("未获取交易信息！");
            return null;
        }
    }



    @Override
    public JSONObject getTransactionJsonOnCosmos(String nodeURL, String transactionHash) {
        OkHttpClient client = new OkHttpClient();

        String endPoint = nodeURL + "/blocks/latest";
        Request request = new Request.Builder()
                .url(endPoint)
                .get()
                .build();

        Response response = null;
        String responseBody = null;
        try {
            response = client.newCall(request).execute();
            responseBody = response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (responseBody != null) {
            System.out.println(responseBody);
        } else {
            System.out.println("NULL!!!");
        }

        return JSON.parseObject(responseBody);
    }
}
