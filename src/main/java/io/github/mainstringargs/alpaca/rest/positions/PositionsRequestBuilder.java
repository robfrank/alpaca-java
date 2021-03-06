package io.github.mainstringargs.alpaca.rest.positions;

import io.github.mainstringargs.alpaca.rest.AlpacaRequestBuilder;

/**
 * The Class PositionsRequestBuilder.
 */
public abstract class PositionsRequestBuilder extends AlpacaRequestBuilder {


  /** The Constant POSITIONS_ENDPOINT. */
  public final static String POSITIONS_ENDPOINT = "positions";

  /**
   * Instantiates a new positions request builder.
   *
   * @param baseUrl the base url
   */
  public PositionsRequestBuilder(String baseUrl) {
    super(baseUrl);
  }

  /*
   * (non-Javadoc)
   * 
   * @see io.github.mainstringargs.alpaca.rest.AlpacaUrlBuilder#endpoint()
   */
  @Override
  public String getEndpoint() {
    return POSITIONS_ENDPOINT;
  }


}
