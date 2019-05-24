package edu.ucsb.munchease;

// Static class - contains example JSON strings to parse into RestaurantSchedule objects
public class SampleJsonRestaurantSchedules {
    // Enforce static class
    private SampleJsonRestaurantSchedules() { }

    public static String incomplete = "{\n" +
            "  \"hours\": [\n" +
            "    {\n" +
            "      \"open\": [\n" +
            "        {\n" +
           // missing is_overnight
            "          \"start\": \"1530\",\n" +
            "          \"end\": \"2200\",\n" +
            "          \"day\": 0\n" +
            "        }\n" +
            "      ],\n" +
            "      \"hours_type\": \"REGULAR\",\n" +
            "      \"is_open_now\": false\n" +
            "    }\n" +
            "  ]\n" +
            "}";

    public static String ascending = "{\n" +
            "  \"hours\": [\n" +
            "    {\n" +
            "      \"open\": [\n" +
            "        {\n" +
            "          \"is_overnight\": false,\n" +
            "          \"start\": \"1530\",\n" +
            "          \"end\": \"1700\",\n" +
            "          \"day\": 0\n" +
            "        },\n" +
            "        {\n" +
            "          \"is_overnight\": false,\n" +
            "          \"start\": \"1630\",\n" +
            "          \"end\": \"1800\",\n" +
            "          \"day\": 1\n" +
            "        },\n" +
            "        {\n" +
            "          \"is_overnight\": false,\n" +
            "          \"start\": \"1730\",\n" +
            "          \"end\": \"1900\",\n" +
            "          \"day\": 2\n" +
            "        },\n" +
            "        {\n" +
            "          \"is_overnight\": false,\n" +
            "          \"start\": \"1830\",\n" +
            "          \"end\": \"2000\",\n" +
            "          \"day\": 3\n" +
            "        },\n" +
            "        {\n" +
            "          \"is_overnight\": false,\n" +
            "          \"start\": \"1930\",\n" +
            "          \"end\": \"2100\",\n" +
            "          \"day\": 4\n" +
            "        },\n" +
            "        {\n" +
            "          \"is_overnight\": false,\n" +
            "          \"start\": \"2030\",\n" +
            "          \"end\": \"2200\",\n" +
            "          \"day\": 5\n" +
            "        },\n" +
            "        {\n" +
            "          \"is_overnight\": false,\n" +
            "          \"start\": \"1430\",\n" +
            "          \"end\": \"2300\",\n" +
            "          \"day\": 6\n" +
            "        }\n" +
            "      ],\n" +
            "      \"hours_type\": \"REGULAR\",\n" +
            "      \"is_open_now\": false\n" +
            "    }\n" +
            "  ]\n" +
            "}";

    public static String consistent = "{\n" +
            "  \"hours\": [\n" +
            "    {\n" +
            "      \"open\": [\n" +
            "        {\n" +
            "          \"is_overnight\": false,\n" +
            "          \"start\": \"1730\",\n" +
            "          \"end\": \"2200\",\n" +
            "          \"day\": 0\n" +
            "        },\n" +
            "        {\n" +
            "          \"is_overnight\": false,\n" +
            "          \"start\": \"1730\",\n" +
            "          \"end\": \"2200\",\n" +
            "          \"day\": 1\n" +
            "        },\n" +
            "        {\n" +
            "          \"is_overnight\": false,\n" +
            "          \"start\": \"1730\",\n" +
            "          \"end\": \"2200\",\n" +
            "          \"day\": 2\n" +
            "        },\n" +
            "        {\n" +
            "          \"is_overnight\": false,\n" +
            "          \"start\": \"1730\",\n" +
            "          \"end\": \"2200\",\n" +
            "          \"day\": 3\n" +
            "        },\n" +
            "        {\n" +
            "          \"is_overnight\": false,\n" +
            "          \"start\": \"1730\",\n" +
            "          \"end\": \"2200\",\n" +
            "          \"day\": 4\n" +
            "        },\n" +
            "        {\n" +
            "          \"is_overnight\": false,\n" +
            "          \"start\": \"1730\",\n" +
            "          \"end\": \"2200\",\n" +
            "          \"day\": 5\n" +
            "        },\n" +
            "        {\n" +
            "          \"is_overnight\": false,\n" +
            "          \"start\": \"1730\",\n" +
            "          \"end\": \"2200\",\n" +
            "          \"day\": 6\n" +
            "        }\n" +
            "      ],\n" +
            "      \"hours_type\": \"REGULAR\",\n" +
            "      \"is_open_now\": false\n" +
            "    }\n" +
            "  ]\n" +
            "}";

    public static String consistent_overnight = "{\n" +
            "  \"hours\": [\n" +
            "    {\n" +
            "      \"open\": [\n" +
            "        {\n" +
            "          \"is_overnight\": true,\n" +
            "          \"start\": \"2000\",\n" +
            "          \"end\": \"0200\",\n" +
            "          \"day\": 0\n" +
            "        },\n" +
            "        {\n" +
            "          \"is_overnight\": true,\n" +
            "          \"start\": \"2200\",\n" +
            "          \"end\": \"0200\",\n" +
            "          \"day\": 1\n" +
            "        },\n" +
            "        {\n" +
            "          \"is_overnight\": true,\n" +
            "          \"start\": \"2200\",\n" +
            "          \"end\": \"0200\",\n" +
            "          \"day\": 2\n" +
            "        },\n" +
            "        {\n" +
            "          \"is_overnight\": true,\n" +
            "          \"start\": \"2200\",\n" +
            "          \"end\": \"0200\",\n" +
            "          \"day\": 3\n" +
            "        },\n" +
            "        {\n" +
            "          \"is_overnight\": true,\n" +
            "          \"start\": \"2200\",\n" +
            "          \"end\": \"0200\",\n" +
            "          \"day\": 4\n" +
            "        },\n" +
            "        {\n" +
            "          \"is_overnight\": true,\n" +
            "          \"start\": \"2200\",\n" +
            "          \"end\": \"0200\",\n" +
            "          \"day\": 5\n" +
            "        },\n" +
            "        {\n" +
            "          \"is_overnight\": true,\n" +
            "          \"start\": \"2200\",\n" +
            "          \"end\": \"0200\",\n" +
            "          \"day\": 6\n" +
            "        }\n" +
            "      ],\n" +
            "      \"hours_type\": \"REGULAR\",\n" +
            "      \"is_open_now\": false\n" +
            "    }\n" +
            "  ]\n" +
            "}";

    public static String multipleSlotsInDay = "{\n" +
            "  \"hours\": [\n" +
            "    {\n" +
            "      \"open\": [\n" +
            "        {\n" +
            "          \"is_overnight\": false,\n" +
            "          \"start\": \"1200\",\n" +
            "          \"end\": \"1500\",\n" +
            "          \"day\": 0\n" +
            "        },\n" +
            "        {\n" +
            "          \"is_overnight\": false,\n" +
            "          \"start\": \"1600\",\n" +
            "          \"end\": \"1900\",\n" +
            "          \"day\": 0\n" +
            "        },\n" +
            "        {\n" +
            "          \"is_overnight\": false,\n" +
            "          \"start\": \"1200\",\n" +
            "          \"end\": \"1500\",\n" +
            "          \"day\": 1\n" +
            "        },\n" +
            "        {\n" +
            "          \"is_overnight\": false,\n" +
            "          \"start\": \"1600\",\n" +
            "          \"end\": \"1900\",\n" +
            "          \"day\": 1\n" +
            "        },\n" +
            "        {\n" +
            "          \"is_overnight\": false,\n" +
            "          \"start\": \"1200\",\n" +
            "          \"end\": \"1500\",\n" +
            "          \"day\": 2\n" +
            "        },\n" +
            "        {\n" +
            "          \"is_overnight\": false,\n" +
            "          \"start\": \"1600\",\n" +
            "          \"end\": \"1900\",\n" +
            "          \"day\": 2\n" +
            "        },\n" +
            "        {\n" +
            "          \"is_overnight\": false,\n" +
            "          \"start\": \"1200\",\n" +
            "          \"end\": \"1500\",\n" +
            "          \"day\": 3\n" +
            "        },\n" +
            "        {\n" +
            "          \"is_overnight\": false,\n" +
            "          \"start\": \"1600\",\n" +
            "          \"end\": \"1900\",\n" +
            "          \"day\": 3\n" +
            "        },\n" +
            "        {\n" +
            "          \"is_overnight\": false,\n" +
            "          \"start\": \"1200\",\n" +
            "          \"end\": \"1500\",\n" +
            "          \"day\": 4\n" +
            "        },\n" +
            "        {\n" +
            "          \"is_overnight\": false,\n" +
            "          \"start\": \"1600\",\n" +
            "          \"end\": \"1900\",\n" +
            "          \"day\": 4\n" +
            "        },\n" +
            "        {\n" +
            "          \"is_overnight\": false,\n" +
            "          \"start\": \"1200\",\n" +
            "          \"end\": \"1500\",\n" +
            "          \"day\": 5\n" +
            "        },\n" +
            "        {\n" +
            "          \"is_overnight\": false,\n" +
            "          \"start\": \"1600\",\n" +
            "          \"end\": \"1900\",\n" +
            "          \"day\": 5\n" +
            "        },\n" +
            "        {\n" +
            "          \"is_overnight\": false,\n" +
            "          \"start\": \"1200\",\n" +
            "          \"end\": \"1500\",\n" +
            "          \"day\": 6\n" +
            "        },\n" +
            "        {\n" +
            "          \"is_overnight\": false,\n" +
            "          \"start\": \"1600\",\n" +
            "          \"end\": \"1900\",\n" +
            "          \"day\": 6\n" +
            "        }\n" +
            "      ],\n" +
            "      \"hours_type\": \"REGULAR\",\n" +
            "      \"is_open_now\": false\n" +
            "    }\n" +
            "  ]\n" +
            "}";
}
