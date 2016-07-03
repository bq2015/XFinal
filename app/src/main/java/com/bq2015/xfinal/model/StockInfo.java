package com.bq2015.xfinal.model;

import java.util.List;

/**
 * 股票信息
 * Created by Kylin on 2016/7/3.
 */
public class StockInfo {

    /**
     * stockinfo : [{"name":"","code":"hs002230","date":null,"time":null,"OpenningPrice":"0","closingPrice":"0","currentPrice":"0","hPrice":"0","lPrice":"0","competitivePrice":"0","auctionPrice":"0","totalNumber":"0","turnover":"0","buyOne":"0","buyOnePrice":"0","buyTwo":"0","buyTwoPrice":"0","buyThree":"0","buyThreePrice":"0","buyFour":"0","buyFourPrice":"0","buyFive":"0","buyFivePrice":"0","sellOne":"0","sellOnePrice":"0","sellTwo":"0","sellTwoPrice":"0","sellThree":"0","sellThreePrice":"0","sellFour":"0","sellFourPrice":"0","sellFive":"0","sellFivePrice":"0","minurl":"http://image.sinajs.cn/newchart/min/n/hs002230.gif","dayurl":"http://image.sinajs.cn/newchart/daily/n/hs002230.gif","weekurl":"http://image.sinajs.cn/newchart/weekly/n/hs002230.gif","monthurl":"http://image.sinajs.cn/newchart/monthly/n/hs002230.gif"}]
     * market : {"shanghai":{"name":"上证指数","date":null,"curdot":"3399.9111","curprice":"-139.2708","rate":"-3.94","growth":null,"startdot":null,"closedot":null,"hdot":null,"ldot":null,"dealnumber":"1458426","turnover":"19513250"},"shenzhen":{"name":"深证成指","date":null,"curdot":"12016.93","curprice":"-647.96","rate":"-5.12","growth":null,"startdot":null,"closedot":null,"hdot":null,"ldot":null,"dealnumber":"166715597","turnover":"29516110"},"DJI":{"name":"道琼斯","date":"2016-01-01 07:30:08","curdot":"17425.03","curprice":null,"rate":"-1.02","growth":"-178.84","startdot":"17590.66","closedot":"17603.87","hdot":"17590.66","ldot":"17421.16","dealnumber":null,"turnover":"0"},"IXIC":{"name":"纳斯达克","date":"2016-01-01 06:40:02","curdot":"5007.41","curprice":null,"rate":"-1.15","growth":"-58.43","startdot":"5047.04","closedot":"5065.85","hdot":"5058.06","ldot":"5007.01","dealnumber":null,"turnover":"1430485917"},"HSI":{"name":"恒生指数","date":"2016/01/04 11:45","curdot":"21417.55","curprice":null,"rate":"-2.27","growth":"-496.85","startdot":"21782.62","closedot":"21914.4","hdot":"21794.84","ldot":"21362.76","dealnumber":null,"turnover":"35088454"}}
     * klinegraph : null
     */

    private ResultBean result;
    /**
     * result : {"stockinfo":[{"name":"","code":"hs002230","date":null,"time":null,"OpenningPrice":"0","closingPrice":"0","currentPrice":"0","hPrice":"0","lPrice":"0","competitivePrice":"0","auctionPrice":"0","totalNumber":"0","turnover":"0","buyOne":"0","buyOnePrice":"0","buyTwo":"0","buyTwoPrice":"0","buyThree":"0","buyThreePrice":"0","buyFour":"0","buyFourPrice":"0","buyFive":"0","buyFivePrice":"0","sellOne":"0","sellOnePrice":"0","sellTwo":"0","sellTwoPrice":"0","sellThree":"0","sellThreePrice":"0","sellFour":"0","sellFourPrice":"0","sellFive":"0","sellFivePrice":"0","minurl":"http://image.sinajs.cn/newchart/min/n/hs002230.gif","dayurl":"http://image.sinajs.cn/newchart/daily/n/hs002230.gif","weekurl":"http://image.sinajs.cn/newchart/weekly/n/hs002230.gif","monthurl":"http://image.sinajs.cn/newchart/monthly/n/hs002230.gif"}],"market":{"shanghai":{"name":"上证指数","date":null,"curdot":"3399.9111","curprice":"-139.2708","rate":"-3.94","growth":null,"startdot":null,"closedot":null,"hdot":null,"ldot":null,"dealnumber":"1458426","turnover":"19513250"},"shenzhen":{"name":"深证成指","date":null,"curdot":"12016.93","curprice":"-647.96","rate":"-5.12","growth":null,"startdot":null,"closedot":null,"hdot":null,"ldot":null,"dealnumber":"166715597","turnover":"29516110"},"DJI":{"name":"道琼斯","date":"2016-01-01 07:30:08","curdot":"17425.03","curprice":null,"rate":"-1.02","growth":"-178.84","startdot":"17590.66","closedot":"17603.87","hdot":"17590.66","ldot":"17421.16","dealnumber":null,"turnover":"0"},"IXIC":{"name":"纳斯达克","date":"2016-01-01 06:40:02","curdot":"5007.41","curprice":null,"rate":"-1.15","growth":"-58.43","startdot":"5047.04","closedot":"5065.85","hdot":"5058.06","ldot":"5007.01","dealnumber":null,"turnover":"1430485917"},"HSI":{"name":"恒生指数","date":"2016/01/04 11:45","curdot":"21417.55","curprice":null,"rate":"-2.27","growth":"-496.85","startdot":"21782.62","closedot":"21914.4","hdot":"21794.84","ldot":"21362.76","dealnumber":null,"turnover":"35088454"}},"klinegraph":null}
     * error_code : 0
     * reason : Succes
     */

    private int error_code;
    private String reason;

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public static class ResultBean {
        /**
         * shanghai : {"name":"上证指数","date":null,"curdot":"3399.9111","curprice":"-139.2708","rate":"-3.94","growth":null,"startdot":null,"closedot":null,"hdot":null,"ldot":null,"dealnumber":"1458426","turnover":"19513250"}
         * shenzhen : {"name":"深证成指","date":null,"curdot":"12016.93","curprice":"-647.96","rate":"-5.12","growth":null,"startdot":null,"closedot":null,"hdot":null,"ldot":null,"dealnumber":"166715597","turnover":"29516110"}
         * DJI : {"name":"道琼斯","date":"2016-01-01 07:30:08","curdot":"17425.03","curprice":null,"rate":"-1.02","growth":"-178.84","startdot":"17590.66","closedot":"17603.87","hdot":"17590.66","ldot":"17421.16","dealnumber":null,"turnover":"0"}
         * IXIC : {"name":"纳斯达克","date":"2016-01-01 06:40:02","curdot":"5007.41","curprice":null,"rate":"-1.15","growth":"-58.43","startdot":"5047.04","closedot":"5065.85","hdot":"5058.06","ldot":"5007.01","dealnumber":null,"turnover":"1430485917"}
         * HSI : {"name":"恒生指数","date":"2016/01/04 11:45","curdot":"21417.55","curprice":null,"rate":"-2.27","growth":"-496.85","startdot":"21782.62","closedot":"21914.4","hdot":"21794.84","ldot":"21362.76","dealnumber":null,"turnover":"35088454"}
         */

        private MarketBean market;
        private Object klinegraph;
        /**
         * name :
         * code : hs002230
         * date : null
         * time : null
         * OpenningPrice : 0
         * closingPrice : 0
         * currentPrice : 0
         * hPrice : 0
         * lPrice : 0
         * competitivePrice : 0
         * auctionPrice : 0
         * totalNumber : 0
         * turnover : 0
         * buyOne : 0
         * buyOnePrice : 0
         * buyTwo : 0
         * buyTwoPrice : 0
         * buyThree : 0
         * buyThreePrice : 0
         * buyFour : 0
         * buyFourPrice : 0
         * buyFive : 0
         * buyFivePrice : 0
         * sellOne : 0
         * sellOnePrice : 0
         * sellTwo : 0
         * sellTwoPrice : 0
         * sellThree : 0
         * sellThreePrice : 0
         * sellFour : 0
         * sellFourPrice : 0
         * sellFive : 0
         * sellFivePrice : 0
         * minurl : http://image.sinajs.cn/newchart/min/n/hs002230.gif
         * dayurl : http://image.sinajs.cn/newchart/daily/n/hs002230.gif
         * weekurl : http://image.sinajs.cn/newchart/weekly/n/hs002230.gif
         * monthurl : http://image.sinajs.cn/newchart/monthly/n/hs002230.gif
         */

        private List<StockinfoBean> stockinfo;

        public MarketBean getMarket() {
            return market;
        }

        public void setMarket(MarketBean market) {
            this.market = market;
        }

        public Object getKlinegraph() {
            return klinegraph;
        }

        public void setKlinegraph(Object klinegraph) {
            this.klinegraph = klinegraph;
        }

        public List<StockinfoBean> getStockinfo() {
            return stockinfo;
        }

        public void setStockinfo(List<StockinfoBean> stockinfo) {
            this.stockinfo = stockinfo;
        }

        public static class MarketBean {
            /**
             * name : 上证指数
             * date : null
             * curdot : 3399.9111
             * curprice : -139.2708
             * rate : -3.94
             * growth : null
             * startdot : null
             * closedot : null
             * hdot : null
             * ldot : null
             * dealnumber : 1458426
             * turnover : 19513250
             */

            private ShanghaiBean shanghai;
            /**
             * name : 深证成指
             * date : null
             * curdot : 12016.93
             * curprice : -647.96
             * rate : -5.12
             * growth : null
             * startdot : null
             * closedot : null
             * hdot : null
             * ldot : null
             * dealnumber : 166715597
             * turnover : 29516110
             */

            private ShenzhenBean shenzhen;
            /**
             * name : 道琼斯
             * date : 2016-01-01 07:30:08
             * curdot : 17425.03
             * curprice : null
             * rate : -1.02
             * growth : -178.84
             * startdot : 17590.66
             * closedot : 17603.87
             * hdot : 17590.66
             * ldot : 17421.16
             * dealnumber : null
             * turnover : 0
             */

            private DJIBean DJI;
            /**
             * name : 纳斯达克
             * date : 2016-01-01 06:40:02
             * curdot : 5007.41
             * curprice : null
             * rate : -1.15
             * growth : -58.43
             * startdot : 5047.04
             * closedot : 5065.85
             * hdot : 5058.06
             * ldot : 5007.01
             * dealnumber : null
             * turnover : 1430485917
             */

            private IXICBean IXIC;
            /**
             * name : 恒生指数
             * date : 2016/01/04 11:45
             * curdot : 21417.55
             * curprice : null
             * rate : -2.27
             * growth : -496.85
             * startdot : 21782.62
             * closedot : 21914.4
             * hdot : 21794.84
             * ldot : 21362.76
             * dealnumber : null
             * turnover : 35088454
             */

            private HSIBean HSI;

            public ShanghaiBean getShanghai() {
                return shanghai;
            }

            public void setShanghai(ShanghaiBean shanghai) {
                this.shanghai = shanghai;
            }

            public ShenzhenBean getShenzhen() {
                return shenzhen;
            }

            public void setShenzhen(ShenzhenBean shenzhen) {
                this.shenzhen = shenzhen;
            }

            public DJIBean getDJI() {
                return DJI;
            }

            public void setDJI(DJIBean DJI) {
                this.DJI = DJI;
            }

            public IXICBean getIXIC() {
                return IXIC;
            }

            public void setIXIC(IXICBean IXIC) {
                this.IXIC = IXIC;
            }

            public HSIBean getHSI() {
                return HSI;
            }

            public void setHSI(HSIBean HSI) {
                this.HSI = HSI;
            }

            public static class ShanghaiBean {
                private String name;
                private Object date;
                private String curdot;
                private String curprice;
                private String rate;
                private Object growth;
                private Object startdot;
                private Object closedot;
                private Object hdot;
                private Object ldot;
                private String dealnumber;
                private String turnover;

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public Object getDate() {
                    return date;
                }

                public void setDate(Object date) {
                    this.date = date;
                }

                public String getCurdot() {
                    return curdot;
                }

                public void setCurdot(String curdot) {
                    this.curdot = curdot;
                }

                public String getCurprice() {
                    return curprice;
                }

                public void setCurprice(String curprice) {
                    this.curprice = curprice;
                }

                public String getRate() {
                    return rate;
                }

                public void setRate(String rate) {
                    this.rate = rate;
                }

                public Object getGrowth() {
                    return growth;
                }

                public void setGrowth(Object growth) {
                    this.growth = growth;
                }

                public Object getStartdot() {
                    return startdot;
                }

                public void setStartdot(Object startdot) {
                    this.startdot = startdot;
                }

                public Object getClosedot() {
                    return closedot;
                }

                public void setClosedot(Object closedot) {
                    this.closedot = closedot;
                }

                public Object getHdot() {
                    return hdot;
                }

                public void setHdot(Object hdot) {
                    this.hdot = hdot;
                }

                public Object getLdot() {
                    return ldot;
                }

                public void setLdot(Object ldot) {
                    this.ldot = ldot;
                }

                public String getDealnumber() {
                    return dealnumber;
                }

                public void setDealnumber(String dealnumber) {
                    this.dealnumber = dealnumber;
                }

                public String getTurnover() {
                    return turnover;
                }

                public void setTurnover(String turnover) {
                    this.turnover = turnover;
                }
            }

            public static class ShenzhenBean {
                private String name;
                private Object date;
                private String curdot;
                private String curprice;
                private String rate;
                private Object growth;
                private Object startdot;
                private Object closedot;
                private Object hdot;
                private Object ldot;
                private String dealnumber;
                private String turnover;

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public Object getDate() {
                    return date;
                }

                public void setDate(Object date) {
                    this.date = date;
                }

                public String getCurdot() {
                    return curdot;
                }

                public void setCurdot(String curdot) {
                    this.curdot = curdot;
                }

                public String getCurprice() {
                    return curprice;
                }

                public void setCurprice(String curprice) {
                    this.curprice = curprice;
                }

                public String getRate() {
                    return rate;
                }

                public void setRate(String rate) {
                    this.rate = rate;
                }

                public Object getGrowth() {
                    return growth;
                }

                public void setGrowth(Object growth) {
                    this.growth = growth;
                }

                public Object getStartdot() {
                    return startdot;
                }

                public void setStartdot(Object startdot) {
                    this.startdot = startdot;
                }

                public Object getClosedot() {
                    return closedot;
                }

                public void setClosedot(Object closedot) {
                    this.closedot = closedot;
                }

                public Object getHdot() {
                    return hdot;
                }

                public void setHdot(Object hdot) {
                    this.hdot = hdot;
                }

                public Object getLdot() {
                    return ldot;
                }

                public void setLdot(Object ldot) {
                    this.ldot = ldot;
                }

                public String getDealnumber() {
                    return dealnumber;
                }

                public void setDealnumber(String dealnumber) {
                    this.dealnumber = dealnumber;
                }

                public String getTurnover() {
                    return turnover;
                }

                public void setTurnover(String turnover) {
                    this.turnover = turnover;
                }
            }

            public static class DJIBean {
                private String name;
                private String date;
                private String curdot;
                private Object curprice;
                private String rate;
                private String growth;
                private String startdot;
                private String closedot;
                private String hdot;
                private String ldot;
                private Object dealnumber;
                private String turnover;

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getDate() {
                    return date;
                }

                public void setDate(String date) {
                    this.date = date;
                }

                public String getCurdot() {
                    return curdot;
                }

                public void setCurdot(String curdot) {
                    this.curdot = curdot;
                }

                public Object getCurprice() {
                    return curprice;
                }

                public void setCurprice(Object curprice) {
                    this.curprice = curprice;
                }

                public String getRate() {
                    return rate;
                }

                public void setRate(String rate) {
                    this.rate = rate;
                }

                public String getGrowth() {
                    return growth;
                }

                public void setGrowth(String growth) {
                    this.growth = growth;
                }

                public String getStartdot() {
                    return startdot;
                }

                public void setStartdot(String startdot) {
                    this.startdot = startdot;
                }

                public String getClosedot() {
                    return closedot;
                }

                public void setClosedot(String closedot) {
                    this.closedot = closedot;
                }

                public String getHdot() {
                    return hdot;
                }

                public void setHdot(String hdot) {
                    this.hdot = hdot;
                }

                public String getLdot() {
                    return ldot;
                }

                public void setLdot(String ldot) {
                    this.ldot = ldot;
                }

                public Object getDealnumber() {
                    return dealnumber;
                }

                public void setDealnumber(Object dealnumber) {
                    this.dealnumber = dealnumber;
                }

                public String getTurnover() {
                    return turnover;
                }

                public void setTurnover(String turnover) {
                    this.turnover = turnover;
                }
            }

            public static class IXICBean {
                private String name;
                private String date;
                private String curdot;
                private Object curprice;
                private String rate;
                private String growth;
                private String startdot;
                private String closedot;
                private String hdot;
                private String ldot;
                private Object dealnumber;
                private String turnover;

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getDate() {
                    return date;
                }

                public void setDate(String date) {
                    this.date = date;
                }

                public String getCurdot() {
                    return curdot;
                }

                public void setCurdot(String curdot) {
                    this.curdot = curdot;
                }

                public Object getCurprice() {
                    return curprice;
                }

                public void setCurprice(Object curprice) {
                    this.curprice = curprice;
                }

                public String getRate() {
                    return rate;
                }

                public void setRate(String rate) {
                    this.rate = rate;
                }

                public String getGrowth() {
                    return growth;
                }

                public void setGrowth(String growth) {
                    this.growth = growth;
                }

                public String getStartdot() {
                    return startdot;
                }

                public void setStartdot(String startdot) {
                    this.startdot = startdot;
                }

                public String getClosedot() {
                    return closedot;
                }

                public void setClosedot(String closedot) {
                    this.closedot = closedot;
                }

                public String getHdot() {
                    return hdot;
                }

                public void setHdot(String hdot) {
                    this.hdot = hdot;
                }

                public String getLdot() {
                    return ldot;
                }

                public void setLdot(String ldot) {
                    this.ldot = ldot;
                }

                public Object getDealnumber() {
                    return dealnumber;
                }

                public void setDealnumber(Object dealnumber) {
                    this.dealnumber = dealnumber;
                }

                public String getTurnover() {
                    return turnover;
                }

                public void setTurnover(String turnover) {
                    this.turnover = turnover;
                }
            }

            public static class HSIBean {
                private String name;
                private String date;
                private String curdot;
                private Object curprice;
                private String rate;
                private String growth;
                private String startdot;
                private String closedot;
                private String hdot;
                private String ldot;
                private Object dealnumber;
                private String turnover;

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getDate() {
                    return date;
                }

                public void setDate(String date) {
                    this.date = date;
                }

                public String getCurdot() {
                    return curdot;
                }

                public void setCurdot(String curdot) {
                    this.curdot = curdot;
                }

                public Object getCurprice() {
                    return curprice;
                }

                public void setCurprice(Object curprice) {
                    this.curprice = curprice;
                }

                public String getRate() {
                    return rate;
                }

                public void setRate(String rate) {
                    this.rate = rate;
                }

                public String getGrowth() {
                    return growth;
                }

                public void setGrowth(String growth) {
                    this.growth = growth;
                }

                public String getStartdot() {
                    return startdot;
                }

                public void setStartdot(String startdot) {
                    this.startdot = startdot;
                }

                public String getClosedot() {
                    return closedot;
                }

                public void setClosedot(String closedot) {
                    this.closedot = closedot;
                }

                public String getHdot() {
                    return hdot;
                }

                public void setHdot(String hdot) {
                    this.hdot = hdot;
                }

                public String getLdot() {
                    return ldot;
                }

                public void setLdot(String ldot) {
                    this.ldot = ldot;
                }

                public Object getDealnumber() {
                    return dealnumber;
                }

                public void setDealnumber(Object dealnumber) {
                    this.dealnumber = dealnumber;
                }

                public String getTurnover() {
                    return turnover;
                }

                public void setTurnover(String turnover) {
                    this.turnover = turnover;
                }
            }
        }

        public static class StockinfoBean {
            private String name;
            private String code;
            private Object date;
            private Object time;
            private String OpenningPrice;
            private String closingPrice;
            private String currentPrice;
            private String hPrice;
            private String lPrice;
            private String competitivePrice;
            private String auctionPrice;
            private String totalNumber;
            private String turnover;
            private String buyOne;
            private String buyOnePrice;
            private String buyTwo;
            private String buyTwoPrice;
            private String buyThree;
            private String buyThreePrice;
            private String buyFour;
            private String buyFourPrice;
            private String buyFive;
            private String buyFivePrice;
            private String sellOne;
            private String sellOnePrice;
            private String sellTwo;
            private String sellTwoPrice;
            private String sellThree;
            private String sellThreePrice;
            private String sellFour;
            private String sellFourPrice;
            private String sellFive;
            private String sellFivePrice;
            private String minurl;
            private String dayurl;
            private String weekurl;
            private String monthurl;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }

            public Object getDate() {
                return date;
            }

            public void setDate(Object date) {
                this.date = date;
            }

            public Object getTime() {
                return time;
            }

            public void setTime(Object time) {
                this.time = time;
            }

            public String getOpenningPrice() {
                return OpenningPrice;
            }

            public void setOpenningPrice(String OpenningPrice) {
                this.OpenningPrice = OpenningPrice;
            }

            public String getClosingPrice() {
                return closingPrice;
            }

            public void setClosingPrice(String closingPrice) {
                this.closingPrice = closingPrice;
            }

            public String getCurrentPrice() {
                return currentPrice;
            }

            public void setCurrentPrice(String currentPrice) {
                this.currentPrice = currentPrice;
            }

            public String getHPrice() {
                return hPrice;
            }

            public void setHPrice(String hPrice) {
                this.hPrice = hPrice;
            }

            public String getLPrice() {
                return lPrice;
            }

            public void setLPrice(String lPrice) {
                this.lPrice = lPrice;
            }

            public String getCompetitivePrice() {
                return competitivePrice;
            }

            public void setCompetitivePrice(String competitivePrice) {
                this.competitivePrice = competitivePrice;
            }

            public String getAuctionPrice() {
                return auctionPrice;
            }

            public void setAuctionPrice(String auctionPrice) {
                this.auctionPrice = auctionPrice;
            }

            public String getTotalNumber() {
                return totalNumber;
            }

            public void setTotalNumber(String totalNumber) {
                this.totalNumber = totalNumber;
            }

            public String getTurnover() {
                return turnover;
            }

            public void setTurnover(String turnover) {
                this.turnover = turnover;
            }

            public String getBuyOne() {
                return buyOne;
            }

            public void setBuyOne(String buyOne) {
                this.buyOne = buyOne;
            }

            public String getBuyOnePrice() {
                return buyOnePrice;
            }

            public void setBuyOnePrice(String buyOnePrice) {
                this.buyOnePrice = buyOnePrice;
            }

            public String getBuyTwo() {
                return buyTwo;
            }

            public void setBuyTwo(String buyTwo) {
                this.buyTwo = buyTwo;
            }

            public String getBuyTwoPrice() {
                return buyTwoPrice;
            }

            public void setBuyTwoPrice(String buyTwoPrice) {
                this.buyTwoPrice = buyTwoPrice;
            }

            public String getBuyThree() {
                return buyThree;
            }

            public void setBuyThree(String buyThree) {
                this.buyThree = buyThree;
            }

            public String getBuyThreePrice() {
                return buyThreePrice;
            }

            public void setBuyThreePrice(String buyThreePrice) {
                this.buyThreePrice = buyThreePrice;
            }

            public String getBuyFour() {
                return buyFour;
            }

            public void setBuyFour(String buyFour) {
                this.buyFour = buyFour;
            }

            public String getBuyFourPrice() {
                return buyFourPrice;
            }

            public void setBuyFourPrice(String buyFourPrice) {
                this.buyFourPrice = buyFourPrice;
            }

            public String getBuyFive() {
                return buyFive;
            }

            public void setBuyFive(String buyFive) {
                this.buyFive = buyFive;
            }

            public String getBuyFivePrice() {
                return buyFivePrice;
            }

            public void setBuyFivePrice(String buyFivePrice) {
                this.buyFivePrice = buyFivePrice;
            }

            public String getSellOne() {
                return sellOne;
            }

            public void setSellOne(String sellOne) {
                this.sellOne = sellOne;
            }

            public String getSellOnePrice() {
                return sellOnePrice;
            }

            public void setSellOnePrice(String sellOnePrice) {
                this.sellOnePrice = sellOnePrice;
            }

            public String getSellTwo() {
                return sellTwo;
            }

            public void setSellTwo(String sellTwo) {
                this.sellTwo = sellTwo;
            }

            public String getSellTwoPrice() {
                return sellTwoPrice;
            }

            public void setSellTwoPrice(String sellTwoPrice) {
                this.sellTwoPrice = sellTwoPrice;
            }

            public String getSellThree() {
                return sellThree;
            }

            public void setSellThree(String sellThree) {
                this.sellThree = sellThree;
            }

            public String getSellThreePrice() {
                return sellThreePrice;
            }

            public void setSellThreePrice(String sellThreePrice) {
                this.sellThreePrice = sellThreePrice;
            }

            public String getSellFour() {
                return sellFour;
            }

            public void setSellFour(String sellFour) {
                this.sellFour = sellFour;
            }

            public String getSellFourPrice() {
                return sellFourPrice;
            }

            public void setSellFourPrice(String sellFourPrice) {
                this.sellFourPrice = sellFourPrice;
            }

            public String getSellFive() {
                return sellFive;
            }

            public void setSellFive(String sellFive) {
                this.sellFive = sellFive;
            }

            public String getSellFivePrice() {
                return sellFivePrice;
            }

            public void setSellFivePrice(String sellFivePrice) {
                this.sellFivePrice = sellFivePrice;
            }

            public String getMinurl() {
                return minurl;
            }

            public void setMinurl(String minurl) {
                this.minurl = minurl;
            }

            public String getDayurl() {
                return dayurl;
            }

            public void setDayurl(String dayurl) {
                this.dayurl = dayurl;
            }

            public String getWeekurl() {
                return weekurl;
            }

            public void setWeekurl(String weekurl) {
                this.weekurl = weekurl;
            }

            public String getMonthurl() {
                return monthurl;
            }

            public void setMonthurl(String monthurl) {
                this.monthurl = monthurl;
            }
        }
    }
}
