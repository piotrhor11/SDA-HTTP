package commons;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.math.BigDecimal;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CryptoCurrencyDTO {

  private String id;
  private String name;
  private Integer rank;
  private BigDecimal price_usd;
  private BigDecimal price_btc;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public BigDecimal getPrice_usd() {
        return price_usd;
    }

    public void setPrice_usd(BigDecimal price_usd) {
        this.price_usd = price_usd;
    }

    public BigDecimal getPrice_btc() {
        return price_btc;
    }

    public void setPrice_btc(BigDecimal price_btc) {
        this.price_btc = price_btc;
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder()
                .append("---Crypto currency: ")
                .append(name)
                .append(" ---\n")
                .append("id: ")
                .append(id)
                .append("\nname: ")
                .append(name)
                .append("\nrank: ")
                .append(rank)
                .append("\nprice in USD: ")
                .append(price_usd)
                .append("\nprice in bitcoins: ")
                .append(price_btc);

        return sb.toString();
    }
}

//json response example from https://api.coinmarketcap.com/v1/ticker/
  /*
    "id": "bitcoin",
    "name": "Bitcoin",
    "symbol": "BTC",
    "rank": "1",
    "price_usd": "16170.6",
    "price_btc": "1.0",
    "24h_volume_usd": "16633000000.0",
    "market_cap_usd": "270472689720",
    "available_supply": "16726200.0",
    "total_supply": "16726200.0",
    "max_supply": "21000000.0",
    "percent_change_1h": "-3.08",
    "percent_change_24h": "25.34",
    "percent_change_7d": "66.41",
    "last_updated": "1512667154"
    */