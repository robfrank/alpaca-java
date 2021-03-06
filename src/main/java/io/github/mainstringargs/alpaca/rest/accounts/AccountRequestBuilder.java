package io.github.mainstringargs.alpaca.rest.accounts;

import io.github.mainstringargs.alpaca.rest.AlpacaRequestBuilder;

/**
 * The Class AccountRequestBuilder.
 */
public abstract class AccountRequestBuilder extends AlpacaRequestBuilder {


  /** The Constant ACCOUNT_ENDPOINT. */
  public final static String ACCOUNT_ENDPOINT = "account";

  /**
   * Instantiates a new account request builder.
   *
   * @param baseUrl the base url
   */
  public AccountRequestBuilder(String baseUrl) {
    super(baseUrl);
  }

  /*
   * (non-Javadoc)
   * 
   * @see io.github.mainstringargs.alpaca.rest.AlpacaUrlBuilder#endpoint()
   */
  @Override
  public String getEndpoint() {
    return ACCOUNT_ENDPOINT;
  }

}
