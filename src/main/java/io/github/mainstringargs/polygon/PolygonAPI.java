package io.github.mainstringargs.polygon;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.google.gson.reflect.TypeToken;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import io.github.mainstringargs.polygon.domain.SymbolAnalystRatings;
import io.github.mainstringargs.polygon.domain.SymbolDetails;
import io.github.mainstringargs.polygon.domain.SymbolDividend;
import io.github.mainstringargs.polygon.domain.SymbolEarning;
import io.github.mainstringargs.polygon.domain.SymbolEndpoints;
import io.github.mainstringargs.polygon.domain.SymbolFinancial;
import io.github.mainstringargs.polygon.domain.SymbolNews;
import io.github.mainstringargs.polygon.nats.PolygonNatsClient;
import io.github.mainstringargs.polygon.nats.PolygonStreamListener;
import io.github.mainstringargs.polygon.properties.PolygonProperties;
import io.github.mainstringargs.polygon.rest.PolygonRequest;
import io.github.mainstringargs.polygon.rest.exceptions.PolygonAPIException;
import io.github.mainstringargs.polygon.rest.meta.symbols.GetSymbolEndpointsRequestBuilder;

/**
 * The Class PolygonAPI.
 */
public class PolygonAPI {

  /** The logger. */
  private static Logger LOGGER = LogManager.getLogger(PolygonAPI.class);

  /** The polygon nats client. */
  private final PolygonNatsClient polygonNatsClient;

  /** The polygon request. */
  private final PolygonRequest polygonRequest;

  /** The base data url. */
  private String baseDataUrl;


  /**
   * Instantiates a new polygon API.
   */
  public PolygonAPI() {

    this(PolygonProperties.KEY_ID_VALUE);
  }

  /**
   * Instantiates a new polygon API.
   *
   * @param keyId the key id
   */
  public PolygonAPI(String keyId) {
    this(PolygonProperties.KEY_ID_VALUE, PolygonProperties.POLYGON_NATS_SERVERS_VALUE);

  }

  /**
   * Instantiates a new polygon API.
   *
   * @param keyId the key id
   * @param polygonNatsServers the polygon nats servers
   */
  public PolygonAPI(String keyId, String... polygonNatsServers) {

    LOGGER.info("PolygonAPI is using the following properties: \nkeyId: " + keyId
        + "\npolygonNatsServers " + Arrays.toString(polygonNatsServers));

    polygonRequest = new PolygonRequest(keyId);
    polygonNatsClient = new PolygonNatsClient(keyId, polygonNatsServers);
    baseDataUrl = PolygonProperties.BASE_DATA_URL_VALUE;

  }

  /**
   * Gets the symbol endpoints.
   *
   * @param symbol the symbol
   * @return the symbol endpoints
   * @throws PolygonAPIException the polygon API exception
   */
  public SymbolEndpoints getSymbolEndpoints(String symbol) throws PolygonAPIException {

    GetSymbolEndpointsRequestBuilder urlBuilder = new GetSymbolEndpointsRequestBuilder(baseDataUrl);

    urlBuilder.symbol(symbol);

    HttpResponse<JsonNode> response = polygonRequest.invokeGet(urlBuilder);

    if (response.getStatus() != 200) {
      throw new PolygonAPIException(response);
    }

    SymbolEndpoints symbolEndpoints =
        polygonRequest.getResponseObject(response, SymbolEndpoints.class);

    return symbolEndpoints;
  }

  /**
   * Gets the symbol details.
   *
   * @param symbol the symbol
   * @return the symbol details
   * @throws PolygonAPIException the polygon API exception
   */
  public SymbolDetails getSymbolDetails(String symbol) throws PolygonAPIException {

    GetSymbolEndpointsRequestBuilder urlBuilder = new GetSymbolEndpointsRequestBuilder(baseDataUrl);

    urlBuilder.symbol(symbol);
    urlBuilder.symbolEndpoint("company");

    HttpResponse<JsonNode> response = polygonRequest.invokeGet(urlBuilder);

    if (response.getStatus() != 200) {
      throw new PolygonAPIException(response);
    }

    SymbolDetails symbolDetails = polygonRequest.getResponseObject(response, SymbolDetails.class);

    return symbolDetails;
  }

  /**
   * Gets the symbol analyst ratings.
   *
   * @param symbol the symbol
   * @return the symbol analyst ratings
   * @throws PolygonAPIException the polygon API exception
   */
  public SymbolAnalystRatings getSymbolAnalystRatings(String symbol) throws PolygonAPIException {

    GetSymbolEndpointsRequestBuilder urlBuilder = new GetSymbolEndpointsRequestBuilder(baseDataUrl);

    urlBuilder.symbol(symbol);
    urlBuilder.symbolEndpoint("analysts");

    HttpResponse<JsonNode> response = polygonRequest.invokeGet(urlBuilder);

    if (response.getStatus() != 200) {
      throw new PolygonAPIException(response);
    }

    SymbolAnalystRatings symbolDetails =
        polygonRequest.getResponseObject(response, SymbolAnalystRatings.class);

    return symbolDetails;
  }

  /**
   * Gets the symbol dividends.
   *
   * @param symbol the symbol
   * @return the symbol dividends
   * @throws PolygonAPIException the polygon API exception
   */
  public List<SymbolDividend> getSymbolDividends(String symbol) throws PolygonAPIException {

    GetSymbolEndpointsRequestBuilder urlBuilder = new GetSymbolEndpointsRequestBuilder(baseDataUrl);

    urlBuilder.symbol(symbol);
    urlBuilder.symbolEndpoint("dividends");

    HttpResponse<JsonNode> response = polygonRequest.invokeGet(urlBuilder);

    if (response.getStatus() != 200) {
      throw new PolygonAPIException(response);
    }

    Type listType = new TypeToken<List<SymbolDividend>>() {}.getType();

    List<SymbolDividend> symbolDetails = polygonRequest.getResponseObject(response, listType);

    return symbolDetails;
  }

  /**
   * Gets the symbol earnings.
   *
   * @param symbol the symbol
   * @return the symbol earnings
   * @throws PolygonAPIException the polygon API exception
   */
  public List<SymbolEarning> getSymbolEarnings(String symbol) throws PolygonAPIException {

    GetSymbolEndpointsRequestBuilder urlBuilder = new GetSymbolEndpointsRequestBuilder(baseDataUrl);

    urlBuilder.symbol(symbol);
    urlBuilder.symbolEndpoint("earnings");

    HttpResponse<JsonNode> response = polygonRequest.invokeGet(urlBuilder);

    if (response.getStatus() != 200) {
      throw new PolygonAPIException(response);
    }

    Type listType = new TypeToken<List<SymbolEarning>>() {}.getType();

    List<SymbolEarning> symbolDetails = polygonRequest.getResponseObject(response, listType);

    return symbolDetails;
  }


  /**
   * Gets the symbol financials.
   *
   * @param symbol the symbol
   * @return the symbol financials
   * @throws PolygonAPIException the polygon API exception
   */
  public List<SymbolFinancial> getSymbolFinancials(String symbol) throws PolygonAPIException {

    GetSymbolEndpointsRequestBuilder urlBuilder = new GetSymbolEndpointsRequestBuilder(baseDataUrl);

    urlBuilder.symbol(symbol);
    urlBuilder.symbolEndpoint("financials");

    HttpResponse<JsonNode> response = polygonRequest.invokeGet(urlBuilder);

    if (response.getStatus() != 200) {
      throw new PolygonAPIException(response);
    }

    Type listType = new TypeToken<List<SymbolFinancial>>() {}.getType();

    List<SymbolFinancial> symbolDetails = polygonRequest.getResponseObject(response, listType);

    return symbolDetails;
  }

  /**
   * Gets the symbol news.
   *
   * @param symbol the symbol
   * @return the symbol news
   * @throws PolygonAPIException the polygon API exception
   */
  public List<SymbolNews> getSymbolNews(String symbol) throws PolygonAPIException {

    GetSymbolEndpointsRequestBuilder urlBuilder = new GetSymbolEndpointsRequestBuilder(baseDataUrl);

    urlBuilder.symbol(symbol);
    urlBuilder.symbolEndpoint("news");

    HttpResponse<JsonNode> response = polygonRequest.invokeGet(urlBuilder);

    if (response.getStatus() != 200) {
      throw new PolygonAPIException(response);
    }

    Type listType = new TypeToken<List<SymbolNews>>() {}.getType();


    List<SymbolNews> symbolDetails = polygonRequest.getResponseObject(response, listType);

    return symbolDetails;
  }


  /**
   * Adds the polygon stream listener.
   *
   * @param streamListener the stream listener
   */
  public void addPolygonStreamListener(PolygonStreamListener streamListener) {
    polygonNatsClient.addListener(streamListener);
  }


  /**
   * Removes the polygon stream listener.
   *
   * @param streamListener the stream listener
   */
  public void removePolygonStreamListener(PolygonStreamListener streamListener) {
    polygonNatsClient.removeListener(streamListener);
  }

}
