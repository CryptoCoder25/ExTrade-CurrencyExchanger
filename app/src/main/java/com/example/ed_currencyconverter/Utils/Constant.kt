package com.example.ed_currencyconverter.Utils

import com.example.ed_currencyconverter.Domain.Models.CurrencyCodes

object Constant {

    //const val BASE_URL = "https://api.exchangeratesapi.io/" - need to create account in api layer.
    const val BASE_URL = "https://api.apilayer.com/exchangerates_data/" // <--
    const val API_KEY = "Ow0SJX6pNs9O3FIxAY5IEDOVKHfTSJbf" // for demo purpose public api key

    //IMAGE
    const val IMAGE_API =  "https://countryflagsapi.com/png/"

    //ROUTES
    const val MainScreen = "MAIN_PAGE";
    const val PortfolioScreen = "PORTFOLIO_PAGE";
    const val TransactionListScreen = "SUB_PAGE";


    //NAV ITEM ID
    const val main_page = "MAIN_PAGE"
    const val portfolio_page = "PORTFOLIO_PAGE"
    const val sub_page = "SUB_PAGE"
    const val exit_page  = "EXIT";

    //COMMISSION FEE
    const val COMMISSION_FEE = 0.007 // 0.7%
    const val NO_COMMISSION_FEE = 0.0

    //DIALOG OPENTYPE
    const val EXTERNAL = "EXTERNAL_ERROR"
    const val EXTERNAL_MESSAGEBODY = "Unable to reach the server, please check your network connection!"
    const val INTERNAL = "INTERNAL_ERROR"
    const val INTERNAL_MESSAGEBODY = "Unable to process transaction, try again later."
    const val SUCCESS = "SUCCESS"
    const val ADD_FUNDS = "ADD_FUNDS"
    const val SUCCESS_ADD_FUNDS = "SUCCESS_ADD_FUNDS"
    const val SUCCESS_ADD_FUNDS_BODY = "You have successfully add funds to your account."


    //LOGS
    const val LATEST_RATE_REPOSITORY = "LATEST_RATE_REPOSITORY"
    const val CRUD_MAINPAGE_VM = "CRUD_MAINPAGE_VM"
    const val API_MAINPAGE_VM = "API_MAINPAGE_VM"
    const val CRUD_PORTFOLIO_VM = "CRUD_PORTFOLIO_VM"
    const val INIT_PORTFOLIO_VM = "INIT_PORTFOLIO_VM"

    val ADD_CURRENCY_CODES = listOf(
        CurrencyCodes("EUR","European Union", "EUR"),
        CurrencyCodes("USD","United States Dollar", "USA"),
        CurrencyCodes("PHP", "Philippine Peso", "PHL"),
        CurrencyCodes("CAD","Canadian Dollar","CAN"),
        CurrencyCodes("CNY","Chinese Yuan","CHN"),
        CurrencyCodes("AUD","Australian Dollar","AUS"),
        CurrencyCodes("INR","Indian Rupee","IND"),
        CurrencyCodes("AED","United Arab Emirates Dirham","ARE"),
        )



    val CURRENCY_CODES = listOf(
        CurrencyCodes("EUR","European Union", "EUR"),
        CurrencyCodes("USD","United States Dollar", "USA"),
        CurrencyCodes("PHP", "Philippine Peso", "PHL"),
        CurrencyCodes("AED","United Arab Emirates Dirham","ARE"),
        CurrencyCodes("AFN","Afghan Afghani","AFG"),
        CurrencyCodes("ALL","Albanian Lek","ALB"),
        CurrencyCodes("AMD","Armenian Dram","ARM"),
        CurrencyCodes("ANG","Netherlands Antillean Guilder","NL"),
        CurrencyCodes("AOA","Angolan Kwanza","AGO"),
        CurrencyCodes("ARS","Argentine Peso","ARG"),
        CurrencyCodes("AUD","Australian Dollar","AUS"),
        CurrencyCodes("AZN","Azerbaijani Manat","AZE"),
        CurrencyCodes("BAM","Bosnia-Herzegovina Convertible Mark","BIH"),
        CurrencyCodes("BBD","Barbadian Dollar","BRB"),
        CurrencyCodes("BDT","Bangladeshi Taka","BGD"),
        CurrencyCodes("BGN","Bulgarian Lev","BGL"),
        CurrencyCodes("BHD","Bahraini Dinar","BHD"),
        CurrencyCodes("BIF","Burundian Franc","BDI"),
        CurrencyCodes("BND","Brunei Dollar","BRN"),
        CurrencyCodes("BOB","Bolivian Boliviano","BOL"),
        CurrencyCodes("BRL","Brazilian Real","BRA"),
        CurrencyCodes("BSD","Bahamian Dollar","BHS"),
        CurrencyCodes("BTN","Bhutanese Ngultrum","BTN"),
        CurrencyCodes("BWP","Botswanan Pula","BWA"),
        CurrencyCodes("BYN","New Belarusian Ruble","BLR"),
        CurrencyCodes("CAD","Canadian Dollar","CAN"),
        CurrencyCodes("CDF","Congolese Franc","COD"),
        CurrencyCodes("CHF","Swiss Franc","CHE"),
        CurrencyCodes("CLP","Chilean Peso","CHL"),
        CurrencyCodes("CNY","Chinese Yuan","CHN"),
        CurrencyCodes("COP","Colombian Peso","COL"),
        CurrencyCodes("CRC","Costa Rican Col\u00f3n","CRI"),
        CurrencyCodes("CUP","Cuban Peso","CUB"),
        CurrencyCodes("CVE","Cape Verdean Escudo","CPV"),
        CurrencyCodes("DJF","Djiboutian Franc","DJI"),
        CurrencyCodes("DKK","Danish Krone","DNK"),
        CurrencyCodes("DOP","Dominican Peso","DOM"),
        CurrencyCodes("DZD","Algerian Dinar","DZA"),
        CurrencyCodes("EGP","Egyptian Pound","EGY"),
        CurrencyCodes("ERN","Eritrean Nakfa","ERI"),
        CurrencyCodes("ETB","Ethiopian Birr","ETH"),
        CurrencyCodes("FJD","Fijian Dollar","FJI"),
        CurrencyCodes("GBP","British Pound Sterling","GBR"),
        CurrencyCodes("GEL","Georgian Lari","GEO"),
        CurrencyCodes("GMD","Gambian Dalasi","GMB"),
        CurrencyCodes("GNF","Guinean Franc","GIN"),
        CurrencyCodes("GTQ","Guatemalan Quetzal","GTM"),
        CurrencyCodes("GYD","Guyanaese Dollar","GUY"),
        CurrencyCodes("HNL","Honduran Lempira","HND"),
        CurrencyCodes("HRK","Croatian Kuna","HRV"),
        CurrencyCodes("HTG","Haitian Gourde","HTI"),
        CurrencyCodes("HUF","Hungarian Forint","HUN"),
        CurrencyCodes("IDR","Indonesian Rupiah","IDN"),
        CurrencyCodes("ILS","Israeli New Sheqel","ISR"),
        CurrencyCodes("INR","Indian Rupee","IND"),
        CurrencyCodes("IQD","Iraqi Dinar","IRQ"),
        CurrencyCodes("IRR","Iranian Rial","IRN"),
        CurrencyCodes("ISK","Icelandic Kr\u00f3na","ISL"),
        CurrencyCodes("JMD","Jamaican Dollar","JAM"),
        CurrencyCodes("JOD","Jordanian Dinar","JOR"),
        CurrencyCodes("JPY","Japanese Yen","JPN"),
        CurrencyCodes("KES","Kenyan Shilling","KEN"),
        CurrencyCodes("KGS","Kyrgystani Som","KGZ"),
        CurrencyCodes("KHR","Cambodian Riel","KHM"),
        CurrencyCodes("KMF","Comorian Franc","COM"),
        CurrencyCodes("KPW","North Korean Won","PRK"),
        CurrencyCodes("KRW","South Korean Won","KOR"),
        CurrencyCodes("KWD","Kuwaiti Dinar","KWT"),
        CurrencyCodes("KZT","Kazakhstani Tenge","KAZ"),
        CurrencyCodes("LAK","Laotian Kip","LAO"),
        CurrencyCodes("LBP","Lebanese Pound","LBN"),
        CurrencyCodes("LKR","Sri Lankan Rupee","LKA"),
        CurrencyCodes("LRD","Liberian Dollar","LBR"),
        CurrencyCodes("LSL","Lesotho Loti","LSO"),
        CurrencyCodes("LYD","Libyan Dinar","LBY"),
        CurrencyCodes("MAD","Moroccan Dirham","MAR"),
        CurrencyCodes("MDL","Moldovan Leu","MDA"),
        CurrencyCodes("MGA","Malagasy Ariary","MDG"),
        CurrencyCodes("MKD","Macedonian Denar","MKD"),
        CurrencyCodes("MMK","Myanma Kyat","MMR"),
        CurrencyCodes("MNT","Mongolian Tugrik","MNG"),
        CurrencyCodes("MUR","Mauritian Rupee","MUS"),
        CurrencyCodes("MVR","Maldivian Rufiyaa","MDV"),
        CurrencyCodes("MWK","Malawian Kwacha","MWI"),
        CurrencyCodes("MXN","Mexican Peso","MEX"),
        CurrencyCodes("MYR","Malaysian Ringgit","MYS"),
        CurrencyCodes("NAD","Namibian Dollar","NAM"),
        CurrencyCodes("NGN","Nigerian Naira","NGA"),
        CurrencyCodes("NOK","Norwegian Krone","NOR"),
        CurrencyCodes("NPR","Nepalese Rupee","NPL"),
        CurrencyCodes("NZD","New Zealand Dollar","NZL"),
        CurrencyCodes("OMR","Omani Rial","OMN"),
        CurrencyCodes("PAB","Panamanian Balboa","PAN"),
        CurrencyCodes("PEN","Peruvian Nuevo Sol","PER"),
        CurrencyCodes("PGK","Papua New Guinean Kina","PNG"),
        CurrencyCodes("PKR","Pakistani Rupee","PAK"),
        CurrencyCodes("PLN","Polish Zloty","POL"),
        CurrencyCodes("PYG","Paraguayan Guarani","PRY"),
        CurrencyCodes("QAR","Qatari Rial","QAT"),
        CurrencyCodes("RON","Romanian Leu","ROU"),
        CurrencyCodes("RSD","Serbian Dinar","SRB"),
        CurrencyCodes("RUB","Russian Ruble","RUS"),
        CurrencyCodes("RWF","Rwandan Franc","RWA"),
        CurrencyCodes("SAR","Saudi Riyal","SAU"),
        CurrencyCodes("SBD","Solomon Islands Dollar","SLB"),
        CurrencyCodes("SCR","Seychellois Rupee","SYC"),
        CurrencyCodes("SDG","Sudanese Pound","SDN"),
        CurrencyCodes("SEK","Swedish Krona","SWE"),
        CurrencyCodes("SGD","Singapore Dollar","SGP"),
        CurrencyCodes("SLL","Sierra Leonean Leone","SLE"),
        CurrencyCodes("SOS","Somali Shilling","SOM"),
        CurrencyCodes("SRD","Surinamese Dollar","SUR"),
        CurrencyCodes("SYP","Syrian Pound","SYR"),
        CurrencyCodes("SZL","Swazi Lilangeni","SWZ"),
        CurrencyCodes("THB","Thai Baht","THA"),
        CurrencyCodes("TJS","Tajikistani Somoni","TJK"),
        CurrencyCodes("TMT","Turkmenistani Manat","TKM"),
        CurrencyCodes("TND","Tunisian Dinar","TUN"),
        CurrencyCodes("TOP","Tongan Pa\u02bbanga","TON"),
        CurrencyCodes("TRY","Turkish Lira","TUR"),
        CurrencyCodes("TTD","Trinidad and Tobago Dollar","TTO"),
        CurrencyCodes("TZS","Tanzanian Shilling","TZA"),
        CurrencyCodes("UAH","Ukrainian Hryvnia","UKR"),
        CurrencyCodes("UYU","Uruguayan Peso","URY"),
        CurrencyCodes("UZS","Uzbekistan Som","UZB"),
        CurrencyCodes("VEF","Venezuelan Bol\u00edvar Fuerte","VEN"),
        CurrencyCodes("VND","Vietnamese Dong","VNM"),
        CurrencyCodes("VUV","Vanuatu Vatu","VUT"),
        CurrencyCodes("WST","Samoan Tala","WSM"),
        CurrencyCodes("XAF","CFA Franc BEAC","CMR"),
        CurrencyCodes("XOF","CFA Franc BCEAO","TGO"),
        CurrencyCodes("YER","Yemeni Rial","YEM"),
        CurrencyCodes("ZAR","South African Rand","ZAF"),
        CurrencyCodes("ZMK","Zambian Kwacha","ZMB"),
        CurrencyCodes("ZWL","Zimbabwean Dollar","ZW")
        )

}