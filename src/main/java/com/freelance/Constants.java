package com.freelance;

public interface Constants {
    /* Buyer APIs */
    String BUYER_API = "/api/buyer";
    String CREATE_BUYER_API = "/createbuyer";
    String GET_BUYER_API = "/getbuyer";
    String GET_BUYER_FROM_EMAIL_API = "/getbuyerbyemail";
    String GET_BUYERS_API = "/getbuyers";
    String UPDATE_BUYER_API = "/updatebuyer";
    String DELETE_BUYER_API = "/deletebuyer";

    /* Seller APIs */
    String SELLER_API = "/api/seller";
    String CREATE_SELLER_API = "/createseller";
    String GET_SELLER_API = "/getseller";
    String GET_SELLERS_API = "/getsellers";
    String UPDATE_SELLER_API = "/updateseller";
    String DELETE_SELLER_API = "/deleteseller";

    /* Project APIs */
    String PROJECT_API = "/api/project";
    String CREATE_PROJECT_API = "/createproject";
    String GET_PROJECT_API = "/getproject";
    String GET_PROJECTS_BY_SELLER_API = "/getprojectsbyseller";
    String RECENT_PROJECTS_API = "/recentprojects";
    String UPDATE_PROJECTS_API = "/updateproject";
    String DELETE_PROJECT_API = "/deleteproject";

    /* Bid APIs */
    String BID_API = "/api/bid";
    String CREATE_BID_API = "/createbid";
    String GET_BY_BID_ID_API = "/getbybid";
    String GET_BID_BY_BUYER_AND_PROJECT_API = "/getbidbybuyerandproject";
    String GET_BIDS_BY_PROJECT_API = "/getbidsbyproject";
    String GET_BIDS_BY_BUYER_API = "/getbybuyer";
    String UPDATE_BID_API = "/updatebid";
    String DELETE_BID_API = "/deletebid";
}
