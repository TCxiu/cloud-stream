package test.provider.enumeration;

public enum ExchangeTypeEnum {
    DIRECTEXCHANGE("directExchange"),TOPICEXCHANGE("topicExchange"),FANOUTEXCHANGE("fanoutExchange"),HEADERSEXCHANGE("headersExchange");

    private String exchangeName;

    ExchangeTypeEnum(String exchangeName){
        this.exchangeName=exchangeName;
    }

    public String getExchangeName() {
        return exchangeName;
    }
}
