package tce.com;

import org.apache.http.message.BasicHeader;


public class TMSHeader {
    private final String contentType;
    private final String from;
    private final String to;
    private final String nadid;
    private final String tid;
    private final String vin;

    private TMSHeader(Builder builder) {
        contentType = builder.contentType;
        from = builder.from;
        to = builder.to;
        nadid = builder.nadid;
        tid = builder.tid;
        vin = builder.vin;
    }

    public static class Builder {
        private String contentType = "application/json;charset=UTF-8";
        private String from = "DLW";
        private String to = "JV";
        private String tid="";
        private final String nadid;
       
        private final String vin;

        public Builder(String nadid, String vin) {
            this.nadid = nadid;
            this.vin = vin;
        }

        public Builder contentType(String val) {
            contentType = val;
            return this;
        }

        public Builder from(String val) {
            from = val;
            return this;
        }

        public Builder to(String val) {
            to = val;
            return this;
        }
        public Builder tid(String val) {
            tid = val;
            return this;
        }
        public TMSHeader build() {
            return new TMSHeader(this);
        }

    }

    public String getContentType() {
        return contentType;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public String getNadid() {
        return nadid;
    }

    public String getTid() {
        return tid;
    }

    public String getVin() {
        return vin;
    }

    public BasicHeader[] toHTTPHeaders() {
        BasicHeader[] headers = { new BasicHeader("nadid", nadid),
                new BasicHeader("tid", tid), new BasicHeader("vin", vin),
                new BasicHeader("Content-Type", contentType),
                new BasicHeader("from", from), new BasicHeader("to", to) };
        return headers;
    }
}
