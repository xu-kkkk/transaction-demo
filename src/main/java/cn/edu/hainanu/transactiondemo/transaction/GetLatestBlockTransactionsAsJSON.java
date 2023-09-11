package cn.edu.hainanu.transactiondemo.transaction;


import cn.edu.hainanu.transactiondemo.entity.TransactionInfo;
import com.google.gson.Gson;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthBlock;
import org.web3j.protocol.http.HttpService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 获取以太坊最新区块中的所有交易信息
 *
 * @author: Tiankai Xu
 * @create: 2023-09-10 13:08
 */
public class GetLatestBlockTransactionsAsJSON {

    public String getTxInfo() throws IOException {
        // 连接到以太坊节点
        Web3j web3j = Web3j.build(
                new HttpService("https://eth-mainnet.g.alchemy.com/v2/YYNvlN7f-3kl25Mn6Lw-0VDcx6GdgFaf"));

        // 最新区块参数
        DefaultBlockParameterName blockParameterName = DefaultBlockParameterName.LATEST;

        // 获取最新区块信息
        EthBlock.Block block = web3j
                .ethGetBlockByNumber(blockParameterName, true)
                .send()
                .getBlock();


        if (block != null) {
            List<EthBlock.TransactionResult> transactions = block.getTransactions();
            List<TransactionInfo> transactionList = new ArrayList<>();

            // 遍历交易列表，并将交易信息添加到列表中
            for (EthBlock.TransactionResult transactionResult : transactions) {
                EthBlock.TransactionObject transaction = (EthBlock.TransactionObject) transactionResult.get();
                String from = transaction.getFrom();
                String to = transaction.getTo();
                String value = transaction.getValue().toString();

                // 创建交易信息对象并添加到列表中
                TransactionInfo transactionInfo = new TransactionInfo(from, to, value);
                transactionList.add(transactionInfo);
            }

            // 将交易信息列表转换为JSON字符串
            Gson gson = new Gson();
            String jsonOutput = gson.toJson(transactionList);

            // 打印JSON字符串
            System.out.println(jsonOutput);

            return jsonOutput;
        } else {
            System.out.println("Block not found");
            return null;
        }
    }

}
