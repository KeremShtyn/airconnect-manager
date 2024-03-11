package com.chainerist.blockchain.manager.util.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "version", "referenceId", "extras", "payload", "page" })
public class ChaineristResponse<T> {
    @JsonProperty("version")
    private String version;
    @JsonProperty("referenceId")
    private String referenceId;
    @JsonProperty("payload")
    private T payload;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getReferenceId() {
        return referenceId;
    }

    public void setReferenceId(String referenceId) {
        this.referenceId = referenceId;
    }


    public T getPayload() {
        return payload;
    }

    public void setPayload(T payload) {
        this.payload = payload;
    }


    public static final class SignResponseBuilder<T> {
        private String version;
        private String referenceId;
        private T payload;

        public SignResponseBuilder<T> withVersion(String version) {
            this.version = version;
            return this;
        }

        public SignResponseBuilder<T> withReferenceId(String referenceId) {
            this.referenceId = referenceId;
            return this;
        }


        public SignResponseBuilder<T> withPayload(T payload) {
            this.payload = payload;
            return this;
        }

        public ChaineristResponse<T> build() {
            ChaineristResponse<T> signResponse = new ChaineristResponse<>();
            signResponse.version = this.version;
            signResponse.referenceId = this.referenceId;
            signResponse.payload = this.payload;
            return signResponse;
        }

    }
}
