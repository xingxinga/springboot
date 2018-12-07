package com.chsoft.fabric.manage.fabricChaincode.entity;

import org.hyperledger.fabric.sdk.ChaincodeID;
import org.hyperledger.fabric.sdk.TransactionRequest;
import org.springframework.stereotype.Component;

@Component
public class FabricChaincode {
    private String id;

    private String chaincodeName;

    private String chaincodePath;

    private String chaincodeFilepath;

    private String chaincodeVersion;

    private Integer invokeWatiTime = 300000;

    private Integer deployWatiTime = 600000;

    private String endorsementPolicyFilePath;

    private String instantiatePolicyFilePath;

    private String transactionName;

    private ChaincodeID chaincodeID;

    // Chaincode language
    private TransactionRequest.Type chaincodeLanguage = TransactionRequest.Type.GO_LANG;

    public ChaincodeID getChaincodeID() {
        return chaincodeID;
    }

    public void setChaincodeID(ChaincodeID chaincodeID) {
        this.chaincodeID = chaincodeID;
    }

    public TransactionRequest.Type getChaincodeLanguage() {
        return chaincodeLanguage;
    }

    public void setChaincodeLanguage(TransactionRequest.Type chaincodeLanguage) {
        this.chaincodeLanguage = chaincodeLanguage;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getChaincodeName() {
        return chaincodeName;
    }

    public void setChaincodeName(String chaincodeName) {
        this.chaincodeName = chaincodeName == null ? null : chaincodeName.trim();
    }

    public String getChaincodePath() {
        return chaincodePath;
    }

    public void setChaincodePath(String chaincodePath) {
        this.chaincodePath = chaincodePath == null ? null : chaincodePath.trim();
    }

    public String getChaincodeFilepath() {
        return chaincodeFilepath;
    }

    public void setChaincodeFilepath(String chaincodeFilepath) {
        this.chaincodeFilepath = chaincodeFilepath == null ? null : chaincodeFilepath.trim();
    }

    public String getChaincodeVersion() {
        return chaincodeVersion;
    }

    public void setChaincodeVersion(String chaincodeVersion) {
        this.chaincodeVersion = chaincodeVersion == null ? null : chaincodeVersion.trim();
    }

    public Integer getInvokeWatiTime() {
        return invokeWatiTime;
    }

    public void setInvokeWatiTime(Integer invokeWatiTime) {
        this.invokeWatiTime = invokeWatiTime;
    }

    public Integer getDeployWatiTime() {
        return deployWatiTime;
    }

    public void setDeployWatiTime(Integer deployWatiTime) {
        this.deployWatiTime = deployWatiTime;
    }

    public String getEndorsementPolicyFilePath() {
        return endorsementPolicyFilePath;
    }

    public void setEndorsementPolicyFilePath(String endorsementPolicyFilePath) {
        this.endorsementPolicyFilePath = endorsementPolicyFilePath == null ? null : endorsementPolicyFilePath.trim();
    }

    public String getInstantiatePolicyFilePath() {
        return instantiatePolicyFilePath;
    }

    public void setInstantiatePolicyFilePath(String instantiatePolicyFilePath) {
        this.instantiatePolicyFilePath = instantiatePolicyFilePath == null ? null : instantiatePolicyFilePath.trim();
    }

    public String getTransactionName() {
        return transactionName;
    }

    public void setTransactionName(String transactionName) {
        this.transactionName = transactionName == null ? null : transactionName.trim();
    }
}