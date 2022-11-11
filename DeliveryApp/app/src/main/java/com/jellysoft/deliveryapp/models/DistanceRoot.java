package com.jellysoft.deliveryapp.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DistanceRoot {

    @SerializedName("destination_addresses")
    private List<String> destinationAddresses;

    @SerializedName("rows")
    private List<RowsItem> rows;

    @SerializedName("origin_addresses")
    private List<String> originAddresses;

    @SerializedName("status")
    private String status;

    public List<String> getDestinationAddresses() {
        return destinationAddresses;
    }

    public List<RowsItem> getRows() {
        return rows;
    }

    public List<String> getOriginAddresses() {
        return originAddresses;
    }

    public String getStatus() {
        return status;
    }

    public static class RowsItem {

        @SerializedName("elements")
        private List<ElementsItem> elements;

        public List<ElementsItem> getElements() {
            return elements;
        }
    }

    public static class ElementsItem {

        @SerializedName("duration")
        private Duration duration;

        @SerializedName("distance")
        private Distance distance;

        @SerializedName("status")
        private String status;

        public Duration getDuration() {
            return duration;
        }

        public Distance getDistance() {
            return distance;
        }

        public String getStatus() {
            return status;
        }
    }

    public static class Duration {

        @SerializedName("text")
        private String text;

        @SerializedName("value")
        private int value;

        public String getText() {
            return text;
        }

        public int getValue() {
            return value;
        }
    }

    public static class Distance {

        @SerializedName("text")
        private String text;

        @SerializedName("value")
        private int value;

        public String getText() {
            return text;
        }

        public int getValue() {
            return value;
        }
    }
}