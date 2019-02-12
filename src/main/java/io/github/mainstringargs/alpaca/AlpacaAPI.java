package io.github.mainstringargs.alpaca;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.List;
import com.google.gson.reflect.TypeToken;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import io.github.mainstringargs.alpaca.domain.Account;
import io.github.mainstringargs.alpaca.domain.Calendar;
import io.github.mainstringargs.alpaca.domain.Clock;
import io.github.mainstringargs.alpaca.domain.Order;
import io.github.mainstringargs.alpaca.domain.Position;
import io.github.mainstringargs.alpaca.enums.Direction;
import io.github.mainstringargs.alpaca.enums.OrderSide;
import io.github.mainstringargs.alpaca.enums.OrderStatus;
import io.github.mainstringargs.alpaca.enums.OrderTimeInForce;
import io.github.mainstringargs.alpaca.enums.OrderType;
import io.github.mainstringargs.alpaca.properties.AlpacaProperties;
import io.github.mainstringargs.alpaca.rest.AccountRequestBuilder;
import io.github.mainstringargs.alpaca.rest.AlpacaRequest;
import io.github.mainstringargs.alpaca.rest.AlpacaRequestBuilder;
import io.github.mainstringargs.alpaca.rest.CalendarRequestBuilder;
import io.github.mainstringargs.alpaca.rest.ClockRequestBuilder;
import io.github.mainstringargs.alpaca.rest.OrdersRequestBuilder;
import io.github.mainstringargs.alpaca.rest.PositionsRequestBuilder;

/**
 * The Class AlpacaAPI.
 */
public class AlpacaAPI {

  /** The key id. */
  private String keyId;

  /** The secret. */
  private String secret;

  /** The base url. */
  private String baseUrl;

  /** The alpaca request. */
  private AlpacaRequest alpacaRequest;

  /**
   * Instantiates a new alpaca API. Uses alpaca.properties for configuration.
   */
  public AlpacaAPI() {

    keyId = AlpacaProperties.KEY_ID_VALUE;
    secret = AlpacaProperties.SECRET_VALUE;
    baseUrl = AlpacaProperties.BASE_URL_VALUE;
    alpacaRequest = new AlpacaRequest(keyId, secret);

  }

  /**
   * Instantiates a new alpaca API.
   *
   * @param keyId the key id
   * @param secret the secret
   * @param baseUrl the base url
   */
  public AlpacaAPI(String keyId, String secret, String baseUrl) {
    this.keyId = keyId;
    this.secret = secret;
    this.baseUrl = baseUrl;
    alpacaRequest = new AlpacaRequest(keyId, secret);


  }

  /**
   * Gets the account.
   *
   * @return the account
   */
  public Account getAccount() {
    AlpacaRequestBuilder urlBuilder = new AccountRequestBuilder(baseUrl);

    HttpResponse<JsonNode> response = alpacaRequest.invokeGet(urlBuilder);

    Account account = alpacaRequest.getResponseObject(response, Account.class);

    return account;
  }

  /**
   * Gets the positions.
   *
   * @return the positions
   */
  public List<Position> getPositions() {
    Type listType = new TypeToken<List<Position>>() {}.getType();

    AlpacaRequestBuilder urlBuilder = new PositionsRequestBuilder(baseUrl);

    HttpResponse<JsonNode> response = alpacaRequest.invokeGet(urlBuilder);

    List<Position> positions = alpacaRequest.getResponseObject(response, listType);

    return positions;
  }


  /**
   * Gets the order.
   *
   * @param orderId the order id
   * @return the order
   */
  public Order getOrder(String orderId) {
    Type objectType = new TypeToken<Order>() {}.getType();

    OrdersRequestBuilder urlBuilder = new OrdersRequestBuilder(baseUrl);

    urlBuilder.orderId(orderId);

    HttpResponse<JsonNode> response = alpacaRequest.invokeGet(urlBuilder);

    Order order = alpacaRequest.getResponseObject(response, objectType);

    return order;
  }

  /**
   * Gets the order by client id.
   *
   * @param clientOrderId the cleint order id
   * @return the order
   */
  public Order getOrderByClientId(String clientOrderId) {
    Type objectType = new TypeToken<Order>() {}.getType();

    OrdersRequestBuilder urlBuilder = new OrdersRequestBuilder(baseUrl);

    urlBuilder.ordersByClientOrderId(clientOrderId);

    HttpResponse<JsonNode> response = alpacaRequest.invokeGet(urlBuilder);

    Order order = alpacaRequest.getResponseObject(response, objectType);

    return order;
  }



  // public Order deleteOrder(String orderId) {
  // Type objectType = new TypeToken<Order>() {}.getType();
  //
  // OrdersUrlBuilder urlBuilder = new OrdersUrlBuilder(baseUrl);
  //
  // urlBuilder.orderId(orderId);
  //
  // HttpResponse<JsonNode> response = alpacaRequest.invokeGet(urlBuilder);
  //
  // Order order = alpacaRequest.getResponseObject(response, objectType);
  //
  // return order;
  // }

  /**
   * Request new order.
   *
   * @param symbol the symbol
   * @param quantity the quantity
   * @param side the side
   * @param type the type
   * @param timeInForce the time in force
   * @param limitPrice the limit price
   * @param stopPrice the stop price
   * @param clientOrderId the client order id
   * @return the order
   */
  public Order requestNewOrder(String symbol, Integer quantity, OrderSide side, OrderType type,
      OrderTimeInForce timeInForce, Double limitPrice, Double stopPrice, String clientOrderId) {

    Type objectType = new TypeToken<Order>() {}.getType();

    OrdersRequestBuilder urlBuilder = new OrdersRequestBuilder(baseUrl);

    urlBuilder.symbol(symbol).quantity(quantity).side(side).type(type).timeInForce(timeInForce)
        .limitPrice(limitPrice).stopPrice(stopPrice).clientOrderId(clientOrderId);

    HttpResponse<JsonNode> response = alpacaRequest.invokePost(urlBuilder);

    Order order = alpacaRequest.getResponseObject(response, objectType);

    return order;
  }



  /**
   * Gets the orders with API defaults.
   *
   * @return the orders
   */
  public List<Order> getOrders() {
    Type listType = new TypeToken<List<Order>>() {}.getType();

    AlpacaRequestBuilder urlBuilder = new OrdersRequestBuilder(baseUrl);

    HttpResponse<JsonNode> response = alpacaRequest.invokeGet(urlBuilder);

    List<Order> orders = alpacaRequest.getResponseObject(response, listType);

    return orders;
  }


  /**
   * Gets the orders.
   *
   * @param status the status
   * @param limit the limit
   * @param after the after
   * @param until the until
   * @param direction the direction
   * @return the orders
   */
  public List<Order> getOrders(OrderStatus status, Integer limit, LocalDateTime after,
      LocalDateTime until, Direction direction) {
    Type listType = new TypeToken<List<Order>>() {}.getType();

    OrdersRequestBuilder urlBuilder = new OrdersRequestBuilder(baseUrl);

    urlBuilder.status(status).limit(limit).after(after).until(until).direction(direction);

    HttpResponse<JsonNode> response = alpacaRequest.invokeGet(urlBuilder);

    List<Order> orders = alpacaRequest.getResponseObject(response, listType);

    return orders;
  }



  /**
   * Gets the calendar.
   *
   * @return the calendar
   */
  public List<Calendar> getCalendar() {
    Type listType = new TypeToken<List<Calendar>>() {}.getType();

    AlpacaRequestBuilder urlBuilder = new CalendarRequestBuilder(baseUrl);

    HttpResponse<JsonNode> response = alpacaRequest.invokeGet(urlBuilder);

    List<Calendar> calendar = alpacaRequest.getResponseObject(response, listType);

    return calendar;
  }

  /**
   * Gets the clock.
   *
   * @return the clock
   */
  public Clock getClock() {
    AlpacaRequestBuilder urlBuilder = new ClockRequestBuilder(baseUrl);

    HttpResponse<JsonNode> response = alpacaRequest.invokeGet(urlBuilder);

    Clock clock = alpacaRequest.getResponseObject(response, Clock.class);

    return clock;
  }

}